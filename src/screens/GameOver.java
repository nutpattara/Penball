package screens;

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
		
		game.font.draw(game.batch, "GAME OVER", 320, 240);
		
		game.batch.end();
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
