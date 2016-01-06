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
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;
import ru.kiripu.lifeinspace.Main;

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

    private Animation heroAnim;
    private Image heroImage;
    private AnimatedSprite heroAnimSprite;
    private InputListener inputListener;

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

        title = new Image(atlas.createSprite("mainMenu/title"));

        Button.ButtonStyle style = new Button.ButtonStyle();
        style.up = new SpriteDrawable(atlas.createSprite("mainMenu/onePlayerButton_up"));
        style.down = new SpriteDrawable(atlas.createSprite("mainMenu/onePlayerButton_down"));
        style.over = new SpriteDrawable(atlas.createSprite("mainMenu/onePlayerButton_hover"));
        onePlayerButton = new Button(style);

        style = new Button.ButtonStyle();
        style.up = new SpriteDrawable(atlas.createSprite("mainMenu/twoPlayerButton_up"));
        style.down = new SpriteDrawable(atlas.createSprite("mainMenu/twoPlayerButton_down"));
        style.over = new SpriteDrawable(atlas.createSprite("mainMenu/twoPlayerButton_hover"));
        twoPlayersButton = new Button(style);

        style = new Button.ButtonStyle();
        style.up = new SpriteDrawable(atlas.createSprite("mainMenu/topSurvivors_up"));
        style.down = new SpriteDrawable(atlas.createSprite("mainMenu/topSurvivors_down"));
        style.over = new SpriteDrawable(atlas.createSprite("mainMenu/topSurvivors_hover"));
        topSurvivors = new Button(style);

        heroAnim = new Animation(0.03f, atlas.findRegions("hero/idle"));
        heroAnim.setPlayMode(Animation.PlayMode.LOOP);
        heroAnimSprite = new AnimatedSprite(heroAnim);
        heroAnimSprite.setAutoUpdate(true);
        heroAnimSprite.play();
        heroImage = new Image(heroAnimSprite);
        heroImage.setOrigin(Align.center);
        heroImage.addAction(Actions.forever(Actions.rotateBy(-30f, 1f)));

        table.add(title).padTop(160).row();
        table.add(heroImage).padTop(60).row();
        table.add(onePlayerButton).padTop(50).row();
        table.add(twoPlayersButton).padTop(20).row();
        table.add(topSurvivors).padTop(20).row();
        stage.addActor(table);

        inputListener = new ClickListener()
        {
            public void clicked(InputEvent event, float x, float y)
            {
                Actor targetActor = event.getTarget();
                if (targetActor == onePlayerButton)
                {
                    System.out.println("onePlayerButton");
                    Main.game.setScreen(new GameWorldScreen());
                }
                else if (targetActor == twoPlayersButton) System.out.println("twoPlayersButton");
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
