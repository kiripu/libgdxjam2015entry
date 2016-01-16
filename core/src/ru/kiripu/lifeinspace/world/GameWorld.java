package ru.kiripu.lifeinspace.world;

import com.badlogic.ashley.core.PooledEngine;
import ru.kiripu.lifeinspace.world.systems.*;

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
        engine.addSystem(new CapsuelControlSystem());
        engine.addSystem(new PhysicSystem());
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new OxygenSystem());

        EntityFactory.createPlayer(engine, 180, 250, 90);
        EntityFactory.createAsteroid(engine, 1, 100, 200, 35);
        EntityFactory.createSafeCapsule(engine, 150, 200, 16);
    }

    public void update(float deltaTime)
    {
        engine.update(deltaTime);
    }
}
