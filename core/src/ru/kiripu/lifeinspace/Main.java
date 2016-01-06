package ru.kiripu.lifeinspace;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.kiripu.lifeinspace.managers.AssetsController;
import ru.kiripu.lifeinspace.screens.MainMenu;

public class Main extends Game
{
	public static Game game;
	public static int width;
	public static int height;
	public static OrthographicCamera camera;
	public static AssetsController assetsController;

	private SpriteBatch batch;
	private Texture backgroundImage;
	private ParticleEffect pe;
	private float curTime = 0;

	@Override
	public void create ()
	{
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(width, height);
		camera.translate(width * 0.5f, height * 0.5f);
		camera.update();
		game = this;

		assetsController = new AssetsController();
		assetsController.init();

		batch = new SpriteBatch();
		backgroundImage = new Texture("background.png");

		pe = new ParticleEffect();
		pe.load(Gdx.files.internal("SpaceParticle"),Gdx.files.internal(""));
		pe.start();

		setScreen(new MainMenu());
	}

	@Override
	public void render ()
	{
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		float deltaTime = Gdx.graphics.getDeltaTime();
		float fastForwardTime = 2f;
		if ((curTime += deltaTime) < fastForwardTime)
		{
			float speed = (curTime < fastForwardTime * 0.5) ? 30 : (fastForwardTime - curTime) * 30;
			pe.update(deltaTime * speed);
		}
		else pe.update(deltaTime);

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(backgroundImage, 0, 0);
		pe.draw(batch);
		batch.end();

		if (pe.isComplete()) pe.reset();

		super.render();
	}
}
