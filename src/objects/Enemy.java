package objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import game.GameManager;

public abstract class Enemy extends Character {
	
	protected GameManager manager;
	protected int delayCount;
	protected int delayShoot;
	//protected Array<Bullet> bullets;
	
	public Enemy(GameManager manager,Texture texture, float x, float y, int level) {
		super(manager.getWorld(), texture, x, y, "Enemy");
		this.manager = manager;
		delayCount = 0;
		//bullets = new Array<Bullet>();
	}

	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		if (!isRemoved) {
			delayCount++;
			if (delayCount == delayShoot) {
				delayCount = 0;
				shoot();
			}
		}
	}
	
	public void shoot() {
		Bullet bullet = new Bullet(manager.getWorld(), new Texture(Gdx.files.internal("assets/character/Bullet.png")), 
				this.body.getPosition().x, this.body.getPosition().y, attack);
		bullet.body.setUserData(bullet);
		manager.bullets.add(bullet);
		
		Vector2 pos = bullet.getPosition();
		Vector2 playerPos = manager.player.getPosition();
		bullet.body.applyLinearImpulse((playerPos.x-pos.x), (playerPos.y-pos.y),pos.x, pos.y, true);
	}
}
