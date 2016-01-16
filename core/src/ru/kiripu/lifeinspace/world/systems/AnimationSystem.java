package ru.kiripu.lifeinspace.world.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;
import ru.kiripu.lifeinspace.world.ComponentMappers;
import ru.kiripu.lifeinspace.world.components.AnimationComponent;
import ru.kiripu.lifeinspace.world.components.ViewComponent;
import ru.kiripu.lifeinspace.world.data.AnimationData;

/**
 * Created by kiripu on 08.01.2016.
 */
public class AnimationSystem extends IteratingSystem {

    public AnimationSystem()
    {
        super(Family.all(AnimationComponent.class, ViewComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        AnimationComponent animationComponent = ComponentMappers.ANIMATION.get(entity);
        ViewComponent viewComponent = ComponentMappers.VIEW.get(entity);
        AnimatedSprite animatedSprite = (AnimatedSprite)viewComponent.getMainSprite();
        if (animationComponent.changed)
        {
            animationComponent.changed = false;
            AnimationData animationData = animationComponent.animationDatas.first();
            if (animationData != null)
            {
                animatedSprite = new AnimatedSprite(animationData.animation);
                animatedSprite.setOrigin(0, 0);
                viewComponent.setMainSprite(animatedSprite, animationData.offset);
            }
        }
        else if (animatedSprite.isAnimationFinished() && animatedSprite.getAnimation().getPlayMode() == Animation.PlayMode.NORMAL)
        {
            animationComponent.animationDatas.removeIndex(0);
            animationComponent.changed = true;
        }
    }
}
