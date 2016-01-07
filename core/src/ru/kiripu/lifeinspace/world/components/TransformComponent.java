package ru.kiripu.lifeinspace.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by kiripu on 06.01.2016.
 */
public class TransformComponent implements Component, Pool.Poolable
{
    public Vector2 position;
    public Vector2 origin;
    public float rotation = 0f;

    public TransformComponent init(float x, float y, float rot)
    {
        position = new Vector2(x, y);
        origin = Vector2.Zero;
        this.rotation = rot;
        return this;
    }

    @Override
    public void reset()
    {
        position.setZero();
        origin.setZero();
        rotation = 0f;
    }
}
