package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.viewport.Viewport;

import screens.MainMenu;

public class Penball extends Game {

	public static final boolean DEBUG = true;
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
  	public SpriteBatch batch;
	public BitmapFont font;
	public BitmapFont font2;
	public GameManager manager;

	public void create() {
		batch = new SpriteBatch();
		manager = new GameManager(this);
		//Font
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/font/04B.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		font = generator.generateFont(parameter);
		font.setColor(1.0f, 0.0f, 0.0f, 1.0f);
		generator.dispose();
		//
		this.setScreen(new MainMenu(this));
	}

	public void render() {
		super.render(); 
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

}
