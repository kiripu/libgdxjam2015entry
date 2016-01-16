package ru.kiripu.lifeinspace.world.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import ru.kiripu.lifeinspace.enums.GameObjectType;
import ru.kiripu.lifeinspace.world.ComponentMappers;
import ru.kiripu.lifeinspace.world.components.*;
import ru.kiripu.lifeinspace.world.data.OxygenModificator;

/**
 * Created by kiripu on 08.01.2016.
 */
public class CollisionSystem extends IteratingSystem {

    public CollisionSystem()
    {
        super(Family.all(CollisionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime)
    {
        CollisionComponent collisionComponent = ComponentMappers.COLLISION.get(entity);
        TypeComponent typeComponent = ComponentMappers.TYPE.get(entity);

        if (typeComponent.type == GameObjectType.TYPE_PLAYER)
        {
            if (collisionComponent.isBegin) processPlayerBeginCollision(entity, collisionComponent.entity);
            else processPlayerEndCollision(entity, collisionComponent.entity);
        }
        entity.remove(CollisionComponent.class);
    }

    private void processPlayerBeginCollision(Entity playerEntity, Entity otherEntity)
    {
        TypeComponent typeComponent = ComponentMappers.TYPE.get(otherEntity);
        CapsuleControlComponent capsuleControlComponent = ComponentMappers.CAPSULE_CONTROL.get(playerEntity);
        if (typeComponent.type == GameObjectType.TYPE_SAFE_CAPSULE && capsuleControlComponent == null)
        {
            Body playerBody = ComponentMappers.PHYSIC.get(playerEntity).body;
            Body safeCapsuleBody = ComponentMappers.PHYSIC.get(otherEntity).body;

            playerBody.getFixtureList().get(0).setSensor(true);
            Vector2 posDiff = playerBody.getWorldCenter().sub(playerBody.getPosition()).cpy();
            playerBody.setTransform(safeCapsuleBody.getWorldCenter().sub(posDiff), playerBody.getAngle());

            WeldJointDef jointDef = new WeldJointDef ();
            jointDef.initialize(playerBody, safeCapsuleBody, safeCapsuleBody.getWorldCenter());
            safeCapsuleBody.getWorld().createJoint(jointDef);

            JetpackControlComponent jetpackControlComponent = ComponentMappers.JETPACK.get(playerEntity);
            jetpackControlComponent.isActive = false;
            TurnControlComponent turnControlComponent = ComponentMappers.CONTROL.get(playerEntity);
            turnControlComponent.isActive = false;
            PooledEngine engine = (PooledEngine) this.getEngine();
            playerEntity.add(engine.createComponent(CapsuleControlComponent.class).init(jetpackControlComponent.jetpackActivateKey));

        }
        if (typeComponent.type == GameObjectType.TYPE_ASTEROID)
        {
            OxygenComponent oxygenComponent = ComponentMappers.OXYGEN.get(playerEntity);
            oxygenComponent.addModificator(OxygenModificator.createInstantAsteroidModificator());
        }
    }

    private void processPlayerEndCollision(Entity playerEntity, Entity otherEntity)
    {
        TypeComponent typeComponent = ComponentMappers.TYPE.get(otherEntity);
        CapsuleControlComponent capsuleControlComponent = ComponentMappers.CAPSULE_CONTROL.get(playerEntity);
        if (typeComponent.type == GameObjectType.TYPE_SAFE_CAPSULE
                && capsuleControlComponent != null && capsuleControlComponent.isExiting)
        {
            playerEntity.remove(CapsuleControlComponent.class);
        }
    }
}
