package ru.kiripu.lifeinspace.world.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import ru.kiripu.lifeinspace.managers.GameMaster;
import ru.kiripu.lifeinspace.world.ComponentMappers;
import ru.kiripu.lifeinspace.world.components.OxygenComponent;

/**
 * Created by kiripu on 08.01.2016.
 */
public class OxygenSystem extends IteratingSystem{

    public OxygenSystem()
    {
        super(Family.all(OxygenComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        OxygenComponent oxygenComponent = ComponentMappers.OXYGEN.get(entity);
        oxygenComponent.curOxygenValue += oxygenComponent.getOxygenChangeSpeed() * deltaTime;
        oxygenComponent.curOxygenValue =
                MathUtils.clamp(oxygenComponent.curOxygenValue, 0, oxygenComponent.maxOxygenValue);
        GameMaster.getInstance().setOxygenProgress(oxygenComponent.curOxygenValue);
    }
}
