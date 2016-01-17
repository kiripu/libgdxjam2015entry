package ru.kiripu.lifeinspace.world.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import ru.kiripu.lifeinspace.managers.GameMaster;
import ru.kiripu.lifeinspace.world.AnimationStorage;
import ru.kiripu.lifeinspace.world.ComponentMappers;
import ru.kiripu.lifeinspace.world.components.*;
import ru.kiripu.lifeinspace.world.data.AnimationData;

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
        int i;
        OxygenComponent oxygenComponent = null;
        Entity entity;
        for (i = 0; i < length; ++i)
        {
            entity = entities.get(i);
            oxygenComponent = ComponentMappers.OXYGEN.get(entity);
            oxygenComponent.curOxygenValue += oxygenComponent.getOxygenChangeSpeed() * deltaTime;
            oxygenComponent.curOxygenValue =
                    MathUtils.clamp(oxygenComponent.curOxygenValue, 0, oxygenComponent.maxOxygenValue);
        }
        if (oxygenComponent != null)
        {
            GameMaster.getInstance().setOxygenProgress(oxygenComponent.curOxygenValue / length);
            if (oxygenComponent.curOxygenValue == 0)
            {
                for (i = 0; i < length; ++i)
                {
                    entity = entities.get(i);
                    Array<AnimationData> animationDatas = new Array<AnimationData>();
                    animationDatas.add(
                            AnimationStorage.getInstance().getAnimation("hero", "die", Animation.PlayMode.NORMAL));
                    animationDatas.add(
                            AnimationStorage.getInstance().getAnimation("hero", "die_2", Animation.PlayMode.NORMAL));
                    animationDatas.add(
                            AnimationStorage.getInstance().getAnimation("hero", "dead", Animation.PlayMode.LOOP));
                    ComponentMappers.ANIMATION.get(entity).change(animationDatas);

                    entity.remove(OxygenComponent.class);
                    entity.remove(JetpackControlComponent.class);
                    entity.remove(TurnControlComponent.class);
                    entity.remove(CapsuleControlComponent.class);
                    entity.remove(CollisionComponent.class);
                }
            }
        }
        else GameMaster.getInstance().setOxygenProgress(0);
    }
}
