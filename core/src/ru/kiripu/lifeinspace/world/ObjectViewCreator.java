package ru.kiripu.lifeinspace.world;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;
import ru.kiripu.lifeinspace.Main;
import ru.kiripu.lifeinspace.enums.GameObjectType;
import ru.kiripu.lifeinspace.world.components.TypeComponent;
import ru.kiripu.lifeinspace.world.components.ViewComponent;

/**
 * Created by kiripu on 14.01.2016.
 */
public class ObjectViewCreator
{
    public static ViewComponent createSpriteViewComponent(PooledEngine engine, TypeComponent typeComponent)
    {
        ViewComponent viewComponent = engine.createComponent(ViewComponent.class).init();
        String spriteName = "gameObjects/";

        switch (typeComponent.type)
        {
            case GameObjectType.TYPE_ASTEROID:
                spriteName += "asteroid_0" + typeComponent.variance;
                break;
            case GameObjectType.TYPE_SAFE_CAPSULE:
                spriteName += "safeCapsule";
                break;

        }

        Sprite sprite = Main.assetsController.getTextureAtlas().createSprite(spriteName);
        sprite.setOrigin(0, 0);

        addDataToViewComponent(viewComponent, sprite, true, null);
        viewComponent.mainSpriteIndex = 0;

        return viewComponent;
    }

    public static ViewComponent createPlayerView(PooledEngine engine, TypeComponent typeComponent)
    {
        ViewComponent viewComponent = engine.createComponent(ViewComponent.class).init();

        AnimatedSprite sprite = new AnimatedSprite(AnimationStorage.getInstance().
                getAnimation("oxygenRay", "idle", Animation.PlayMode.LOOP).animation);
        sprite.setOrigin(0, 0);
        addDataToViewComponent(viewComponent, sprite, false, new Vector2(35, 18));

        sprite = new AnimatedSprite(AnimationStorage.getInstance().
                getAnimation("hero", "idle", Animation.PlayMode.LOOP).animation);
        sprite.setOrigin(0, 0);
        addDataToViewComponent(viewComponent, sprite, true, null);

        viewComponent.mainSpriteIndex = 1;

        return viewComponent;
    }

    private static void addDataToViewComponent(ViewComponent viewComponent, Sprite sprite, Boolean visibility, Vector2 offset)
    {
        viewComponent.sprites.add(sprite);
        viewComponent.spritesVisibility.add(visibility);
        viewComponent.offset.add(offset);
    }
}
