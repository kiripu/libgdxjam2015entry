package ru.kiripu.lifeinspace.world;

import com.badlogic.ashley.core.ComponentMapper;
import ru.kiripu.lifeinspace.world.components.PhysicComponent;
import ru.kiripu.lifeinspace.world.components.SpriteComponent;
import ru.kiripu.lifeinspace.world.components.TransformComponent;

/**
 * Created by kiripu on 06.01.2016.
 */
public class ComponentMappers
{
    public static final ComponentMapper<TransformComponent> TRANSFORM = ComponentMapper.getFor(TransformComponent.class);
    public static final ComponentMapper<PhysicComponent> PHYSIC = ComponentMapper.getFor(PhysicComponent.class);
    public static final ComponentMapper<SpriteComponent> SPRITE = ComponentMapper.getFor(SpriteComponent.class);

}
