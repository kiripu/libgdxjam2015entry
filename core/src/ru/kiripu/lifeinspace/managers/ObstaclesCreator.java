package ru.kiripu.lifeinspace.managers;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ru.kiripu.lifeinspace.world.EntityFactory;

/**
 * Created by kiripu on 17.01.2016.
 */
public class ObstaclesCreator
{
    private static final int TOP = 0;
    private static final int BOTTOM = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;


    private PooledEngine engine;
    private float curTime;
    private float spawnTime;

    public void init(PooledEngine engine)
    {
        this.engine = engine;
        curTime = 0;
        spawnTime = 0.1f;
    }

    public void update(float deltaTime)
    {
        curTime += deltaTime;
        if (curTime >= spawnTime)
        {
            generateNextSpawnDelay();
            spawn();
        }
    }

    private void generateNextSpawnDelay()
    {
        spawnTime = curTime + 3;
    }

    private void spawn()
    {
        int asteroidsToSpawn = 5;
        int safeCapsuleToSpawn = 1;
        int i;
        for (i = 0; i < asteroidsToSpawn; i++) spawnAsteroid();
        for (i = 0; i < safeCapsuleToSpawn; i++) spawnSafeCapsule();
    }

    private void spawnAsteroid()
    {
        int spawnSide = generateSpawnSide();
        Vector2 pos = generateSpawnPosition(spawnSide);
        Vector2 impulse = generateStartVelocity(spawnSide);
        EntityFactory.createAsteroid(engine, MathUtils.random(1, 4), pos, impulse, 0);
    }

    private void spawnSafeCapsule()
    {
        int spawnSide = generateSpawnSide();
        Vector2 pos = generateSpawnPosition(spawnSide);
        Vector2 impulse = generateStartVelocity(spawnSide);
        EntityFactory.createSafeCapsule(engine, pos, impulse, 0);
    }

    private Vector2 generateSpawnPosition(int spawnSide)
    {
        Vector2 pos = new Vector2();
        switch (spawnSide)
        {
            case TOP: pos.x = MathUtils.random(100, 700); pos.y = 650; break;
            case BOTTOM: pos.x = MathUtils.random(100, 700); pos.y = -50; break;
            case LEFT: pos.x = 850; pos.y = MathUtils.random(100, 500); break;
            case RIGHT: pos.x = -50; pos.y = MathUtils.random(100, 500); break;
        }
        return pos;
    }

    private Vector2 generateStartVelocity(int spawnSide)
    {
        Vector2 velocityVector = new Vector2();
        float velocityValue = 10;
        switch (spawnSide)
        {
            case TOP: velocityVector.y = -velocityValue; break;
            case BOTTOM: velocityVector.y = velocityValue; break;
            case LEFT: velocityVector.x = -velocityValue; break;
            case RIGHT: velocityVector.x = velocityValue; break;
        }
        velocityVector.rotate(MathUtils.random(-30, 30));
        return velocityVector;
    }

    private int generateSpawnSide() { return MathUtils.random(0, 3);}
}
