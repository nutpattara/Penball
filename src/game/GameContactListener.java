package game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import objects.Enemy01;
import objects.Entity;

public class GameContactListener implements ContactListener{

	@Override
	public void beginContact(Contact c) {
		// TODO Auto-generated method stub
		/*
		Entity fa = (Entity) c.getFixtureA().getUserData();
		Entity fb = (Entity) c.getFixtureB().getUserData();
		if (fa.getName() == "Player" && fb.getName() == "Enemy" ||
				fa.getName() == "Enemy" && fb.getName() == "Player") {
			if (fb.getName() == "Enemy") {
				((Enemy01) fb).takeDamage(2);
				System.out.println(((Enemy01) fb).getHealth());
			} else {
				((Enemy01) fa).takeDamage(2);
				System.out.println(((Enemy01) fb).getHealth());
			}
			
		}
		*/

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

}
