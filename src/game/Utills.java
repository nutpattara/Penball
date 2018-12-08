package game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import objects.Entity;

public class Utills {
	
	public static void positionCheck(Entity entity) {
		System.out.println(entity.getX());
		System.out.println(entity.getY());
		System.out.println(entity.body.getLinearVelocity());
		System.out.println(Gdx.graphics.getWidth());
		System.out.println(Gdx.graphics.getHeight());
	}
	
	public static int randomNum(int low, int high) {
		Random random = new Random();
		return low + random.nextInt(high - low);
	}
	
}
