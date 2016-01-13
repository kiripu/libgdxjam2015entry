package ru.kiripu.lifeinspace.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by kiripu on 13.01.2016.
 */
public class ViewComponent implements Component, Pool.Poolable
{
    public int mainSpriteIndex;
    public Array<Sprite> sprites;
    public Array<Boolean> spritesVisibility;
    public Array<Vector2> offset;

    public ViewComponent init()
    {
        sprites = new Array<Sprite>();
        spritesVisibility = new Array<Boolean>();
        offset = new Array<Vector2>();
        return this;
    }

    public Sprite getMainSprite()
    {
        return sprites.get(mainSpriteIndex);
    }


    @Override
    public void reset() {

    }
}
