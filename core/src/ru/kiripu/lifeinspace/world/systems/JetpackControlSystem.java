package ru.kiripu.lifeinspace.world.systems;

import com.badlogic.ashley.core.Entity;
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

/**
 * Created by kiripu on 08.01.2016.
 */
public class JetpackControlSystem extends IteratingSystem {

    public JetpackControlSystem()
    {
        super(Family.all(JetpackControlComponent.class, ViewComponent.class, PhysicComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        Input input = Gdx.input;
        JetpackControlComponent jetpackControlComponent = ComponentMappers.JETPACK.get(entity);
        Boolean isJetpackActivated = input.isKeyPressed(jetpackControlComponent.jetpackActivateKey);
        int oxygenChangeAdd = 0;

        if (isJetpackActivated)
        {
            if (!jetpackControlComponent.jetpackIsActive)
            {
                jetpackControlComponent.jetpackIsActive = true;
                oxygenChangeAdd += -10;
            }
            Body body = ComponentMappers.PHYSIC.get(entity).body;
            jetpackControlComponent.forceVector.setAngleRad(body.getAngle() + (float)Math.PI);
            body.applyForceToCenter(jetpackControlComponent.forceVector, true);
        }
        else
        {
            if (jetpackControlComponent.jetpackIsActive)
            {
                jetpackControlComponent.jetpackIsActive = false;
                oxygenChangeAdd += 10;
            }
        }

        ComponentMappers.VIEW.get(entity).spritesVisibility.set(0, isJetpackActivated);

        OxygenComponent oxygenComponent = ComponentMappers.OXYGEN.get(entity);
        if (oxygenComponent != null && oxygenChangeAdd != 0)
        {
            oxygenComponent.oxygenChangeSpeed += oxygenChangeAdd;
        }


    }
}
