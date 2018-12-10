package objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import game.GameManager;
import screens.Stage01;

public class PolarBear extends Enemy {

	public PolarBear(GameManager manager, float x, float y, int level) {
		super(manager, new Texture(Gdx.files.internal("assets/character/PolarBear.png")), x / Stage01.SCALE,
				y / Stage01.SCALE, level);
		health = 6;
		attack = 2;
		delayShoot = 240;
	}

}
