package ru.kiripu.lifeinspace.world;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import ru.kiripu.lifeinspace.world.components.PhysicComponent;
import ru.kiripu.lifeinspace.world.components.SpriteComponent;
import ru.kiripu.lifeinspace.world.components.TransformComponent;

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
}
