package ru.kiripu.lifeinspace.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

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

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        table.top();
        table.setDebug(true);

        title = new Image(new Texture("mainMenu/title.png"));

        Button.ButtonStyle style = new Button.ButtonStyle();
        style.up = new SpriteDrawable(new Sprite(new Texture("mainMenu/onePlayerButton_up.png")));
        style.down = new SpriteDrawable(new Sprite(new Texture("mainMenu/onePlayerButton_down.png")));
        style.over = new SpriteDrawable(new Sprite(new Texture("mainMenu/onePlayerButton_hover.png")));
        onePlayerButton = new Button(style);

        style = new Button.ButtonStyle();
        style.up = new SpriteDrawable(new Sprite(new Texture("mainMenu/twoPlayerButton_up.png")));
        style.down = new SpriteDrawable(new Sprite(new Texture("mainMenu/twoPlayerButton_down.png")));
        style.over = new SpriteDrawable(new Sprite(new Texture("mainMenu/twoPlayerButton_hover.png")));
        twoPlayersButton = new Button(style);

        style = new Button.ButtonStyle();
        style.up = new SpriteDrawable(new Sprite(new Texture("mainMenu/topSurvivors_up.png")));
        style.down = new SpriteDrawable(new Sprite(new Texture("mainMenu/topSurvivors_down.png")));
        style.over = new SpriteDrawable(new Sprite(new Texture("mainMenu/topSurvivors_hover.png")));
        topSurvivors = new Button(style);

        Image image = new Image(new Sprite(new Texture("mainMenu/topSurvivors_up.png")));

        table.add(title).padTop(150).row();
        table.add(image).spaceTop(200).row();
//        table.add(twoPlayersButton).spaceTop(50).row();
//        table.add(topSurvivors).spaceTop(50).row();
        stage.addActor(table);



    }

    @Override
    public void render(float delta)
    {
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
