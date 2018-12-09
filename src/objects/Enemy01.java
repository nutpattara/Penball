package objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import screens.Stage01;

public class Enemy01 extends Character implements Enemy{
	
	public Enemy01(World world, float x, float y, int level) {
		super(world, new Texture(Gdx.files.internal("assets/character/Seal.png")),
				x / Stage01.SCALE, y / Stage01.SCALE, "Enemy");
		health = 5;
		attack = 1;
	}

	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		shoot();
	}
	
	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}

}
