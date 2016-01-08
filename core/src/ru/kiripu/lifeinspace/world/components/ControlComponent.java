package ru.kiripu.lifeinspace.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by kiripu on 08.01.2016.
 */
public class ControlComponent implements Component, Pool.Poolable {
    public int turnRightKey;
    public int turnLeftKey;
    public int jetpackActivateKey;
    public float turnValue;
    public Vector2 forceVector = Vector2.Zero.cpy();

    @Override
    public void reset() {
        turnLeftKey = Input.Keys.UNKNOWN;
        turnLeftKey = Input.Keys.UNKNOWN;
        jetpackActivateKey = Input.Keys.UNKNOWN;
        turnValue = 0;
        forceVector.setZero();
    }

    public ControlComponent init(int turnRightKey, int turnLeftKey, int jetpackActivateKey, float turnValue, float force)
    {
        this.turnRightKey = turnRightKey;
        this.turnLeftKey = turnLeftKey;
        this.jetpackActivateKey = jetpackActivateKey;
        this.turnValue = turnValue;
        this.forceVector.x = force;
        return this;
    }
}
