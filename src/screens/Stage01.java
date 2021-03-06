package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.GameContactListener;
import game.Penball;
import game.Utills;
import objects.Player;

public class Stage01 implements Screen {

	// Core engine
	public final static int SCALE = 10;
	private Penball game;
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private Viewport viewport;
	private GameContactListener contactListener;

	// Game
	private Texture map;
	private Player player;

	// Vars
	private boolean touchCheck;
	private float xPos;
	private float yPos;
	private int delay;

	public Stage01(Penball game) {

		// Core engine setup
		this.game = game;
		touchCheck = false;
		world = new World(new Vector2(0, 0), true);
		contactListener = new GameContactListener(game);
		world.setContactListener(contactListener);
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Penball.WIDTH / SCALE, Penball.HEIGHT / SCALE);
		viewport = new FitViewport(Penball.WIDTH / SCALE, Penball.HEIGHT / SCALE, camera);

		// Stage Setup
		game.manager.setWorld(world);

		// Entity create
		game.manager.createLevel();
		player = game.manager.player;

		// Set map Texture
		setMap();

		// Create a body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(50 / SCALE, 250 / SCALE);
		bodyDef.linearDamping = 0.35f;
		bodyDef.angularDamping = 0.5f;

		// Create our body in the world using our body definition
		Body body = world.createBody(bodyDef);
		FixtureDef fixtureDef = new FixtureDef();

		// Wall
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(320 / SCALE, 0 / SCALE);
		body = world.createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(320 / SCALE, 100 / SCALE);

		fixtureDef.shape = shape;
		fixtureDef.filter.categoryBits = Utills.Vars.BIT_WALL;
		body.createFixture(fixtureDef).setUserData("Wall");

		bodyDef.position.set(0 / SCALE, 240 / SCALE);
		body = world.createBody(bodyDef);
		shape.setAsBox(32 / SCALE, 240 / SCALE);
		body.createFixture(fixtureDef).setUserData("Wall");

		bodyDef.position.set(640 / SCALE, 240 / SCALE);
		body = world.createBody(bodyDef);
		shape.setAsBox(32 / SCALE, 240 / SCALE);
		body.createFixture(fixtureDef).setUserData("Wall");

		bodyDef.position.set(320 / SCALE, 480 / SCALE);
		body = world.createBody(bodyDef);
		shape.setAsBox(320 / SCALE, 32 / SCALE);
		body.createFixture(fixtureDef).setUserData("Wall");

		shape.dispose();

	}

	@Override
	public void render(float arg0) {
		// Clear screen
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Camera move
		// debugRenderer.render(world, camera.combined);
		camera.update();

		if (Gdx.input.isTouched() && !touchCheck) {
			Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			viewport.unproject(touchPos);
			xPos = touchPos.x;
			yPos = touchPos.y;
			if (player.getX() + 1.6f >= (xPos) && player.getX() - 1.6f <= (xPos) && player.getY() + 1.6f >= (yPos)
					&& player.getY() - 1.6f <= (yPos)) {
				touchCheck = true;
				player.stopMovement();
			}

		}

		if (!Gdx.input.isTouched() && touchCheck) {
			Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			viewport.unproject(touchPos);
			float xPos2 = touchPos.x;
			float yPos2 = touchPos.y;
			touchCheck = false;
			Vector2 pos = player.getPosition();
			player.body.applyLinearImpulse((20 * (xPos - xPos2)), (20 * (yPos - yPos2)), pos.x, pos.y, true);
		}

		game.batch.begin();
		game.batch.draw(map, 0, 0);

		// render entities
		player.render(game.batch);
		for (int i = 0; i < game.manager.enemies.size; i++) {
			game.manager.enemies.get(i).render(game.batch);
		}

		for (int i = 0; i < game.manager.bullets.size; i++) {
			game.manager.bullets.get(i).render(game.batch);
		}

		// HUD Render
		game.font.draw(game.batch, "LEVEL " + Integer.toString(game.manager.getCurrentLevel()), 270, 470);
		game.font.draw(game.batch, Integer.toString(game.manager.getScore()), 400, 40);
		game.font.draw(game.batch, Integer.toString(game.manager.player.getHealth()), 200, 40);

		game.batch.end();

		if (Penball.DEBUG)
			debugRenderer.render(world, camera.combined);

		world.step(1 / 60f, 6, 2);

		// remove bodies
		Array<Body> bodies = contactListener.getBodiesToRemove();
		for (int i = 0; i < bodies.size; i++) {
			Body b = bodies.get(i);
			world.destroyBody(b);
		}
		bodies.clear();

		if (!(player.stateCheck())) {
			game.setScreen(new GameOver(game));
			dispose();
		} else {
			// Level clear Check
			if (game.manager.getEnemiesInStage() == 0) {
				delay = delay <= 60 ? delay + 1 : 60;
				if (delay == 60) {
					toNextLevel();
				}
			}
		}

	}

	private void setMap() {
		if (game.manager.getCurrentLevel() <= 5) {
			map = new Texture(Gdx.files.internal("textures/Stage1.png"));
		} else if (game.manager.getCurrentLevel() <= 10) {
			map = new Texture(Gdx.files.internal("textures/Stage2.png"));
		} else if (game.manager.getCurrentLevel() <= 15) {
			map = new Texture(Gdx.files.internal("textures/Stage3.png"));
		} else {
			if ((game.manager.getCurrentLevel() % 5) == 0) {
				map = new Texture(Gdx.files.internal("textures/Boss.png"));
			} else {
				map = new Texture(Gdx.files.internal("textures/Stage4.png"));
			}
		}
	}

	private void toNextLevel() {
		game.manager.nextLevel();
		game.stats.setPlayerHealth(player.getHealth());
		game.stats.setPlayerAttack(player.getAttack());
		game.setScreen(new Stage01(game));
		dispose();
	}

	@Override
	public void resize(int arg0, int arg1) {
		viewport.update(arg0, arg1);
	}

	@Override
	public void dispose() {
		world.dispose();
		debugRenderer.dispose();
		map.dispose();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void show() {

	}

}
