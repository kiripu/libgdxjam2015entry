package ru.kiripu.lifeinspace.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by kiripu on 06.01.2016.
 */
public class PhysicComponent implements Component, Pool.Poolable
{
    public Body body;
    public Vector2 startVelocity = Vector2.Zero;

    @Override
    public void reset()
    {
        body = null;
        startVelocity.setZero();
    }

    public PhysicComponent init(Vector2 startImpulse)
    {
        this.startVelocity = startImpulse;
        return this;
    }

    public PhysicComponent setBody(Body body)
    {
        this.body = body;
        return this;
    }
}
