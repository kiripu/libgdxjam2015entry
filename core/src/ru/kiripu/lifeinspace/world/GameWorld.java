package ru.kiripu.lifeinspace.world;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import ru.kiripu.lifeinspace.world.components.PhysicComponent;
import ru.kiripu.lifeinspace.world.components.PositionComponent;
import ru.kiripu.lifeinspace.world.systems.PhysicSystem;

/**
 * Created by kiripu on 06.01.2016.
 */
public class GameWorld
{
    private PooledEngine engine;

    public void init()
    {
        engine = new PooledEngine(0, 50, 0, 50);

        Entity testEntity = engine.createEntity();
        PositionComponent pos = engine.createComponent(PositionComponent.class).init(100, 100);
        PhysicComponent phys = engine.createComponent(PhysicComponent.class).init("asteroid_03");
        testEntity.add(pos);
        testEntity.add(phys);
        engine.addEntity(testEntity);

        PhysicSystem physicSystem = new PhysicSystem();
        engine.addSystem(physicSystem);
    }

    public void update(float deltaTime, OrthographicCamera camera)
    {
        engine.update(deltaTime);
    }
}
