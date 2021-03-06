package objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import game.GameManager;
import screens.Stage01;

public class Fox extends Enemy {

	public Fox(GameManager manager, float x, float y, int level) {
		super(manager, new Texture(Gdx.files.internal("character/Fox.png")), x / Stage01.SCALE,
				y / Stage01.SCALE, level);
		health = 6;
		attack = 2;
		delayShoot = 120;
	}

}
