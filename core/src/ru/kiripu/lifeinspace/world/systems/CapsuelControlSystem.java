package ru.kiripu.lifeinspace.world.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;
import ru.kiripu.lifeinspace.world.AnimationStorage;
import ru.kiripu.lifeinspace.world.ComponentMappers;
import ru.kiripu.lifeinspace.world.components.CapsuleControlComponent;
import ru.kiripu.lifeinspace.world.components.OxygenComponent;
import ru.kiripu.lifeinspace.world.components.PhysicComponent;
import ru.kiripu.lifeinspace.world.components.ViewComponent;
import ru.kiripu.lifeinspace.world.data.OxygenModificator;

/**
 * Created by kiripu on 08.01.2016.
 */
public class CapsuelControlSystem extends IteratingSystem implements EntityListener {
    public CapsuelControlSystem()
    {
        super(Family.all(CapsuleControlComponent.class, ViewComponent.class, PhysicComponent.class).get());
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        engine.addEntityListener(getFamily(), this);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        Input input = Gdx.input;
        CapsuleControlComponent capsuleControlComponent = ComponentMappers.CAPSULE_CONTROL.get(entity);
        Boolean isCapsuleExitKeyActivated = input.isKeyJustPressed(capsuleControlComponent.capsuleExitKey);

        if (isCapsuleExitKeyActivated && !capsuleControlComponent.isExiting)
        {
            capsuleControlComponent.isExiting = true;
            Body body = ComponentMappers.PHYSIC.get(entity).body;
            Vector2 impulseVector = new Vector2(-10000, 0);
            impulseVector.rotateRad(body.getAngle());
            body.getWorld().destroyJoint(body.getJointList().get(0).joint);
            body.applyLinearImpulse(impulseVector, body.getWorldCenter(), true);
            ComponentMappers.CONTROL.get(entity).isActive = true;
            ComponentMappers.JETPACK.get(entity).isActive = true;
        }
    }

    @Override
    public void entityAdded(Entity entity)
    {
        ViewComponent viewComponent = ComponentMappers.VIEW.get(entity);
        ((AnimatedSprite)viewComponent.getMainSprite()).setAnimation(
                AnimationStorage.getInstance().
                        getAnimation("hero", "safe", Animation.PlayMode.LOOP));

        OxygenComponent oxygenComponent = ComponentMappers.OXYGEN.get(entity);
        oxygenComponent.addModificator(OxygenModificator.PERMANENT_SAFE_CAPSUEL_USE);
    }

    @Override
    public void entityRemoved(Entity entity)
    {
        ViewComponent viewComponent = ComponentMappers.VIEW.get(entity);
        ((AnimatedSprite)viewComponent.getMainSprite()).setAnimation(
                AnimationStorage.getInstance().
                        getAnimation("hero", "idle", Animation.PlayMode.LOOP));

        OxygenComponent oxygenComponent = ComponentMappers.OXYGEN.get(entity);
        oxygenComponent.removeModificator(OxygenModificator.PERMANENT_SAFE_CAPSUEL_USE);

    }
}
