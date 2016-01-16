package ru.kiripu.lifeinspace.world.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Body;
import ru.kiripu.lifeinspace.world.ComponentMappers;
import ru.kiripu.lifeinspace.world.components.JetpackControlComponent;
import ru.kiripu.lifeinspace.world.components.OxygenComponent;
import ru.kiripu.lifeinspace.world.components.PhysicComponent;
import ru.kiripu.lifeinspace.world.components.ViewComponent;
import ru.kiripu.lifeinspace.world.data.OxygenModificator;

/**
 * Created by kiripu on 08.01.2016.
 */
public class JetpackControlSystem extends IteratingSystem implements EntityListener {
    public JetpackControlSystem()
    {
        super(Family.all(JetpackControlComponent.class, ViewComponent.class, PhysicComponent.class).get());
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        engine.addEntityListener(getFamily(), this);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        JetpackControlComponent jetpackControlComponent = ComponentMappers.JETPACK.get(entity);
        if (jetpackControlComponent.isActive)
        {
            Input input = Gdx.input;
            Boolean isJetpackKeyActivated = input.isKeyPressed(jetpackControlComponent.jetpackActivateKey);

            if (isJetpackKeyActivated)
            {
                Body body = ComponentMappers.PHYSIC.get(entity).body;
                jetpackControlComponent.forceVector.setAngleRad(body.getAngle() + (float)Math.PI);
                body.applyForceToCenter(jetpackControlComponent.forceVector, true);
            }

            updateViewComponent(isJetpackKeyActivated, entity);
            updateOxygenComponent(
                    isJetpackKeyActivated, entity, jetpackControlComponent);
        }
        else if (jetpackControlComponent.jetpackIsActive)
        {
            updateViewComponent(false, entity);
            updateOxygenComponent(
                    false, entity, jetpackControlComponent);
        }
    }

    private void updateOxygenComponent(
            Boolean isJetpackKeyPressed,
            Entity entity,
            JetpackControlComponent jetpackControlComponent)
    {
        OxygenComponent oxygenComponent = ComponentMappers.OXYGEN.get(entity);
        if (jetpackControlComponent != null)
        {
            if (isJetpackKeyPressed && !jetpackControlComponent.jetpackIsActive)
            {
                jetpackControlComponent.jetpackIsActive = true;
                oxygenComponent.addModificator(OxygenModificator.PERMANENT_JETPACK_USE);
            }
            else if (!isJetpackKeyPressed && jetpackControlComponent.jetpackIsActive)
            {
                jetpackControlComponent.jetpackIsActive = false;
                oxygenComponent.removeModificator(OxygenModificator.PERMANENT_JETPACK_USE);
            }
        }
        else
        {
            oxygenComponent.removeModificator(OxygenModificator.PERMANENT_JETPACK_USE);
        }

    }

    private void updateViewComponent(
            Boolean isJetpackKeyPressed,
            Entity entity)
    {
        ViewComponent viewComponent = ComponentMappers.VIEW.get(entity);
        if (viewComponent != null)
        {
            viewComponent.spritesVisibility.set(0, isJetpackKeyPressed);
        }
    }

    @Override
    public void entityAdded(Entity entity) {

    }

    @Override
    public void entityRemoved(Entity entity)
    {
    }
}
