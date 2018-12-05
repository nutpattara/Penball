package screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.GameManager;
import game.Penball;
import game.ScreenCalibrator;
import game.Utills;
import objects.Enemy01;
import objects.Entity;
import objects.Player;

public class Stage01 implements Screen{

	public final static int SCALE = 10;
	private Penball game;
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	private Viewport viewport;
	private ScreenCalibrator screenCalibrator;
	
	private TiledMap tileMap;
	
	private Player player;
	private Entity enemy1;
	private Entity enemy2;
	private Entity enemy3;
	
	private boolean touchCheck;
	private boolean stageClear;
	private float xPos;
	private float yPos;
	
	public Stage01 (Penball game) {
		
		this.game = game;
		touchCheck = false;
		world = new World(new Vector2(0,0), true);
		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Penball.WIDTH / SCALE, Penball.HEIGHT / SCALE);
		viewport = new FitViewport(Penball.WIDTH / SCALE,Penball.HEIGHT / SCALE,camera);
		screenCalibrator = new ScreenCalibrator();
		
		// Stage Setup
		stageClear = false;
		
		// Entity create40]]
		player = new Player(world, 50, 150);
		
		enemy1 = new Enemy01(world, 150, 300);
		
		// First we create a body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(50 / SCALE, 250 / SCALE);
		bodyDef.linearDamping = 0.35f;
		bodyDef.angularDamping = 0.5f;

		// Create our body in the world using our body definition
		Body body = world.createBody(bodyDef);
		CircleShape circle = new CircleShape();
		FixtureDef fixtureDef = new FixtureDef();
		/*
		playerBody = world.createBody(bodyDef);

		// Create a circle shape and set its radius to 6
		circle.setRadius(24 / SCALE);

		// Create a fixture definition to apply our shape to
		fixtureDef.shape = circle;
		fixtureDef.density = 0.5f; 
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f; // Make it bounce a little bit

		// Create our fixture and attach it to the body
		Fixture fixture = playerBody.createFixture(fixtureDef);
		*/
		
		
		//Wall
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(320 / SCALE, 0 / SCALE);
		body = world.createBody(bodyDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(320 / SCALE, 20 / SCALE);
		
		fixtureDef.shape = shape;
		Fixture fixture = body.createFixture(fixtureDef);
		
		bodyDef.position.set(0 / SCALE, 240 / SCALE);
		body = world.createBody(bodyDef);
		shape.setAsBox(20 / SCALE, 240 / SCALE);
		body.createFixture(fixtureDef);
		
		bodyDef.position.set(640 / SCALE, 240 / SCALE);
		body = world.createBody(bodyDef);
		shape.setAsBox(20 / SCALE, 240 / SCALE);
		body.createFixture(fixtureDef);
		
		bodyDef.position.set(320 / SCALE, 480 / SCALE);
		body = world.createBody(bodyDef);
		shape.setAsBox(320 / SCALE, 20 / SCALE);
		body.createFixture(fixtureDef);
		
		shape.dispose();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float arg0) {
		// Clear screen
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Camera move
		debugRenderer.render(world, camera.combined);
		camera.update();
		
		if (Gdx.input.isKeyPressed(Keys.A)) {			
			Utills.positionCheck(player);
			System.out.println(screenCalibrator.getRatio());
		}
		
		
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			Vector3 cali = screenCalibrator.calibrate(touchPos, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			System.out.println("Raw Input: " + touchPos.x+ " " + touchPos.y);
			System.out.println("Calibrate: " + cali.x+ " " + cali.y);
		}
		
		
		if (Gdx.input.isTouched() && !touchCheck) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			xPos = touchPos.x / SCALE;
			yPos = touchPos.y / SCALE;
			if (player.getX()+1.6f >= (xPos)
					&& player.getX()-1.6f <= (xPos)
					&& 48-player.getY()+1.6f >= (yPos)
					&& 48-player.getY()-1.6f <= (yPos)) {
				touchCheck = true;
				player.stopMovement();
				System.out.println("test" + touchPos.x / SCALE + " " + touchPos.y / SCALE);
				System.out.println("test" + player.getX() + " " + player.getY());
			}
		}
		
		if (!Gdx.input.isTouched() && touchCheck) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			float xPos2 = touchPos.x / SCALE;
			float yPos2 = touchPos.y / SCALE;
			System.out.println("SHOOT");
			touchCheck = false;
			Vector2 pos = player.getPosition();
			player.body.applyLinearImpulse((20*(xPos - xPos2)), (20*(yPos2 - yPos)), pos.x, pos.y, true);
		}
		
		player.linearCheck();
		
		game.batch.begin();
		player.render(game.batch);
		enemy1.render(game.batch);
		//enemy2.render(game.batch);
		game.batch.end();
		
		if (game.manager.getEnemiesInStage() == 0 && !stageClear) stageClear = true;
		
		world.step(1/60f, 6, 2);
		
		// Hp check
	}

	@Override
	public void resize(int arg0, int arg1) {
		viewport.update(arg0, arg1);
		screenCalibrator.update(arg0, arg1);
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void show() {
		
	}
	
	@Override
	public void dispose() {
		
	}

	
	
}
