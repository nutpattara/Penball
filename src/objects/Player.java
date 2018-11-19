package objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import screens.Stage01;

public class Player extends Entity{

	public Player(World world, float x, float y) {
		super(world, new Texture("player.gif"), x / Stage01.SCALE, y / Stage01.SCALE);
	}
	
	public void sling() {
		
	}
}
