package objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

public class Bullet extends Entity{

	protected int attack;
	
	public Bullet(World world, Texture texture, float x, float y, int attack) {
		super(world, texture, x, y, "Bullet",16);
		this.attack = attack;
		
	}

	public int getAttack() {
		return attack;
	}

	public void shot() {
		isRemoved = true;
	}
	
}
