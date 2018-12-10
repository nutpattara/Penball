package objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import game.GameManager;
import screens.Stage01;

public class Player extends Character{
	
	private GameManager manager;
	
	public Player(GameManager manager, float x, float y) {
		super(manager.getWorld(), new Texture(Gdx.files.internal("assets/character/Player.png")),
				x / Stage01.SCALE, y / Stage01.SCALE, "Player");
		health = manager.getPlayerHealth();
		attack = manager.getPlayerAttack();
	}
	
	public boolean stateCheck() {
		if (health == 0) return false;
		this.linearCheck();
		
		return true;
	}

	public void dispose() {
	}
	
}
