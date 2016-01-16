package ru.kiripu.lifeinspace.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by kiripu on 16.01.2016.
 */
public class CapsuleControlComponent implements Component, Pool.Poolable {
    public int capsuleExitKey;
    public Boolean isExiting;

    public CapsuleControlComponent init(int capsuleExitKey)
    {
        this.capsuleExitKey = capsuleExitKey;
        this.isExiting = false;
        return this;
    }
    @Override
    public void reset() {
       capsuleExitKey = 0;
       isExiting = false;
    }
}
