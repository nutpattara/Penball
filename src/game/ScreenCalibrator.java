package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

public class ScreenCalibrator {

	private float width;
	private float height;
	private float ratio;
	
	public ScreenCalibrator() {
		//width = Gdx.graphics.getWidth();
		//height = Gdx.graphics.getHeight();
		width = 640;
		height = 480;
		ratio = (float) width / height;
	}
	
	public void update(int width, int height) {
		ratio = (float) width / height;
		
		if (ratio >= 1.33) {
			this.height = height;
			this.width = (float) height/3*4;
			
		} else {
			this.width = width;
			this.height = (float) width/4*3;
			
		}
		
	}
	
	public Vector3 calibrate(Vector3 v, float w, float h) {
		// fix this
		
		Vector3 newV = new Vector3();
		float space;
		float newX;
		float newY;
		float lenght;
		
		if (ratio >= 1.333) {
			// extends x axis
			space = w / 64;
			lenght = (float) space * v.x;
			newX = (lenght - ((w - width) / 2)) / 10;
			newY = v.y;
			
		} else {
			// extends y axis 
			space = h / 64;
			lenght = (float) space * v.y;
			newY = (lenght - ((h - height) / 2)) / 10;
			newX = v.x;
			
		}
		
		newV.set(newX, newY, 0);
		return newV;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public float getRatio() {
		return ratio;
	}
	
}
