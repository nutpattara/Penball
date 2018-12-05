package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.math.Vector3;

import game.ScreenCalibrator;

class ScreenCalibratorTest {

	@Test
	void testScreenCalibrator() {
		ScreenCalibrator cali = new ScreenCalibrator();
		assertEquals(640, cali.getWidth());
		assertEquals(480, cali.getHeight());
		assertEquals(1.333, cali.getRatio(), 0.001f);
	}

	@Test
	void testUpdate() {
		ScreenCalibrator cali = new ScreenCalibrator();
		cali.update(1077, 480);
		assertEquals(640, cali.getWidth(), 0.1f);
		cali.update(640, 700);
		assertEquals(480, cali.getHeight(), 0.1f);
		cali.update(1534, 593);
		assertEquals(790.66f, cali.getWidth(), 0.1f);
	}

	@Test
	void testCalibrate() {
		ScreenCalibrator cali = new ScreenCalibrator();
		Vector3 v = new Vector3();
		Vector3 newV;
		
		v.set(20.539f, 15f, 0);
		newV = cali.calibrate(v, 1505, 480);
		assertEquals(5.0f, newV.x, 0.5f);
		assertEquals(v.y, newV.y);
		
		v.set(5.0f, 19.2f, 0);
		newV = cali.calibrate(v, 640, 925);
		assertEquals(15.0f, newV.y, 0.5f);
		assertEquals(v.x, newV.x);
		
		v.set(5.0f, 18.056835f, 0);
		newV = cali.calibrate(v, 640, 739);
		assertEquals(15.0f, newV.y, 0.5f);
		assertEquals(v.x, newV.x);
		
		v.set(5.0f, 17.208511f, 0);
		newV = cali.calibrate(v, 928, 940);
		assertEquals(15.0f, newV.y, 0.5f);
		assertEquals(v.x, newV.x);
		
		v.set(11.0875f, 15.0f, 0);
		newV = cali.calibrate(v, 1039, 600);
		assertEquals(5.0f, newV.x, 0.5f);
		assertEquals(v.y, newV.y);
	}

}
