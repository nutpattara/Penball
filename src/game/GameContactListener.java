package game;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;

import objects.Enemy01;
import objects.Entity;
import objects.Fox;
import objects.PolarBear;

public class GameContactListener implements ContactListener{
	
	private Array<Body> bodiesToRemove;
	
	public GameContactListener() {
		super();
		bodiesToRemove = new Array<Body>();
	}

	@Override
	public void beginContact(Contact c) {
		// TODO Auto-generated method stub
		
		Fixture fa = c.getFixtureA();
		Fixture fb = c.getFixtureB();
		
		if (fa.getUserData() != null && fa.getUserData().equals("Player")) {
			//Player Hit enemy
			if (fb.getUserData() != null && fb.getUserData().equals("Enemy")) {
				boolean isDeath = false;
				if (fb.getBody().getUserData() instanceof Enemy01) {
					isDeath = ((Enemy01) fb.getBody().getUserData()).takeDamage(1);
				} else if (fb.getBody().getUserData() instanceof Fox) {
					isDeath = ((Fox) fb.getBody().getUserData()).takeDamage(1);
				} else if (fb.getBody().getUserData() instanceof PolarBear) {
					isDeath = ((PolarBear) fb.getBody().getUserData()).takeDamage(1);
				}
				
				if (isDeath) {
					bodiesToRemove.add(fb.getBody());
				}
			}
		}

	}

	@Override
	public void endContact(Contact c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact c, ContactImpulse ci) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact c, Manifold m) {
		// TODO Auto-generated method stub
		
	}
	
	public Array<Body> getBodiesToRemove(){
		return bodiesToRemove;
	}

}
