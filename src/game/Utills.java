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
	
	public static class Vars {
		public static final short BIT_PLAYER = 2;
		public static final short BIT_ENEMY = 4;
		public static final short BIT_WALL = 8;
		public static final short BIT_BULLET = 16;
	}
	
}
