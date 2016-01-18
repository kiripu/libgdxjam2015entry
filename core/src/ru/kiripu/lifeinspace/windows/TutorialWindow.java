package ru.kiripu.lifeinspace.windows;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import ru.kiripu.lifeinspace.Main;
import ru.kiripu.lifeinspace.enums.LocalStorageKeys;
import ru.kiripu.lifeinspace.factories.UIObjectFactory;
import ru.kiripu.lifeinspace.managers.GameMaster;
import ru.kiripu.lifeinspace.managers.GameTimeController;

/**
 * Created by kiripu on 16.01.2016.
 */
public class TutorialWindow {
    private final Table table;
    private static ClickListener inputListener;
    private final Image tutorialImage;
    private final Button playButton;
    private final Button doNotShowCheckbox;

    public TutorialWindow(final Stage stage)
    {
        GameTimeController.getInstance().setTimeModifer(0);
        stage.getActors().peek().setTouchable(Touchable.disabled);
        table = new Table();
        table.setFillParent(true);
        table.setBackground(new SpriteDrawable(UIObjectFactory.createSprite("tutorialBack", null)));
        table.center();

        tutorialImage = UIObjectFactory.createImage("tutorialImage");
        playButton = UIObjectFactory.createButton("playButton");
        doNotShowCheckbox = UIObjectFactory.createCheckButton("doNotShowCheckbox");

        table.add(tutorialImage).row();
        table.add(playButton).padTop(20).row();
        table.add(doNotShowCheckbox).padTop(20).row();
        stage.addActor(table);

        boolean checkboxHided = Main.localDataStorage.getBoolean(
                LocalStorageKeys.TUTORIAL_HIDED + GameMaster.getInstance().getGameType());
        doNotShowCheckbox.setVisible(!checkboxHided);

        inputListener = new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                Actor targetActor = event.getTarget();
                if (targetActor == playButton)
                {
                    if (doNotShowCheckbox.isChecked())
                    {
                        Main.localDataStorage.saveBoolean(
                                LocalStorageKeys.TUTORIAL_HIDED + GameMaster.getInstance().getGameType(), true);
                    }
                    table.remove();
                    stage.getActors().peek().setTouchable(Touchable.enabled);
                    Main.game.resume();
                    GameTimeController.getInstance().setTimeModifer(1);
                }
            }
        };

        table.addListener(inputListener);
    }
}
