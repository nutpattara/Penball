package game;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;

import objects.Enemy;
import objects.Enemy01;
import screens.Stage01;

public class GameManager {

	private Penball game;
	private int currentLevel;
	private int enemiesInStage;
	private ArrayList<Screen> stages;
	
	public GameManager(Penball game) {
		this.game = game;
		currentLevel = 1;
		createLevel1();
		
		game.setScreen(new Stage01(game));
	}
	
	public void createLevel1() {
		// random 
		enemiesInStage = (int)(Math.random() * 3 + 1);
		if (enemiesInStage < 2) enemiesInStage = 2;
		
		
		
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	public void nextLevel() {
		currentLevel++;
	}
	
	public int getEnemiesInStage() {
		return enemiesInStage;
	}
}
