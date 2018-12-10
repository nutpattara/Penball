package objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import screens.Stage01;

public class Boss extends Character implements Enemy{

	public Boss(World world, float x, float y, int level) {
		super(world, new Texture(Gdx.files.internal("assets/character/Boss.png")), 
				x / Stage01.SCALE, y / Stage01.SCALE, "Boss");
		health = 20;
		attack = 3;
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}

}
