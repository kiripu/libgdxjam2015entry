package ru.kiripu.lifeinspace.world.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
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

        if (typeComponent.type == GameObjectType.TYPE_PLAYER) processPlayerCollision(entity, collisionComponent.entity);
        entity.remove(CollisionComponent.class);
    }

    private static void processPlayerCollision(Entity playerEntity, Entity otherEntity)
    {
        TypeComponent typeComponent = ComponentMappers.TYPE.get(otherEntity);
        if (typeComponent.type == GameObjectType.TYPE_SAFE_CAPSULE)
        {
            Body playerBody = ComponentMappers.PHYSIC.get(playerEntity).body;
            Body safeCapsuleBody = ComponentMappers.PHYSIC.get(otherEntity).body;

            playerBody.getFixtureList().get(0).setSensor(true);
            playerBody.setTransform(safeCapsuleBody.getPosition(), safeCapsuleBody.getAngle());

            WeldJointDef jointDef = new WeldJointDef ();
            jointDef.initialize(playerBody, safeCapsuleBody, safeCapsuleBody.getWorldCenter());
            safeCapsuleBody.getWorld().createJoint(jointDef);

            playerEntity.remove(TurnControlComponent.class);
            playerEntity.remove(JetpackControlComponent.class);
        }
        if (typeComponent.type == GameObjectType.TYPE_ASTEROID)
        {
            OxygenComponent oxygenComponent = ComponentMappers.OXYGEN.get(playerEntity);
            oxygenComponent.addModificator(OxygenModificator.createInstantAsteroidModificator());
        }
    }
}
