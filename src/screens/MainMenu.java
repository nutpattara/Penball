package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.Penball;

public class MainMenu implements Screen {

	private Penball game;
	private OrthographicCamera camera;
	private Viewport viewport;
	private Texture screen;
	private Texture playButton;
	private Texture playButtonClicked;
	private Texture highscoreButton;
	private Texture highscoreButtonClicked;
	private Texture exitButton;
	private Texture exitButtonClicked;
	private int delay;

	public MainMenu(Penball game) {
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 640, 480);
		viewport = new FitViewport(640, 480, camera);
		screen = new Texture(Gdx.files.internal("main/mainBG.png"));
		playButton = new Texture(Gdx.files.internal("main/PlayButton.png"));
		playButtonClicked = new Texture(Gdx.files.internal("main/PlayButtonClicked.png"));
		highscoreButton = new Texture(Gdx.files.internal("main/HighScoreButton.png"));
		highscoreButtonClicked = new Texture(Gdx.files.internal("main/HighScoreButtonClicked.png"));
		exitButton = new Texture(Gdx.files.internal("main/ExitButton.png"));
		exitButtonClicked = new Texture(Gdx.files.internal("main/ExitButtonClicked.png"));

	}

	@Override
	public void render(float arg0) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		delay = delay <= 20 ? delay + 1 : 20;
		Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		viewport.unproject(touchPos);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();

		game.batch.draw(screen, 0, 0);
		
		if (touchPos.x >= 257 && touchPos.x <= 383) {
			if (touchPos.y >= 200 && touchPos.y < 240) {
				game.batch.draw(playButtonClicked, 257, 190);
			} else {
				game.batch.draw(playButton, 257, 190);
			}
			if (touchPos.y >= 150 && touchPos.y < 190) {
				game.batch.draw(highscoreButtonClicked, 257, 140);
			} else {
				game.batch.draw(highscoreButton, 257, 140);
			}
			if (touchPos.y >= 100 && touchPos.y < 140) {
				game.batch.draw(exitButtonClicked, 257, 90);
			} else {
				game.batch.draw(exitButton, 257, 90);
			}
		} else {
			game.batch.draw(playButton, 257, 190);
			game.batch.draw(highscoreButton, 257, 140);
			game.batch.draw(exitButton, 257, 90);
		}
		game.batch.end();

		if (Gdx.input.isTouched() && delay == 20) {

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
		playButtonClicked.dispose();
		highscoreButton.dispose();
		highscoreButtonClicked.dispose();
		exitButton.dispose();
		exitButtonClicked.dispose();
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
