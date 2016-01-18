package ru.kiripu.lifeinspace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;
import ru.kiripu.lifeinspace.Main;
import ru.kiripu.lifeinspace.enums.BitmapFonts;
import ru.kiripu.lifeinspace.enums.GameType;
import ru.kiripu.lifeinspace.enums.LocalStorageKeys;
import ru.kiripu.lifeinspace.factories.UIObjectFactory;
import ru.kiripu.lifeinspace.managers.GameMaster;
import ru.kiripu.lifeinspace.utils.TimeStringGenerator;

/**
 * Created by kiripu on 26.12.2015.
 */
public class MainMenu implements Screen
{
    private Stage stage;
    private Table table;
    private Image title;
    private Button onePlayerButton;
    private Button twoPlayersButton;
    private Button topSurvivors;
    private Button soundButton;

    private Animation heroAnim;
    private Image heroImage;
    private AnimatedSprite heroAnimSprite;
    private InputListener inputListener;
    private Image onePlayerRecordStar;
    private Image twoPlayersRecordStar;
    private TextField onePlayerRecordText;
    private TextField twoPlayersRecordText;

    @Override
    public void show()
    {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.top();
//        table.setDebug(true);

        TextureAtlas atlas  = Main.assetsController.getTextureAtlas();

        title = UIObjectFactory.createImage("title_MainMenu");
        onePlayerRecordStar = UIObjectFactory.createImage("star");
        twoPlayersRecordStar = UIObjectFactory.createImage("star");
        onePlayerRecordStar.setOrigin(7.5f, 7.5f);
        twoPlayersRecordStar.setOrigin(7.5f, 7.5f);
        onePlayerRecordStar.addAction(Actions.forever(Actions.rotateBy(-60f, 1f)));
        twoPlayersRecordStar.addAction(Actions.forever(Actions.rotateBy(-60f, 1f)));
        onePlayerRecordText = UIObjectFactory.createTextField("", BitmapFonts.OLIVER, 20);
        twoPlayersRecordText = UIObjectFactory.createTextField("", BitmapFonts.OLIVER, 20);

        int onePlayerRecordValue = Main.localDataStorage.getInt(LocalStorageKeys.GAME_TIME + GameType.SINGLE);
        int twoPlayersRecordValue = Main.localDataStorage.getInt(LocalStorageKeys.GAME_TIME + GameType.COOP);

        onePlayerButton = UIObjectFactory.createButton("onePlayerButton");
        twoPlayersButton = UIObjectFactory.createButton("twoPlayerButton");
        topSurvivors = UIObjectFactory.createButton("topSurvivors");
        soundButton = UIObjectFactory.createButton("soundButton");

        heroAnim = new Animation(0.03f, atlas.findRegions("hero/idle"));
        heroAnim.setPlayMode(Animation.PlayMode.LOOP);
        heroAnimSprite = new AnimatedSprite(heroAnim);
        heroAnimSprite.setAutoUpdate(true);
        heroAnimSprite.play();
        heroImage = new Image(heroAnimSprite);
        heroImage.setOrigin(Align.center);
        heroImage.addAction(Actions.forever(Actions.rotateBy(-30f, 1f)));

        HorizontalGroup onePlayerGroup = new HorizontalGroup();
        onePlayerGroup.addActor(onePlayerButton);
        if (onePlayerRecordValue > 0)
        {
            onePlayerRecordText.setText(TimeStringGenerator.generateTimeString(onePlayerRecordValue));
            onePlayerGroup.addActor(onePlayerRecordStar);
            onePlayerGroup.addActor(onePlayerRecordText);
        }
        onePlayerGroup.space(10);

        HorizontalGroup twoPlayersGroup = new HorizontalGroup();
        twoPlayersGroup.addActor(twoPlayersButton);
        if (twoPlayersRecordValue > 0)
        {
            twoPlayersRecordText.setText(TimeStringGenerator.generateTimeString(twoPlayersRecordValue));
            twoPlayersGroup.addActor(twoPlayersRecordStar);
            twoPlayersGroup.addActor(twoPlayersRecordText);
        }
        twoPlayersGroup.space(10);

        table.add(title).padTop(160).row();
        table.add(heroImage).padTop(60).row();
        table.add(onePlayerGroup).padTop(50).left().padLeft(200).row();
        table.add(twoPlayersGroup).padTop(20).left().padLeft(200).row();
        table.add(topSurvivors).padTop(20).row();
        table.add(soundButton).expand().bottom().right().pad(10);
        stage.addActor(table);

        inputListener = new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                Actor targetActor = event.getTarget();
                if (targetActor == onePlayerButton)
                {
                    GameMaster.getInstance().init(GameType.SINGLE);
                    Main.game.setScreen(new GameWorldScreen());
                }
                else if (targetActor == twoPlayersButton)
                {
                    GameMaster.getInstance().init(GameType.COOP);
                    Main.game.setScreen(new GameWorldScreen());
                }
                else if (targetActor == topSurvivors) System.out.println("topSurvivors");
            }
        };

        onePlayerButton.addListener(inputListener);
        twoPlayersButton.addListener(inputListener);
        topSurvivors.addListener(inputListener);
    }

    @Override
    public void render(float delta)
    {
        heroAnimSprite.update(delta);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height)
    {
        stage.getViewport().update(width, height, true);
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
