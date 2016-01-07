package ru.kiripu.lifeinspace.world.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Sprite;
import ru.kiripu.lifeinspace.Main;
import ru.kiripu.lifeinspace.world.ComponentMappers;
import ru.kiripu.lifeinspace.world.components.SpriteComponent;
import ru.kiripu.lifeinspace.world.components.TransformComponent;

/**
 * Created by kiripu on 06.01.2016.
 */
public class RenderSystem extends EntitySystem implements EntityListener
{
    private Engine engine;
    private Family family;
    private ImmutableArray<Entity> entities;

    public RenderSystem()
    {
        family = Family.all(SpriteComponent.class, TransformComponent.class).get();
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
        TransformComponent transform;
        Sprite sprite;
        for (int i = 0; i < length; ++i)
        {
            entity = entities.get(i);
            transform = ComponentMappers.TRANSFORM.get(entity);
            sprite = ComponentMappers.SPRITE.get(entity).sprite;
            sprite.setPosition(transform.position.x, transform.position.y);
            sprite.setOrigin(transform.origin.x, transform.origin.y);
            sprite.setRotation(transform.rotation);
            sprite.draw(Main.batch);
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
