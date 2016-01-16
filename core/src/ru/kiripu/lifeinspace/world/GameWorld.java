package ru.kiripu.lifeinspace.world;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Input;
import ru.kiripu.lifeinspace.enums.GameType;
import ru.kiripu.lifeinspace.managers.GameMaster;
import ru.kiripu.lifeinspace.managers.ObstaclesCreator;
import ru.kiripu.lifeinspace.world.systems.*;

/**
 * Created by kiripu on 06.01.2016.
 */
public class GameWorld
{
    private PooledEngine engine;
    private ObstaclesCreator obstaclesCreator;

    public void init()
    {
        engine = new PooledEngine(0, 50, 0, 50);
        engine.addSystem(new RenderSystem());
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new JetpackControlSystem());
        engine.addSystem(new TurnControlSystem());
        engine.addSystem(new CapsuelControlSystem());
        engine.addSystem(new PhysicSystem());
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new OxygenSystem());

        if (GameMaster.getInstance().getGameType() == GameType.SINGLE) createSingleGame();
        else if (GameMaster.getInstance().getGameType() == GameType.COOP) createCoopGame();

        obstaclesCreator = new ObstaclesCreator();
        obstaclesCreator.init(engine);
    }

    public void update(float deltaTime)
    {
        engine.update(deltaTime);
        obstaclesCreator.update(deltaTime);
    }

    public void dispose()
    {
        engine.removeAllEntities();
        engine.clearPools();
        engine = null;
    }

    private void createSingleGame()
    {
        EntityFactory.createPlayer(engine, 300, 300, 0,
                Input.Keys.UP, Input.Keys.RIGHT, Input.Keys.LEFT);
    }

    private void createCoopGame()
    {
        EntityFactory.createPlayer(engine, 300, 300, 180,
                Input.Keys.W, Input.Keys.D, Input.Keys.A);
        EntityFactory.createPlayer(engine, 500, 300, 0,
                Input.Keys.UP, Input.Keys.RIGHT, Input.Keys.LEFT);
    }
}
