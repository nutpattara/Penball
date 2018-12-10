package game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import objects.Boss;
import objects.Enemy;
import objects.Enemy01;
import objects.Entity;
import objects.Fox;
import objects.Player;
import objects.PolarBear;
import screens.Stage01;

public class GameManager {

	private Penball game;
	private int currentLevel;
	private int enemiesInStage;
	private int score;
	private World world;
	public PlayerStats stats;
	public Array<Entity> enemies;
	public Player player;
	
	public GameManager(Penball game) {
		this.game = game;
		stats = new PlayerStats();
		enemies = new Array<Entity>();
		currentLevel = 1;
		score = 0;
		//createLevel(1);
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public World getWorld() {
		return world;
	}
	
	
	/////// CREATE LEVEL /////////
	
	public void createLevel() {
		if ((currentLevel % 5) != 0) {
			createNormalLevel();
		} else {
			createBossLevel();
		}
	}
	
	public void createNormalLevel() {
		//Create player
		player = new Player(this, Utills.randomNum(100, 540), Utills.randomNum(100, 380));
		player.body.setUserData(player);
		
		//Create enemies
		int modifier = currentLevel / 5;
		int monsterAmount = Utills.randomNum(2+modifier, 4+modifier);
		enemiesInStage = monsterAmount;
		for (int i = 0; i < monsterAmount; i++) {
			int monsterType = Utills.randomNum(0, 3);
			Entity enemy;
			if (monsterType == 0) {
				enemy = new Enemy01(this, Utills.randomNum(100, 540), Utills.randomNum(100, 380), currentLevel);
			} else if (monsterType == 1) {
				enemy = new Fox(this, Utills.randomNum(100, 540), Utills.randomNum(100, 380), currentLevel);
			} else {
				enemy = new PolarBear(this, Utills.randomNum(100, 540), Utills.randomNum(100, 380), currentLevel);
			}
			enemy.body.setUserData(enemy);
			enemies.add(enemy);
		}
	}
	
	public void createBossLevel() {
		enemiesInStage = 1;
		//Create player
		player = new Player(this, 320, 120);
		player.body.setUserData(player);
		
		//Create Boss
		Entity boss;
		boss = new Boss(this, 320, 340, currentLevel);
		boss.body.setUserData(boss);
		enemies.add(boss);
	}
	
	public void enemyDies(String type) {
		if (type.equals("Enemy01")) {
			score += 100;
		} else if (type.equals("Fox")) {
			score += 150;
		} else if (type.equals("PolarBear")) {
			score += 200;
		} else if (type.equals("Boss")) {
			score += 1000;
		}
		score += (currentLevel - 1) * 5;
		enemiesInStage--;
	}
	
	
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	public void nextLevel() {
		for (int i = 0; i < enemies.size; i++) {
			enemies.get(i).dispose();
		}
		enemies.clear();
		currentLevel++;
	}
	
	public int getEnemiesInStage() {
		return enemiesInStage;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getPlayerHealth() {
		return game.stats.health;
	}
	
	public int getPlayerAttack() {
		return game.stats.attack;
	}
	
	public void setPlayerHealth(int health) {
		game.stats.health = health;
	}
	
	public void setPlayerAttack(int attack) {
		game.stats.attack = attack;
	}
	
}
