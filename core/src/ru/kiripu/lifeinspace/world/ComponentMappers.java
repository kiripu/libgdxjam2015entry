package ru.kiripu.lifeinspace.world;

import com.badlogic.ashley.core.ComponentMapper;
import ru.kiripu.lifeinspace.world.components.*;

/**
 * Created by kiripu on 06.01.2016.
 */
public class ComponentMappers
{
    public static final ComponentMapper<TransformComponent> TRANSFORM = ComponentMapper.getFor(TransformComponent.class);
    public static final ComponentMapper<PhysicComponent> PHYSIC = ComponentMapper.getFor(PhysicComponent.class);
    public static final ComponentMapper<ViewComponent> VIEW = ComponentMapper.getFor(ViewComponent.class);
    public static final ComponentMapper<TurnControlComponent> CONTROL = ComponentMapper.getFor(TurnControlComponent.class);
    public static final ComponentMapper<EnergyComponent> ENERGY = ComponentMapper.getFor(EnergyComponent.class);
    public static final ComponentMapper<JetpackControlComponent> JETPACK = ComponentMapper.getFor(JetpackControlComponent.class);

}
