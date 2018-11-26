package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

import screens.MainMenu;

public class Penball extends Game {

	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
  	public SpriteBatch batch;
	public BitmapFont font;
	public GameManager manager;

	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MainMenu(this));
	}

	public void render() {
		// Have no idea how this is work
		super.render(); 
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

}
