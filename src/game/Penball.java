package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import screens.MainMenu;

public class Penball extends Game {

	public static final boolean DEBUG = false;
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	private Music music;
	public PlayerStats stats;
  	public SpriteBatch batch;
	public BitmapFont font;
	public BitmapFont font2;
	public GameManager manager;

	public void create() {
		batch = new SpriteBatch();
		manager = new GameManager(this);
		stats = new PlayerStats();
		music = Gdx.audio.newMusic(Gdx.files.internal("audio/bgm.mp3"));
		music.setLooping(true);
		music.setVolume(0.05f);
		music.play();
		//Font
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/04B.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		font = generator.generateFont(parameter);
		font.setColor(1.0f, 0.0f, 0.0f, 1.0f);
		font2 = generator.generateFont(parameter);
		font2.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		generator.dispose();
		//
		this.setScreen(new MainMenu(this));
	}
	
	public class PlayerStats {
		protected int health;
		protected int attack;
		
		PlayerStats(){
			health = 20;
			attack = 2;
		}
		
		public int getPlayerHealth() {
			return health;
		}
		
		public int getPlayerAttack() {
			return attack;
		}
		
		public void setPlayerHealth(int health) {
			this.health = health;
		}
		
		public void setPlayerAttack(int attack) {
			this.attack = attack;
		}
	}

	public void render() {
		super.render(); 
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
		music.dispose();
	}

}
