package ru.kiripu.lifeinspace.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Pool;
import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;
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

    public SpriteComponent init(AnimationComponent animationComponent)
    {
        sprite = new AnimatedSprite(animationComponent.animation);
        sprite.setOrigin(0, 0);
        return this;
    }

}
