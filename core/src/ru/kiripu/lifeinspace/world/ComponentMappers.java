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
    public static final ComponentMapper<JetpackControlComponent> JETPACK = ComponentMapper.getFor(JetpackControlComponent.class);
    public static final ComponentMapper<TypeComponent> TYPE = ComponentMapper.getFor(TypeComponent.class);
    public static final ComponentMapper<CollisionComponent> COLLISION = ComponentMapper.getFor(CollisionComponent.class);
    public static final ComponentMapper<OxygenComponent> OXYGEN = ComponentMapper.getFor(OxygenComponent.class);
    public static final ComponentMapper<CapsuleControlComponent> CAPSULE_CONTROL
            = ComponentMapper.getFor(CapsuleControlComponent.class);
    public static final ComponentMapper<StateComponent> STATE = ComponentMapper.getFor(StateComponent.class);
    public static final ComponentMapper<AnimationComponent> ANIMATION = ComponentMapper.getFor(AnimationComponent.class);

}
