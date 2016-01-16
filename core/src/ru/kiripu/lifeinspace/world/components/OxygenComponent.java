package ru.kiripu.lifeinspace.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import ru.kiripu.lifeinspace.world.data.OxygenModificator;

/**
 * Created by kiripu on 16.01.2016.
 */
public class OxygenComponent implements Component, Pool.Poolable {
    public static float curOxygenValue = 0;
    public static float maxOxygenValue;
    private Array<OxygenModificator> permanentModificators;

    public OxygenComponent init(float oxygenValue)
    {
        this.curOxygenValue += oxygenValue;
        this.maxOxygenValue += oxygenValue;
        this.permanentModificators = new Array<OxygenModificator>();
        return this;
    }

    public int getOxygenChangeSpeed()
    {
        int curOxygenChangeSpeed = 0;
        int i = 0;
        OxygenModificator modificator;
        for (i = 0; i < permanentModificators.size; i++)
        {
            modificator = permanentModificators.get(i);
            curOxygenChangeSpeed += modificator.value;
        }
        return curOxygenChangeSpeed;
    }

    public OxygenComponent addModificator(OxygenModificator modificator)
    {
        if (modificator.permanent)
        {
            if (!permanentModificators.contains(modificator, true)) permanentModificators.add(modificator);
        }
        else curOxygenValue += modificator.value;
        return this;
    }

    public OxygenComponent removeModificator(OxygenModificator modificator)
    {
        if (modificator.permanent && permanentModificators.contains(modificator, true))
        {
            permanentModificators.removeValue(modificator, true);
        }
        return this;
    }

    @Override
    public void reset() {
        curOxygenValue = 0;
        maxOxygenValue = 0;
        permanentModificators.clear();
    }
}
