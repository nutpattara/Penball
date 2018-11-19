package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import objects.Entity;

public class Utills {
	
	public static void positionCheck(Entity entity) {
		System.out.println(entity.getX());
		System.out.println(entity.getY());
		System.out.println(entity.body.getLinearVelocity());
	}
}
