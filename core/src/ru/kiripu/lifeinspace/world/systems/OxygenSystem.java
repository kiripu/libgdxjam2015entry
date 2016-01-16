package ru.kiripu.lifeinspace.world.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.MathUtils;
import ru.kiripu.lifeinspace.managers.GameMaster;
import ru.kiripu.lifeinspace.world.ComponentMappers;
import ru.kiripu.lifeinspace.world.components.OxygenComponent;

/**
 * Created by kiripu on 06.01.2016.
 */
public class OxygenSystem extends EntitySystem
{
    private ImmutableArray<Entity> entities;

    @Override
    public void addedToEngine(Engine engine)
    {
        entities = engine.getEntitiesFor(Family.all(OxygenComponent.class).get());
    }

    @Override
    public void update(float deltaTime)
    {
        int length = entities.size();
        OxygenComponent oxygenComponent = null;
        Entity entity;
        for (int i = 0; i < length; ++i)
        {
            entity = entities.get(i);
            oxygenComponent = ComponentMappers.OXYGEN.get(entity);
            oxygenComponent.curOxygenValue += oxygenComponent.getOxygenChangeSpeed() * deltaTime;
            oxygenComponent.curOxygenValue =
                    MathUtils.clamp(oxygenComponent.curOxygenValue, 0, oxygenComponent.maxOxygenValue);
        }
        if (oxygenComponent != null)
            GameMaster.getInstance().setOxygenProgress(oxygenComponent.curOxygenValue / length);
    }
}
