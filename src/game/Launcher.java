package game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Launcher {

	public static void main(String[] args) {
	      LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	      config.title = "Penball";
	      config.width = Penball.WIDTH;
	      config.height = Penball.HEIGHT;
	      config.resizable = false;
	      new LwjglApplication(new Penball(), config);

	}

}
