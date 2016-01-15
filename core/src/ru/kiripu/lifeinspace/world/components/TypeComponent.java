package ru.kiripu.lifeinspace.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;
import ru.kiripu.lifeinspace.enums.GameObjectType;

/**
 * Created by kiripu on 15.01.2016.
 */
public class TypeComponent implements Component, Pool.Poolable {

    public int type;
    public int variance;

    public TypeComponent init(int type, int variance)
    {
        this.type = type;
        this.variance = variance;
        return this;
    }

    public TypeComponent init(int type)
    {
        this.type = type;
        this.variance = GameObjectType.TYPE_EMPTY;
        return this;
    }

    @Override
    public void reset()
    {
        type = GameObjectType.TYPE_EMPTY;
        variance = GameObjectType.TYPE_EMPTY;
    }
}
