package ru.kiripu.lifeinspace.factories;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
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
    private static TextureAtlas textureAtlas;


    public static Button createButton(String buttonName)
    {
        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = new SpriteDrawable(getAtlas().createSprite(UI_PATH + buttonName + BUTTON_STATE_UP));
        buttonStyle.down = new SpriteDrawable(getAtlas().createSprite(UI_PATH + buttonName + BUTTON_STATE_DOWN));
        buttonStyle.over = new SpriteDrawable(getAtlas().createSprite(UI_PATH + buttonName + BUTTON_STATE_HOVER));
        Button button = new Button(buttonStyle);
        button.setName(buttonName);
        return button;
    }

    private static TextureAtlas getAtlas()
    {
        if (textureAtlas == null) textureAtlas = Main.assetsController.getTextureAtlas();
        return textureAtlas;
    }
}
