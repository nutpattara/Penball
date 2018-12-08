package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import game.Utills;

class UtillsTest {

	@Test
	void testRandomNum() {
		int correct = 0;
		for (int i = 0; i < 100; i++) {
			int num = Utills.randomNum(12, 43);
			if (num >= 12 && num < 43) correct++;
		}
		assertEquals(100, correct);
	}

}
