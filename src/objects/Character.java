package objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Character extends Entity {

	protected int health;
	protected int attack;

	public Character(World world, Texture texture, float x, float y, String type) {
		super(world, texture, x, y, type, 24);
	}

	public boolean takeDamage(int damage) {
		if (damage > 0) {
			health = health - damage > 0 ? health - damage : 0;
		}
		if (health == 0) {
			isRemoved = true;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
	}

	public int getHealth() {
		return health;
	}

	public int getAttack() {
		return attack;
	}

}
