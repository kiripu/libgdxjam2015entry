package ru.kiripu.lifeinspace.world;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Input;
import ru.kiripu.lifeinspace.enums.GameObjectType;
import ru.kiripu.lifeinspace.world.components.*;

/**
 * Created by kiripu on 07.01.2016.
 */
public class EntityFactory
{
    public static void createAsteroid(PooledEngine engine, String gameObjectType, float posX, float posY, float rot)
    {
        Entity entity = engine.createEntity();
        entity.add(engine.createComponent(TransformComponent.class).init(posX, posY, rot));
        entity.add(engine.createComponent(PhysicComponent.class).init(gameObjectType));
        entity.add(engine.createComponent(SpriteComponent.class).init(gameObjectType));
        engine.addEntity(entity);
    }

    public static void createPlayer(PooledEngine engine, float posX, float posY, float rot)
    {
        Entity player = engine.createEntity();
        player.add(engine.createComponent(TransformComponent.class).init(posX, posY, rot));
        player.add(engine.createComponent(PhysicComponent.class).init(GameObjectType.HERO));
        player.add(engine.createComponent(AnimationComponent.class).init(GameObjectType.HERO, "idle"));
        player.add(engine.createComponent(SpriteComponent.class).init(player.getComponent(AnimationComponent.class)));
        player.add(engine.createComponent(ControlComponent.class).
                init(Input.Keys.D, Input.Keys.A, Input.Keys.W, 1f, 10000));

        Entity jetpack = engine.createEntity();
        TransformComponent transformComponent = engine.createComponent(TransformComponent.class).init(35, 18, 0);
        transformComponent.setParent(player.getComponent(TransformComponent.class));
        jetpack.add(transformComponent);
        jetpack.add(engine.createComponent(AnimationComponent.class).init("oxygenRay", "idle"));
        jetpack.add(engine.createComponent(SpriteComponent.class).init(jetpack.getComponent(AnimationComponent.class)));

        engine.addEntity(jetpack);
        engine.addEntity(player);
    }

}
