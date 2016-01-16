package ru.kiripu.lifeinspace.windows;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import ru.kiripu.lifeinspace.Main;
import ru.kiripu.lifeinspace.enums.BitmapFonts;
import ru.kiripu.lifeinspace.factories.UIObjectFactory;
import ru.kiripu.lifeinspace.screens.GameWorldScreen;
import ru.kiripu.lifeinspace.screens.MainMenu;

/**
 * Created by kiripu on 16.01.2016.
 */
public class GameOverWindow {
    private final Table table;
    private static ClickListener inputListener;
    private final Image title;
    private final Label timeLabel;
    private final Image record;
    private final Label enterNameLabel;
    private final TextField enterNameField;
    private final Button playAgainButton;
    private final Button mainMenuButton;

    public GameOverWindow(Stage stage)
    {
        stage.getActors().peek().setTouchable(Touchable.disabled);
        table = new Table();
        table.setFillParent(true);
        table.setSize(800, 600);
        table.setBackground(new SpriteDrawable(UIObjectFactory.createSprite("windowBack", null)));
        table.center();
        table.padTop(200);

        title = UIObjectFactory.createImage("youDied_label");
        timeLabel = UIObjectFactory.createLabel("You survived: 00.20", BitmapFonts.OLIVER, 20);
        record = UIObjectFactory.createImage("newRecord_label");
        enterNameLabel = UIObjectFactory.createLabel("Enter your name:", BitmapFonts.OLIVER, 20);
        enterNameField = UIObjectFactory.createTextField("ASDAS", BitmapFonts.OLIVER, 20);
        playAgainButton = UIObjectFactory.createButton("playAgainButton");
        mainMenuButton = UIObjectFactory.createButton("mainMenuButton");

        HorizontalGroup horizontalGroup = new HorizontalGroup();
        horizontalGroup.addActor(playAgainButton);
        horizontalGroup.addActor(mainMenuButton);
        horizontalGroup.space(20);

        table.add(title).width(800).row();
        table.add(timeLabel).padTop(20).row();
        table.add(record).padTop(20).row();
        table.add(enterNameLabel).padTop(20).row();
        table.add(enterNameField).padTop(20).row();
        table.add(horizontalGroup).pad(20);
        stage.addActor(table);

        inputListener = new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                Actor targetActor = event.getTarget();
                if (targetActor == playAgainButton)
                {
                    Main.game.setScreen(new GameWorldScreen());
                }
                else if (targetActor == mainMenuButton)
                {
                    Main.game.setScreen(new MainMenu());
                }
            }
        };

        table.addListener(inputListener);
    }
}
