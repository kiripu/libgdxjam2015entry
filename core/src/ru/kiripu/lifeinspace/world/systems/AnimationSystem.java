package ru.kiripu.lifeinspace.world.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;
import ru.kiripu.lifeinspace.world.ComponentMappers;
import ru.kiripu.lifeinspace.world.components.AnimationComponent;
import ru.kiripu.lifeinspace.world.components.SpriteComponent;

/**
 * Created by kiripu on 06.01.2016.
 */
public class AnimationSystem extends EntitySystem implements EntityListener
{
    private Engine engine;
    private Family family;
    private ImmutableArray<Entity> entities;

    public AnimationSystem()
    {
        family = Family.all(SpriteComponent.class, AnimationComponent.class).get();
    }

    @Override
    public void addedToEngine(Engine engine)
    {
        this.engine = engine;
        entities = engine.getEntitiesFor(family);
        engine.addEntityListener(family, this);

        int length = entities.size();
        Entity entity;
        for (int i = 0; i < length; ++i)
        {
            entity = entities.get(i);
            entityAdded(entity);
        }
    }

    @Override
    public void update(float deltaTime)
    {
        int length = entities.size();
        Entity entity;
        AnimatedSprite animatedSprite;
        for (int i = 0; i < length; ++i)
        {
            entity = entities.get(i);
            animatedSprite = (AnimatedSprite)ComponentMappers.SPRITE.get(entity).sprite;
            animatedSprite.update(deltaTime);
        }
    }

    @Override
    public void entityAdded(Entity entity)
    {
    }

    @Override
    public void entityRemoved(Entity entity)
    {

    }
}
