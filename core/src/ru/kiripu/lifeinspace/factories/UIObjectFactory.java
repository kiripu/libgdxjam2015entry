package ru.kiripu.lifeinspace.factories;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import ru.kiripu.lifeinspace.Main;

/**
 * Created by kiripu on 16.01.2016.
 */
public class UIObjectFactory
{
    private static final String UI_PATH = "ui/";
    private static final String BUTTON_STATE_UP = "_up";
    private static final String BUTTON_STATE_DOWN = "_down";
    private static final String BUTTON_STATE_HOVER = "_hover";
    private static final String PROGRESS_BAR_BACKGROUND = "_back";
    private static final String PROGRESS_BAR_FILL = "_fill";
    private static TextureAtlas textureAtlas;


    public static Button createButton(String name)
    {
        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = new SpriteDrawable(getAtlas().createSprite(UI_PATH + name + BUTTON_STATE_UP));
        buttonStyle.down = new SpriteDrawable(getAtlas().createSprite(UI_PATH + name + BUTTON_STATE_DOWN));
        buttonStyle.over = new SpriteDrawable(getAtlas().createSprite(UI_PATH + name + BUTTON_STATE_HOVER));
        Button button = new Button(buttonStyle);
        button.setName(name);
        return button;
    }

    public static Image createImage(String name)
    {
        Image image = new Image(createSprite(name, UI_PATH));
        image.setName(name);
        return image;
    }

    public static Sprite createSprite(String name, String path)
    {
        path = (path == null) ? UI_PATH : path;
        return getAtlas().createSprite(path + name);
    }

    public static ProgressBar createProgressBar(String name)
    {
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
        progressBarStyle.background = new SpriteDrawable(getAtlas().createSprite(UI_PATH + name + PROGRESS_BAR_BACKGROUND));
        progressBarStyle.knobBefore = new SpriteDrawable(getAtlas().createSprite(UI_PATH + name + PROGRESS_BAR_FILL));
        ProgressBar progressBar = new ProgressBar(0, 100, 0.5f, false, progressBarStyle);
        progressBar.setValue(0);
        progressBar.setName(name);
        return progressBar;
    }

    public static TextField createTextField(String text, BitmapFont bitmapFont, int size)
    {
        float lineHeight = bitmapFont.getData().lineHeight;
        if (Math.abs(lineHeight - size) > 1)
        {
            float scale = size / bitmapFont.getData().lineHeight;
            bitmapFont.getData().setScale(scale);
        }

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = bitmapFont;
        textFieldStyle.fontColor = Color.WHITE;
        TextField textField = new TextField(text, textFieldStyle);
        return textField;
    }

    public static Label createLabel(String text, BitmapFont bitmapFont, int size)
    {
        float lineHeight = bitmapFont.getData().lineHeight;
        if (Math.abs(lineHeight - size) > 1)
        {
            float scale = size / bitmapFont.getData().lineHeight;
            bitmapFont.getData().setScale(scale);
        }

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        labelStyle.fontColor = Color.WHITE;

        Label label = new Label(text, labelStyle);
        return label;
    }



    private static TextureAtlas getAtlas()
    {
        if (textureAtlas == null) textureAtlas = Main.assetsController.getTextureAtlas();
        return textureAtlas;
    }
}
