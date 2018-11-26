package objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import screens.Stage01;

public class Enemy01 extends Entity implements Enemy{
	
	public Enemy01(World world, float x, float y) {
		super(world, new Texture(Gdx.files.internal("assets/enemy01.gif")), x / Stage01.SCALE, y / Stage01.SCALE);
		
	}

}
