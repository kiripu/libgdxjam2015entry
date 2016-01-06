package ru.kiripu.lifeinspace.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by kiripu on 06.01.2016.
 */
public class PositionComponent implements Component, Pool.Poolable
{
    public float x = 0f;
    public float y = 0f;

    public PositionComponent init(float x, float y)
    {
        this.x = x;
        this.y = y;
        return this;
    }

    @Override
    public void reset()
    {
        x = 0f;
        y = 0f;
    }
}
