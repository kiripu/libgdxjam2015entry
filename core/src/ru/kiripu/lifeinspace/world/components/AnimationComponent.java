package ru.kiripu.lifeinspace.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import ru.kiripu.lifeinspace.world.data.AnimationData;

/**
 * Created by kiripu on 16.01.2016.
 */
public class AnimationComponent implements Component, Pool.Poolable
{
    public Array<AnimationData> animationDatas = new Array<AnimationData>();
    public boolean changed;

    public AnimationComponent init(AnimationData animationData)
    {
        animationDatas.add(animationData);
        changed = true;
        return this;
    }

    public void change(Array<AnimationData> animationDatas)
    {
        this.animationDatas.clear();
        this.animationDatas = animationDatas;
        changed = true;
    }

    @Override
    public void reset() {
        animationDatas.clear();
    }
}
