package ru.kiripu.lifeinspace.world;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.Animation;
import ru.kiripu.lifeinspace.enums.GameObjectType;
import ru.kiripu.lifeinspace.enums.States;
import ru.kiripu.lifeinspace.world.components.*;
import ru.kiripu.lifeinspace.world.data.OxygenModificator;

/**
 * Created by kiripu on 07.01.2016.
 */
public class EntityFactory
{
    public static void createAsteroid(PooledEngine engine, int variance, float posX, float posY, float rot)
    {
        TypeComponent typeComponent = engine.createComponent(TypeComponent.class).init(GameObjectType.TYPE_ASTEROID, variance);
        ViewComponent viewComponent = ObjectViewCreator.createSpriteViewComponent(engine, typeComponent);
        Entity entity = engine.createEntity();
        entity.add(engine.createComponent(TransformComponent.class).init(posX, posY, rot));
        entity.add(typeComponent);
        entity.add(viewComponent);
        entity.add(engine.createComponent(PhysicComponent.class));
        engine.addEntity(entity);
    }

    public static void createSafeCapsule(PooledEngine engine, float posX, float posY, float rot)
    {
        TypeComponent typeComponent = engine.createComponent(TypeComponent.class).init(GameObjectType.TYPE_SAFE_CAPSULE);
        ViewComponent viewComponent = ObjectViewCreator.createSpriteViewComponent(engine, typeComponent);
        Entity entity = engine.createEntity();
        entity.add(engine.createComponent(TransformComponent.class).init(posX, posY, rot));
        entity.add(typeComponent);
        entity.add(viewComponent);
        entity.add(engine.createComponent(PhysicComponent.class));
        entity.add(engine.createComponent(StateComponent.class).init(States.STATE_FREE));
        engine.addEntity(entity);
    }


    public static void createPlayer(
            PooledEngine engine,
            float posX, float posY, float rotation,
            int actionKey, int turnRightKey, int turnLeftKey)
    {
        TypeComponent typeComponent = engine.createComponent(TypeComponent.class).init(GameObjectType.TYPE_PLAYER, 0);
        ViewComponent viewComponent = ObjectViewCreator.createPlayerView(engine, typeComponent);
        AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
        animationComponent.init(
                AnimationStorage.getInstance().getAnimation("hero", "idle", Animation.PlayMode.LOOP));

        Entity entity = engine.createEntity();
        entity.add(engine.createComponent(TransformComponent.class).init(posX, posY, rotation));
        entity.add(typeComponent);
        entity.add(viewComponent);
        entity.add(animationComponent);
        entity.add(engine.createComponent(PhysicComponent.class));
        entity.add(engine.createComponent(TurnControlComponent.class).init(turnRightKey, turnLeftKey, 1f));
        entity.add(engine.createComponent(JetpackControlComponent.class).init(actionKey, 10000));
        entity.add(engine.createComponent(OxygenComponent.class).init(100).addModificator(OxygenModificator.PERMANENT_DEFAULT));
        engine.addEntity(entity);
    }

}
