package ru.kiripu.lifeinspace.world.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Body;
import ru.kiripu.lifeinspace.world.ComponentMappers;
import ru.kiripu.lifeinspace.world.components.ControlComponent;
import ru.kiripu.lifeinspace.world.components.PhysicComponent;
import ru.kiripu.lifeinspace.world.components.ViewComponent;

/**
 * Created by kiripu on 08.01.2016.
 */
public class ControlSystem extends IteratingSystem {

    public ControlSystem()
    {
        super(Family.all(PhysicComponent.class, ControlComponent.class, ViewComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        Body body = ComponentMappers.PHYSIC.get(entity).body;
        ViewComponent viewComponent = ComponentMappers.VIEW.get(entity);
        ControlComponent controlComponent = ComponentMappers.CONTROL.get(entity);
        Input input = Gdx.input;
        float angularVelocity = 0f;
        Boolean isJetpackActivated = false;

        if (input.isKeyPressed(controlComponent.turnLeftKey)) angularVelocity += controlComponent.turnValue;
        if (input.isKeyPressed(controlComponent.turnRightKey)) angularVelocity += -controlComponent.turnValue;
        if (input.isKeyPressed(controlComponent.jetpackActivateKey)) isJetpackActivated = true;
        if (isJetpackActivated)
        {
            controlComponent.forceVector.setAngleRad(body.getAngle() + (float)Math.PI);
            body.applyForceToCenter(controlComponent.forceVector, true);
        }
        viewComponent.spritesVisibility.set(0, isJetpackActivated);
        body.setAngularVelocity(angularVelocity);
    }
}
