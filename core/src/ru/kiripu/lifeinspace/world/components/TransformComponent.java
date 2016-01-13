package ru.kiripu.lifeinspace.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.MathUtils;
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

    public float getPositionX(Vector2 offset)
    {
        if (offset != null)
        {
            return (float) (position.x + offset.len() * Math.cos(rotation * MathUtils.degreesToRadians + offset.angleRad()));
        }
        else return position.x;
    }

    public float getPositionY(Vector2 offset)
    {
        if (offset != null)
        {
            return (float) (position.y + offset.len() * Math.sin(rotation * MathUtils.degreesToRadians + offset.angleRad()));
        }
        else return position.y;
    }

    public float getRotation()
    {
        return rotation;
    }

    @Override
    public void reset()
    {
        position.setZero();
        origin.setZero();
        rotation = 0f;
    }
}
