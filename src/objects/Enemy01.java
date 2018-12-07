package objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import screens.Stage01;

public class Enemy01 extends Entity implements Enemy{
	
	int health;
	
	public Enemy01(World world, float x, float y, String name) {
		super(world, new Texture(Gdx.files.internal("assets/enemy01.gif")),
				x / Stage01.SCALE, y / Stage01.SCALE, name);
		health = 5;
		
	}
	
	public boolean takeDamage(int damage) {
		if (damage > 0) {
			health = health - damage > 0 ? health - damage : 0;
			return true;
		}
		return false;
	}
	
	public int getHealth() {
		return health;
	}

}
