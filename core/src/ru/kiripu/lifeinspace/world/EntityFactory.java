package ru.kiripu.lifeinspace.world;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Input;
import ru.kiripu.lifeinspace.enums.GameObjectType;
import ru.kiripu.lifeinspace.world.components.ControlComponent;
import ru.kiripu.lifeinspace.world.components.PhysicComponent;
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
        entity.add(ObjectViewCreator.createAsteroidView(engine, gameObjectType));
        engine.addEntity(entity);
    }

    public static void createPlayer(PooledEngine engine, float posX, float posY, float rot)
    {
        Entity entity = engine.createEntity();
        entity.add(engine.createComponent(TransformComponent.class).init(posX, posY, rot));
        entity.add(engine.createComponent(PhysicComponent.class).init(GameObjectType.HERO));
        entity.add(ObjectViewCreator.createPlayerView(engine, "asdasd"));
        entity.add(engine.createComponent(ControlComponent.class).
                init(Input.Keys.D, Input.Keys.A, Input.Keys.W, 1f, 10000));
        engine.addEntity(entity);
    }
}
