package ru.kiripu.lifeinspace.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by kiripu on 16.01.2016.
 */
public class CollisionComponent implements Component, Pool.Poolable
{
    public Entity entity;
    public Boolean isBegin;

    public CollisionComponent init(Entity entity, Boolean isBegin)
    {
        this.entity = entity;
        this.isBegin = isBegin;
        return this;
    }

    @Override
    public void reset() {
        entity = null;
    }
}
