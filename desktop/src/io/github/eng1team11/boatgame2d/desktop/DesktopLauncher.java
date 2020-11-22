package io.github.eng1team11.boatgame2d.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import io.github.eng1team11.boatgame2d.BoatGame;

import static java.lang.System.exit;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "BoatGame3D";
		config.width = 1280;
		config.height = 720;
		config.vSyncEnabled = true;
		config.useGL30 = true;
		new LwjglApplication(new BoatGame(), config);
	}
}
