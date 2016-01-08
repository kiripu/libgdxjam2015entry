package ru.kiripu.lifeinspace.world;

import com.badlogic.ashley.core.PooledEngine;
import ru.kiripu.lifeinspace.enums.GameObjectType;
import ru.kiripu.lifeinspace.world.systems.ControlSystem;
import ru.kiripu.lifeinspace.world.systems.PhysicSystem;
import ru.kiripu.lifeinspace.world.systems.RenderSystem;

/**
 * Created by kiripu on 06.01.2016.
 */
public class GameWorld
{
    private PooledEngine engine;

    public void init()
    {
        engine = new PooledEngine(0, 50, 0, 50);
//        engine.addSystem(new AnimationSystem());
        engine.addSystem(new RenderSystem());
        engine.addSystem(new ControlSystem());
        engine.addSystem(new PhysicSystem());
        EntityFactory.createAsteroid(engine, GameObjectType.ASTEROID_3, 500, 200, 35);
        EntityFactory.createAsteroid(engine, GameObjectType.ASTEROID_1, 150, 200, 0);
        EntityFactory.createPlayer(engine, 150, 400, 45);
    }

    public void update(float deltaTime)
    {
        engine.update(deltaTime);
    }
}
