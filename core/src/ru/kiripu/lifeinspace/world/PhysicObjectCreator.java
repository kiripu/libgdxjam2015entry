package ru.kiripu.lifeinspace.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import ru.kiripu.lifeinspace.enums.GameObjectType;
import ru.kiripu.lifeinspace.world.components.TypeComponent;

/**
 * Created by kiripu on 15.01.2016.
 */
public class PhysicObjectCreator
{
    final private static short CATEGORY_PLAYER = 0x0001;
    final private static short CATEGORY_ASTEROID = 0x0002;
    final private static short CATEGORY_SAFE_CUPSULE = 0x0004;
    final private static short CATEGORY_SAFE_CUPSULE_CENTER = 0x0008;

    final private static short MASK_PLAYER = CATEGORY_ASTEROID | CATEGORY_SAFE_CUPSULE_CENTER;
    final private static short MASK_ASTEROID = CATEGORY_PLAYER | CATEGORY_SAFE_CUPSULE;
    final private static short MASK_SAFE_CAPSULE = CATEGORY_ASTEROID;
    final private static short MASK_SAFE_CAPSULE_CENTER = CATEGORY_PLAYER;

    private static BodyEditorLoader bodyEditorLoader;

    public static Body createBodyByTypeComponent(
            World world, TypeComponent typeComponent, float scale, Vector2 position, float rotation)
    {
        switch (typeComponent.type)
        {
            case GameObjectType.TYPE_ASTEROID:
                return createAsteroidBody(world, typeComponent.variance, scale, position, rotation);
            case GameObjectType.TYPE_SAFE_CAPSULE:
                return createSafeCapsuleBody(world, scale, position, rotation);
            case GameObjectType.TYPE_PLAYER:
                return createPlayerBody(world, scale, position, rotation);
            default: return null;
        }
    }

    private static Body createAsteroidBody(World world, int variance, float scale, Vector2 position, float rotation)
    {
        String bodyName = "asteroid_0" + variance;
        Body body = createBodyWithBodyEditorLoader(world, bodyName, scale, position, rotation);

        Filter filter = new Filter();
        filter.categoryBits = CATEGORY_ASTEROID;
        filter.maskBits = MASK_ASTEROID;
        body.getFixtureList().get(0).setFilterData(filter);
        return body;
    }

    private static Body createSafeCapsuleBody(World world, float scale, Vector2 position, float rotation)
    {
        String bodyName = "safeCapsule";
        Body body = createBodyWithBodyEditorLoader(world, bodyName, scale, position, rotation);

        Filter safeCapsuleFilter = new Filter();
        safeCapsuleFilter.categoryBits = CATEGORY_SAFE_CUPSULE;
        safeCapsuleFilter.maskBits = MASK_SAFE_CAPSULE;
        Filter safeCapsuleCenterFilter = new Filter();
        safeCapsuleCenterFilter.categoryBits = CATEGORY_SAFE_CUPSULE_CENTER;
        safeCapsuleCenterFilter.maskBits = MASK_SAFE_CAPSULE_CENTER;

        body.getFixtureList().get(0).setFilterData(safeCapsuleFilter);
        body.getFixtureList().get(1).setFilterData(safeCapsuleCenterFilter);
        body.getFixtureList().get(1).setSensor(true);
        return body;
    }

    private static Body createPlayerBody(World world, float scale, Vector2 position, float rotation)
    {
        String bodyName = "hero";
        Body body = createBodyWithBodyEditorLoader(world, bodyName, scale, position, rotation);

        Filter filter = new Filter();
        filter.categoryBits = CATEGORY_PLAYER;
        filter.maskBits = MASK_PLAYER;
        body.getFixtureList().get(0).setFilterData(filter);
        return body;
    }

    private static Body createBodyWithBodyEditorLoader(World world, String bodyName, float scale, Vector2 position, float rotation)
    {
        BodyDef bd = new BodyDef();
        bd.position.set(position);
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.angle = rotation * MathUtils.degreesToRadians;

        FixtureDef fd = new FixtureDef();
        fd.density = 1;
        fd.friction = 0.5f;
        fd.restitution = 0.3f;

        Body body = world.createBody(bd);
        getBodyLoader().attachFixture(body, bodyName, fd, scale);
        return body;
    }

    private static BodyEditorLoader getBodyLoader()
    {
        if (bodyEditorLoader == null) bodyEditorLoader = new BodyEditorLoader(Gdx.files.internal("physics/liveInSpace"));
        return bodyEditorLoader;
    }
}
