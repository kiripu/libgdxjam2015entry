package ru.kiripu.lifeinspace.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by kiripu on 16.01.2016.
 */
public class OxygenComponent implements Component, Pool.Poolable {
    public float curOxygenValue;
    public float maxOxygenValue;
    public float oxygenChangeSpeed;

    public OxygenComponent init(float oxygenValue, float oxygenChangeSpeed)
    {
        this.curOxygenValue = oxygenValue;
        this.maxOxygenValue = oxygenValue;
        this.oxygenChangeSpeed = oxygenChangeSpeed;
        return this;
    }

    @Override
    public void reset() {
        curOxygenValue = 0;
        maxOxygenValue = 0;
        oxygenChangeSpeed = 0;
    }
}
