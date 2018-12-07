package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.GameManager;
import game.Penball;

public class MainMenu implements Screen{

	Penball game;
	OrthographicCamera camera;
	Viewport viewport;
	Texture screen;
	Texture playButton;
	Texture highscoreButton;
	Texture exitButton;
	
	public MainMenu(Penball game) {
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 640, 480);
		viewport = new FitViewport(640,480,camera);
		screen = new Texture(Gdx.files.internal("assets/main/mainBG.png"));
		playButton = new Texture(Gdx.files.internal("assets/main/PlayButton.png"));
		highscoreButton = new Texture(Gdx.files.internal("assets/main/HighScoreButton.png"));
		exitButton = new Texture(Gdx.files.internal("assets/main/ExitButton.png"));
		
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
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
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.batch.draw(screen, 0,0);
		game.batch.draw(playButton, 257, 190);
		game.batch.draw(highscoreButton, 257, 140);
		game.batch.draw(exitButton, 257, 90);
		game.batch.end();

		if (Gdx.input.isTouched()) {
			game.manager = new GameManager(game);
			dispose();
		}
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		viewport.update(arg0, arg1);
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

}
