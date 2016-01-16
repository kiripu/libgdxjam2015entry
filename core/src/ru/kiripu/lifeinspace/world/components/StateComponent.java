package ru.kiripu.lifeinspace.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by kiripu on 16.01.2016.
 */
public class StateComponent implements Component, Pool.Poolable {
    public int state;

    public StateComponent init(int state)
    {
        this.state = state;
        return this;
    }

    @Override
    public void reset() {

    }
}
