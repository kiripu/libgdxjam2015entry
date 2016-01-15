package ru.kiripu.lifeinspace.factories;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
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
        Image image = new Image(getAtlas().createSprite(UI_PATH + name));
        image.setName(name);
        return image;
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

    private static TextureAtlas getAtlas()
    {
        if (textureAtlas == null) textureAtlas = Main.assetsController.getTextureAtlas();
        return textureAtlas;
    }
}
