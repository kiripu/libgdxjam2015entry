package ru.kiripu.lifeinspace.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Pool;
import ru.kiripu.lifeinspace.Main;

/**
 * Created by kiripu on 07.01.2016.
 */
public class SpriteComponent implements Component, Pool.Poolable
{
    public Sprite sprite;

    @Override
    public void reset()
    {
        sprite.notify();
    }

    public SpriteComponent init(String spriteName)
    {
        sprite = Main.assetsController.getTextureAtlas().createSprite("gameObjects/" + spriteName);
        sprite.setOrigin(0, 0);
        return this;
    }

}
