package screens;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.Penball;

public class HighScore implements Screen {

	private Penball game;
	private OrthographicCamera camera;
	private Viewport viewport;
	private int delay;
	private Texture screen;
	private int score;

	public HighScore(Penball game) {
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 640, 480);
		viewport = new FitViewport(640, 480, camera);
		screen = new Texture(Gdx.files.internal("main/mainBG.png"));
		delay = 0;
		score = 0;

		File highScore = new File("highscore.txt");
		try {
			if (highScore.createNewFile()) {
				FileWriter writer = new FileWriter(highScore);
				writer.write("0");
				writer.close();
			}
			BufferedReader br = new BufferedReader(new FileReader("highscore.txt"));
			String line = br.readLine();
			while (line != null) {
				int nextScore = Integer.parseInt(line);
				if (nextScore > score) {
					score = nextScore;
				}
				line = br.readLine();
			}
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "ERROR : Can not load highscore.txt");
			Gdx.app.exit();
		}
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

		game.font2.draw(game.batch, "YOUR HIGH SCORE IS", 200, 240);
		game.font2.draw(game.batch, Integer.toString(score), 280, 210);

		game.batch.end();

		if (Gdx.input.isTouched() && delay == 20) {
			game.setScreen(new MainMenu(game));
			dispose();
		}
	}

	@Override
	public void resize(int arg0, int arg1) {
		viewport.update(arg0, arg1);
	}

	@Override
	public void dispose() {
		screen.dispose();
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
