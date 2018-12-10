package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.GameManager;
import game.Penball;

public class MainMenu implements Screen {

	private Penball game;
	private OrthographicCamera camera;
	private Viewport viewport;
	private Texture screen;
	private Texture playButton;
	private Texture highscoreButton;
	private Texture exitButton;
	private int delay;

	public MainMenu(Penball game) {
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 640, 480);
		viewport = new FitViewport(640, 480, camera);
		screen = new Texture(Gdx.files.internal("assets/main/mainBG.png"));
		playButton = new Texture(Gdx.files.internal("assets/main/PlayButton.png"));
		highscoreButton = new Texture(Gdx.files.internal("assets/main/HighScoreButton.png"));
		exitButton = new Texture(Gdx.files.internal("assets/main/ExitButton.png"));

	}

	@Override
	public void render(float arg0) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		delay = delay <= 20 ? delay + 1 : 20;

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();

		game.batch.draw(screen, 0, 0);
		game.batch.draw(playButton, 257, 190);
		game.batch.draw(highscoreButton, 257, 140);
		game.batch.draw(exitButton, 257, 90);

		game.batch.end();

		if (Gdx.input.isTouched() && delay == 20) {
			Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			viewport.unproject(touchPos);

			if (touchPos.x >= 257 && touchPos.x <= 383) {
				if (touchPos.y >= 190 && touchPos.y < 240) {
					// Play game button
					game.setScreen(new Stage01(game));
					dispose();
				} else if (touchPos.y >= 140 && touchPos.y < 190) {
					// High Score button
					game.setScreen(new HighScore(game));
					dispose();
				} else if (touchPos.y >= 90 && touchPos.y < 140) {
					// Exit button
					Gdx.app.exit();
					dispose();
				}
			}
		}

	}

	@Override
	public void resize(int arg0, int arg1) {
		viewport.update(arg0, arg1);
	}

	@Override
	public void dispose() {
		screen.dispose();
		playButton.dispose();
		highscoreButton.dispose();
		exitButton.dispose();
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
