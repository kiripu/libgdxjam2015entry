package ru.kiripu.lifeinspace.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by kiripu on 14.01.2016.
 */
public class JetpackControlComponent implements Component, Pool.Poolable {

    public boolean jetpackIsActive;
    public int jetpackActivateKey;
    public Vector2 forceVector = Vector2.Zero.cpy();

    public JetpackControlComponent init(int jetpackActivateKey, float force)
    {
        this.jetpackActivateKey = jetpackActivateKey;
        this.forceVector.x = force;
        this.jetpackIsActive = false;
        return this;
    }

    @Override
    public void reset() {
        jetpackActivateKey = -1;
        forceVector.setZero();
        jetpackIsActive = false;
    }
}
