package ru.kiripu.lifeinspace.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by kiripu on 06.01.2016.
 */
public class PhysicComponent implements Component, Pool.Poolable
{
    public Body body;
    public String bodyName;

    @Override
    public void reset()
    {
        bodyName = null;
        body = null;
    }

    public PhysicComponent init(String bodyName)
    {
        this.bodyName = bodyName;
        return this;
    }
}
