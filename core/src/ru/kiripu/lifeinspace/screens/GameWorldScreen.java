package ru.kiripu.lifeinspace.screens;

import com.badlogic.gdx.Screen;
import ru.kiripu.lifeinspace.world.GameWorld;

/**
 * Created by kiripu on 06.01.2016.
 */
public class GameWorldScreen implements Screen {

    private final GameWorld world;

    public GameWorldScreen()
    {
        world = new GameWorld();
        world.init();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta)
    {
        world.update(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
