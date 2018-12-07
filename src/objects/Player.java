package objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import screens.Stage01;

public class Player extends Entity{

	int health;
	
	public Player(World world, float x, float y, String name) {
		super(world, new Texture(Gdx.files.internal("assets/player.gif")),
				x / Stage01.SCALE, y / Stage01.SCALE, name);
		health = 10;
	}
	
	public boolean stateCheck() {
		if (health == 0) return false;
		this.linearCheck();
		
		return true;
	}
	
	public boolean takeDamage(int damage) {
		if (damage > 0) {
			health = health - damage > 0 ? health - damage : 0;
			return true;
		}
		return false;
	}
	
	public void sling() {
		
	}
}
