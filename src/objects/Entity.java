
package objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import screens.Stage01;

public abstract class Entity {
	
	private int size;
	protected String type;
	protected World world;
	protected Sprite sprite;
	protected boolean isRemoved;
	public Body body;
	
	public Entity (World world, Texture texture, float x, float y, String type, int size) {
		isRemoved = false;
		this.world = world;
		this.type = type;
		this.size = type.equals("Boss") ? 36 : size;
		sprite = new Sprite(texture);
		createSprite(x ,y);
		
	}
	
	public void createSprite(float x, float y) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(x, y);
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.linearDamping = 0.35f;
		bodyDef.angularDamping = 0.5f;
		body = world.createBody(bodyDef);
		
		FixtureDef fixtureDef = new FixtureDef();
		CircleShape circle = new CircleShape();
		circle.setRadius(size / Stage01.SCALE);
		
		fixtureDef.shape = circle;
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f;
		body.createFixture(fixtureDef).setUserData(type);
		
		circle.dispose();
	}
	
	public void render(SpriteBatch batch) {
		if (!isRemoved) {
			float posX;
			float posY;
			float rotation;
			if (type.equals("Boss")) {
				posX = getX() * Stage01.SCALE - sprite.getWidth()/2;
				posY = getY() * Stage01.SCALE - 32;
				rotation = 0;
			} else {
				posX = getX() * Stage01.SCALE - sprite.getWidth()/2;
				posY = getY() * Stage01.SCALE - sprite.getHeight()/2;
				rotation = (float) Math.toDegrees(body.getAngle());
			}
			sprite.setPosition(posX, posY);
			sprite.setRotation(rotation);
			
			sprite.draw(batch);
		}
	}
	
	public float getX() {
		return body.getPosition().x;
	}
	
	public float getY() {
		return body.getPosition().y;
	}

	public Vector2 getPosition() {
		return body.getPosition();
	}
	
	public float getRatation() {
		return (float) Math.toDegrees(body.getAngle());
	}
	
	public void linearCheck() {
		// Entity will stop moving if LinearVelocity < 1
		if ((body.getLinearVelocity().x < 0.2 && body.getLinearVelocity().x > -0.2)
			|| (body.getLinearVelocity().y < 0.2 && body.getLinearVelocity().y > -0.2)){
			stopMovement();
		}
	}
	
	public void stopMovement() {
		body.setLinearVelocity(0, 0);
		body.setAngularVelocity(0);
	}
	
	public String getType() {
		return type;
	}
}
