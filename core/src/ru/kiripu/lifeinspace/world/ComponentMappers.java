package ru.kiripu.lifeinspace.world;

import com.badlogic.ashley.core.ComponentMapper;
import ru.kiripu.lifeinspace.world.components.PhysicComponent;
import ru.kiripu.lifeinspace.world.components.PositionComponent;

/**
 * Created by kiripu on 06.01.2016.
 */
public class ComponentMappers
{
    public static final ComponentMapper<PositionComponent> POSITION = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<PhysicComponent> PHYSIC = ComponentMapper.getFor(PhysicComponent.class);

}
