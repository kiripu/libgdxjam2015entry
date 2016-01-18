package ru.kiripu.lifeinspace.world.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import ru.kiripu.lifeinspace.enums.GameObjectType;
import ru.kiripu.lifeinspace.world.ComponentMappers;
import ru.kiripu.lifeinspace.world.components.TransformComponent;
import ru.kiripu.lifeinspace.world.components.TypeComponent;
import ru.kiripu.lifeinspace.world.components.ViewComponent;

/**
 * Created by kiripu on 06.01.2016.
 */
public class RenderSystem extends EntitySystem implements EntityListener
{
    private final SpriteBatch batch;
    private Family family;
    private Array<Entity> frontObjects;
    private Array<Entity> backObjects;

    public RenderSystem()
    {
        family = Family.all(TransformComponent.class, ViewComponent.class).get();
        batch = new SpriteBatch();

        frontObjects = new Array<Entity>();
        backObjects = new Array<Entity>();
    }

    @Override
    public void addedToEngine(Engine engine)
    {
        ImmutableArray<Entity> entities = engine.getEntitiesFor(family);
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
        batch.begin();

        int length = backObjects.size;
        for (int i = 0; i < length; ++i) drawEntity(backObjects.get(i));
        length = frontObjects.size;
        for (int i = 0; i < length; ++i) drawEntity(frontObjects.get(i));

        batch.end();
    }

    private void drawEntity(Entity entity)
    {
        TransformComponent transform;
        ViewComponent viewComponent;
        Sprite sprite;
        Boolean isSpriteVisible;
        Vector2 offset;

        transform = ComponentMappers.TRANSFORM.get(entity);
        viewComponent = ComponentMappers.VIEW.get(entity);
        for (int j = 0; j < viewComponent.sprites.size; j++)
        {
            isSpriteVisible = viewComponent.spritesVisibility.get(j);
            if (isSpriteVisible)
            {
                sprite = viewComponent.sprites.get(j);
                offset = viewComponent.offset.get(j);
                sprite.setPosition(transform.getPositionX(offset), transform.getPositionY(offset));
                sprite.setRotation(transform.getRotation());
                sprite.draw(batch);
            }
        }
    }

    @Override
    public void entityAdded(Entity entity)
    {
        TypeComponent typeComponent = ComponentMappers.TYPE.get(entity);
        if (typeComponent.type == GameObjectType.TYPE_ASTEROID) frontObjects.add(entity);
        else backObjects.add(entity);
    }

    @Override
    public void entityRemoved(Entity entity)
    {
        TypeComponent typeComponent = ComponentMappers.TYPE.get(entity);
        if (typeComponent.type == GameObjectType.TYPE_ASTEROID) frontObjects.removeValue(entity, true);
        else backObjects.removeValue(entity, true);
    }
}
