package ru.kiripu.lifeinspace.world.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Body;
import ru.kiripu.lifeinspace.world.ComponentMappers;
import ru.kiripu.lifeinspace.world.components.PhysicComponent;
import ru.kiripu.lifeinspace.world.components.TurnControlComponent;

/**
 * Created by kiripu on 08.01.2016.
 */
public class TurnControlSystem extends IteratingSystem {

    public TurnControlSystem()
    {
        super(Family.all(PhysicComponent.class, TurnControlComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        Body body = ComponentMappers.PHYSIC.get(entity).body;
        TurnControlComponent turnControlComponent = ComponentMappers.CONTROL.get(entity);
        Input input = Gdx.input;
        float angularVelocity = 0f;

        if (input.isKeyPressed(turnControlComponent.turnLeftKey)) angularVelocity += turnControlComponent.turnValue;
        if (input.isKeyPressed(turnControlComponent.turnRightKey)) angularVelocity += -turnControlComponent.turnValue;
        body.setAngularVelocity(angularVelocity);
    }
}
