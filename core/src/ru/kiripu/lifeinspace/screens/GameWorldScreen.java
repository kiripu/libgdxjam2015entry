package ru.kiripu.lifeinspace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import ru.kiripu.lifeinspace.factories.UIObjectFactory;
import ru.kiripu.lifeinspace.managers.GameMaster;
import ru.kiripu.lifeinspace.world.GameWorld;

/**
 * Created by kiripu on 06.01.2016.
 */
public class GameWorldScreen implements Screen {

    private final GameWorld world;
    private final Stage stage;
    private final Table table;
    private final Image progressBarLabel;
    private final ProgressBar progressBar;
    private final Button soundButton;

    public GameWorldScreen()
    {
        world = new GameWorld();
        world.init();

        progressBarLabel = UIObjectFactory.createImage("oxygenBar_label");
        progressBar = UIObjectFactory.createProgressBar("oxygenBar");
        soundButton = UIObjectFactory.createButton("soundButton");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        table = new Table();

        table.setFillParent(true);
        table.bottom();
        table.add(progressBarLabel).padBottom(20).padLeft(150).bottom();
        table.add(progressBar).padBottom(20).padLeft(5).bottom();
        table.add(soundButton).expand().bottom().right().pad(10);
        stage.addActor(table);

        progressBarLabel.addAction(Actions.forever(Actions.sequence(Actions.alpha(0.3f, 0.75f), Actions.alpha(1, 0.75f))));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta)
    {
        progressBar.setSize(380, 7);
        progressBar.setValue(GameMaster.getInstance().getOxygenProgress());

        world.update(delta);
        stage.act(delta);
        stage.draw();
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
