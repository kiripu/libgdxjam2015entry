package ru.kiripu.lifeinspace;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import ru.kiripu.lifeinspace.managers.AssetsController;
import ru.kiripu.lifeinspace.screens.MainMenu;

public class Main extends Game
{
	public  static AssetsController assetsController;
	private SpriteBatch batch;
	private Texture img;
	private ParticleEffect pe;
	private float curTime = 0;
	private Animation animation;
	private TextureRegion currentFrame;
	private float currentState = 0f;

	@Override
	public void create ()
	{
		assetsController = new AssetsController();
		assetsController.init();

		batch = new SpriteBatch();
		img = new Texture("background.png");

		pe = new ParticleEffect();
		pe.load(Gdx.files.internal("SpaceParticle"),Gdx.files.internal(""));
		pe.start();

		animation = new Animation(0.03f, assetsController.getTextureAtlas().findRegions("hero/idle"));

		setScreen(new MainMenu());
	}

	@Override
	public void render ()
	{
		float deltaTime = Gdx.graphics.getDeltaTime();
		float fastForwardTime = 2f;
		if ((curTime += deltaTime) < fastForwardTime)
		{
			float speed = (curTime < fastForwardTime * 0.5) ? 30 : (fastForwardTime - curTime) * 30;
			pe.update(deltaTime * speed);
		}
		else pe.update(deltaTime);

		currentState += deltaTime;
		currentFrame = animation.getKeyFrame(currentState, true);

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(currentFrame, 100, 100);
		pe.draw(batch);
		batch.end();

		if (pe.isComplete()) pe.reset();

		super.render();
	}
}
