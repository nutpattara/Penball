package objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import game.GameManager;
import screens.Stage01;

public class Boss extends Character {

	protected GameManager manager;
	protected int delayCount;
	protected int delayShoot;

	public Boss(GameManager manager, float x, float y, int level) {
		super(manager.getWorld(), new Texture(Gdx.files.internal("assets/character/Boss.png")), x / Stage01.SCALE,
				y / Stage01.SCALE, "Boss");
		this.manager = manager;
		delayCount = 0;
		health = 20;
		attack = 3;
		delayCount = 150;
		delayShoot = 300;
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
		for (int i = 0; i < 7; i++) {
			int modifier = (i - 3) * 7;
			Bullet bullet = new Bullet(manager.getWorld(), new Texture(Gdx.files.internal("assets/character/Boss.png")),
					this.body.getPosition().x, this.body.getPosition().y, attack);
			bullet.body.setUserData(bullet);
			manager.bullets.add(bullet);

			Vector2 pos = bullet.getPosition();
			Vector2 playerPos = manager.player.getPosition();
			bullet.body.applyLinearImpulse((playerPos.x - pos.x) + modifier, (playerPos.y - pos.y), pos.x, pos.y, true);
		}
	}

}
