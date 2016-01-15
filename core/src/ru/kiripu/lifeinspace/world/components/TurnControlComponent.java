package ru.kiripu.lifeinspace.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by kiripu on 08.01.2016.
 */
public class TurnControlComponent implements Component, Pool.Poolable {
    public int turnRightKey;
    public int turnLeftKey;
    public float turnValue;

    @Override
    public void reset() {
        turnLeftKey = Input.Keys.UNKNOWN;
        turnLeftKey = Input.Keys.UNKNOWN;
        turnValue = 0;
    }

    public TurnControlComponent init(int turnRightKey, int turnLeftKey, float turnValue)
    {
        this.turnRightKey = turnRightKey;
        this.turnLeftKey = turnLeftKey;
        this.turnValue = turnValue;
        return this;
    }
}
