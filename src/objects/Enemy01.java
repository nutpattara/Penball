package objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import game.GameManager;
import screens.Stage01;

public class Enemy01 extends Enemy {

	public Enemy01(GameManager manager, float x, float y, int level) {
		super(manager, new Texture(Gdx.files.internal("assets/character/Seal.png")), x / Stage01.SCALE,
				y / Stage01.SCALE, level);
		health = 5;
		attack = 1;
		delayShoot = 180;
	}

}
