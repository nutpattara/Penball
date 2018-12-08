package objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import screens.Stage01;

public class PolarBear extends Character implements Enemy {

	public PolarBear(World world, float x, float y, int level) {
		super(world, new Texture(Gdx.files.internal("assets/character/PolarBear.png"))
				, x / Stage01.SCALE , y / Stage01.SCALE , "Enemy");
		health = 6;
		attack = 2;
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}
	
}
