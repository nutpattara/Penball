package screens;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.Penball;

public class GameOver implements Screen{

	Penball game;
	OrthographicCamera camera;
	Viewport viewport;
	
	public GameOver(Penball game) {
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 640, 480);
		viewport = new FitViewport(640,480,camera);
	}
	
	@Override
	public void render(float arg0) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		
		game.font2.draw(game.batch, "GAME OVER", 200, 280);
		game.font2.draw(game.batch, "YOUR SCORE IS : " + Integer.toString(game.manager.getScore()), 200, 260);
		
		game.batch.end();
		
		if (Gdx.input.isTouched()) {
			Gdx.app.exit();
			dispose();
		}
	}

	@Override
	public void resize(int arg0, int arg1) {
		viewport.update(arg0, arg1);
	}

	@Override
	public void dispose() {
		
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
