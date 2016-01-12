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
    public TransformComponent parent;

    public TransformComponent init(float x, float y, float rot)
    {
        position = new Vector2(x, y);
        origin = Vector2.Zero;
        this.rotation = rot;
        return this;
    }

    public void setParent(TransformComponent parent)
    {
        this.parent = parent;
    }

    public float getPositionX()
    {
        if (parent != null)
        {
            return (float) (parent.position.x + position.len() * Math.cos(parent.rotation * MathUtils.degreesToRadians + position.angleRad()));
        }
        else return position.x;
    }

    public float getPositionY()
    {
        if (parent != null)
        {
            return (float) (parent.position.y + position.len() * Math.sin(parent.rotation * MathUtils.degreesToRadians + position.angleRad()));
        }
        else return position.y;
    }

    public float getOriginX()
    {
        return origin.x;
    }

    public float getOriginY()
    {
        return origin.y;
    }

    public float getRotation()
    {
        if (parent != null) return rotation + parent.rotation;
        else return rotation;
    }

    @Override
    public void reset()
    {
        position.setZero();
        origin.setZero();
        rotation = 0f;
        parent = null;
    }
}
