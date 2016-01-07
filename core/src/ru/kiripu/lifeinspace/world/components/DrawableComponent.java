package ru.kiripu.lifeinspace.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by kiripu on 06.01.2016.
 */
public class DrawableComponent implements Component, Pool.Poolable
{
    public Drawable drawable;

    @Override
    public void reset() {

    }

    public DrawableComponent initWithSpriteName(String spriteName)
    {
//        drawable = Main.assetsController.getTextureAtlas().createSprite(spriteName);
        return this;
    }
}
