package ru.kiripu.lifeinspace.windows;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import ru.kiripu.lifeinspace.Main;
import ru.kiripu.lifeinspace.factories.UIObjectFactory;
import ru.kiripu.lifeinspace.screens.MainMenu;

/**
 * Created by kiripu on 16.01.2016.
 */
public class PauseWindow {
    private final Table table;
    private static ClickListener inputListener;
    private final Image title;
    private final Button resumeButton;
    private final Button mainMenuButton;

    public PauseWindow(final Stage stage)
    {
        stage.getActors().peek().setTouchable(Touchable.disabled);
        table = new Table();
        table.setDebug(true);
        table.setFillParent(true);
        table.setSize(800, 600);
        table.setBackground(new SpriteDrawable(UIObjectFactory.createSprite("windowBack", null)));
        table.top();

        title = UIObjectFactory.createImage("pause_title");
        resumeButton = UIObjectFactory.createButton("resumeButton");
        mainMenuButton = UIObjectFactory.createButton("mainMenuButton");

        HorizontalGroup horizontalGroup = new HorizontalGroup();
        horizontalGroup.addActor(resumeButton);
        horizontalGroup.addActor(mainMenuButton);
        horizontalGroup.space(20);

        table.add(title).padTop(200).row();
        table.add(horizontalGroup).padTop(100);
        stage.addActor(table);

        inputListener = new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                Actor targetActor = event.getTarget();
                if (targetActor == resumeButton)
                {
                    table.remove();
                    stage.getActors().peek().setTouchable(Touchable.enabled);
                    Main.game.resume();
                }
                else if (targetActor == mainMenuButton)
                {
                    Main.game.getScreen().dispose();
                    Main.game.setScreen(new MainMenu());
                }
            }
        };

        table.addListener(inputListener);
    }
}
