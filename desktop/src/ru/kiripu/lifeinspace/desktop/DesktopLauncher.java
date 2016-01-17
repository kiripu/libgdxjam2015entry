package ru.kiripu.lifeinspace.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.kiripu.lifeinspace.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Live in spacce";
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new Main(), config);
	}
}
