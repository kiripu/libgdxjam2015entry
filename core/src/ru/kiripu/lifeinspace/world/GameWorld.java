package ru.kiripu.lifeinspace.world;

import com.badlogic.ashley.core.PooledEngine;
import ru.kiripu.lifeinspace.enums.GameObjectType;
import ru.kiripu.lifeinspace.world.systems.JetpackControlSystem;
import ru.kiripu.lifeinspace.world.systems.PhysicSystem;
import ru.kiripu.lifeinspace.world.systems.RenderSystem;
import ru.kiripu.lifeinspace.world.systems.TurnControlSystem;

/**
 * Created by kiripu on 06.01.2016.
 */
public class GameWorld
{
    private PooledEngine engine;

    public void init()
    {
        engine = new PooledEngine(0, 50, 0, 50);
        engine.addSystem(new RenderSystem());
        engine.addSystem(new JetpackControlSystem());
        engine.addSystem(new TurnControlSystem());
        engine.addSystem(new PhysicSystem());
        EntityFactory.createAsteroid(engine, GameObjectType.ASTEROID_3, 500, 200, 35);
        EntityFactory.createSafeCapsule(engine, 150, 200, 0);
        EntityFactory.createPlayer(engine, 150, 400, 45);
    }

    public void update(float deltaTime)
    {
        engine.update(deltaTime);
    }
}
