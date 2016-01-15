package ru.kiripu.lifeinspace.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by kiripu on 14.01.2016.
 */
public class EnergyComponent implements Component, Pool.Poolable
{
    public float energy;
    public float jetpackUseDecreaseSpeed;
    public float damageDecreaseValue;
    public float safeCapsuleEncreaseSpeed;
    public float coopEncreaseSpeed;

    public EnergyComponent init(float energyValue,
                                float jetpackUseDecreaseSpeed,
                                float damageDecreaseValue,
                                float safeCapsuleEncreaseSpeed,
                                float coopEncreaseSpeed)
    {
        this.energy = energyValue;
        this.jetpackUseDecreaseSpeed = jetpackUseDecreaseSpeed;
        this.damageDecreaseValue = damageDecreaseValue;
        this.safeCapsuleEncreaseSpeed = safeCapsuleEncreaseSpeed;
        this.coopEncreaseSpeed = coopEncreaseSpeed;
        return this;
    }

    @Override
    public void reset() {

    }
}
