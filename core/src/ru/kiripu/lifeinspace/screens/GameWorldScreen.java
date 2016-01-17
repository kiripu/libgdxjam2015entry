package ru.kiripu.lifeinspace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import ru.kiripu.lifeinspace.Main;
import ru.kiripu.lifeinspace.enums.LocalStorageKeys;
import ru.kiripu.lifeinspace.enums.WindowType;
import ru.kiripu.lifeinspace.factories.UIObjectFactory;
import ru.kiripu.lifeinspace.factories.WindowFactory;
import ru.kiripu.lifeinspace.managers.GameMaster;
import ru.kiripu.lifeinspace.managers.GameTimeController;
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
    private boolean startShowGameOver = false;

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
        table.setTouchable(Touchable.enabled);

        table.setFillParent(true);
        table.bottom();
        table.add(progressBarLabel).padBottom(20).padLeft(150).bottom();
        table.add(progressBar).padBottom(20).padLeft(5).bottom();
        table.add(soundButton).expand().bottom().right().pad(10);
        stage.addActor(table);

        progressBarLabel.addAction(Actions.forever(Actions.sequence(Actions.alpha(0.3f, 0.75f), Actions.alpha(1, 0.75f))));

        GameTimeController.getInstance().setTimeModifer(1);
    }

    @Override
    public void show()
    {
        if (!Main.localDataStorage.getBoolean(LocalStorageKeys.TUTORIAL_HIDED + GameMaster.getInstance().getGameType()))
            WindowFactory.createWindow(stage, WindowType.TUTORIAL);

    }

    @Override
    public void render(float delta)
    {
        GameMaster gm = GameMaster.getInstance();
        progressBar.setSize(380, 7);
        progressBar.setValue(gm.getOxygenProgress());

        float timeModifer = GameTimeController.getInstance().getTimeModifer();
        gm.updateGameTime(delta * timeModifer);
        world.update(delta * timeModifer);
        stage.act(delta);
        stage.draw();

        boolean screenIsActive = table.isTouchable();
        if (screenIsActive)
        {
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) && !gm.gameOver())
            {
                Main.game.pause();
            }
            if (gm.gameOver() && !startShowGameOver)
            {
                startShowGameOver = true;
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        WindowFactory.createWindow(stage, WindowType.GAME_OVER);
                    }
                }, 4);
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        WindowFactory.createWindow(stage, WindowType.PAUSE);
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose()
    {
        world.dispose();
        stage.dispose();
    }
}
