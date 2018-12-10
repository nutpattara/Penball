package objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import game.GameManager;
import screens.Stage01;

public class Boss extends Enemy{
	
	public Boss(GameManager manager, float x, float y, int level) {
		super(manager, new Texture(Gdx.files.internal("assets/character/Boss.png")), 
				x / Stage01.SCALE, y / Stage01.SCALE, level);
		health = 20;
		attack = 3;
	}

}
