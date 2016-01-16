package ru.kiripu.lifeinspace.world;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Input;
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

        EntityFactory.createPlayer(
                engine,
                300, 300, 180,
                Input.Keys.W, Input.Keys.D, Input.Keys.A);
        EntityFactory.createPlayer(
                engine,
                500, 300, 0,
                Input.Keys.UP, Input.Keys.RIGHT, Input.Keys.LEFT);
        EntityFactory.createAsteroid(engine, 1, 100, 200, 35);
        EntityFactory.createSafeCapsule(engine, 400, 300, 16);
    }

    public void update(float deltaTime)
    {
        engine.update(deltaTime);
    }
}
