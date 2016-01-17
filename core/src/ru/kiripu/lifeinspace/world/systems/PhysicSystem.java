package ru.kiripu.lifeinspace.world.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import ru.kiripu.lifeinspace.Main;
import ru.kiripu.lifeinspace.enums.GameObjectType;
import ru.kiripu.lifeinspace.world.ComponentMappers;
import ru.kiripu.lifeinspace.world.PhysicObjectCreator;
import ru.kiripu.lifeinspace.world.components.*;

/**
 * Created by kiripu on 06.01.2016.
 */
public class PhysicSystem extends EntitySystem implements EntityListener, ContactListener
{
    private Engine engine;
    private World world;
    private Box2DDebugRenderer debugRenderer;

    private Family family;
    private OrthographicCamera debugCamera;
    private ImmutableArray<Entity> entities;

    public PhysicSystem()
    {
        Box2D.init();
        world = new World(new Vector2(0, 0), true);
        world.setContactListener(this);
        debugRenderer = new Box2DDebugRenderer();

        family = Family.all(PhysicComponent.class, TransformComponent.class).get();

        debugCamera = new OrthographicCamera(Main.width, Main.height);
        debugCamera.translate(Main.width * 0.5f, Main.height * 0.5f);
        debugCamera.update();

    }

    @Override
    public void addedToEngine(Engine engine)
    {
        this.engine = engine;
        entities = engine.getEntitiesFor(family);
        engine.addEntityListener(family, this);

        int length = entities.size();
        Entity entity;
        for (int i = 0; i < length; ++i)
        {
            entity = entities.get(i);
            entityAdded(entity);
        }
    }

    @Override
    public void update(float deltaTime)
    {
        debugRenderer.render(world, debugCamera.combined);
        world.step(deltaTime, 6, 2);

        int length = entities.size();
        Body body;
        TransformComponent transform;
        int type;
        Vector2 velocity;
        float velocityValue;
        Entity entity;
        for (int i = 0; i < length; ++i)
        {
            entity = entities.get(i);
            body = ComponentMappers.PHYSIC.get(entity).body;
            type = ComponentMappers.TYPE.get(entity).type;
            if (type != GameObjectType.TYPE_PLAYER)
            {
                velocity = body.getLinearVelocity().cpy();
                velocityValue = velocity.len();
                if (velocityValue == 0)
                {
                    velocity.x = 10;
                    velocity.rotate(MathUtils.random() * 360);
                    body.setLinearVelocity(velocity);
                }
                else if (velocityValue < 10)
                {
                    velocity.set(velocity.x * 1.03f, velocity.y * 1.03f);
                    body.setLinearVelocity(velocity);
                }
            }
            transform = ComponentMappers.TRANSFORM.get(entity);
            transform.position = body.getPosition().sub(transform.origin);
            transform.rotation = body.getAngle() * MathUtils.radiansToDegrees;
        }
    }

    @Override
    public void entityAdded(Entity entity)
    {
        TypeComponent typeComponent = ComponentMappers.TYPE.get(entity);
        TransformComponent transformComponent = ComponentMappers.TRANSFORM.get(entity);
        ViewComponent viewComponent = ComponentMappers.VIEW.get(entity);
        PhysicComponent physicComponent = ComponentMappers.PHYSIC.get(entity);

        Body body = PhysicObjectCreator.createBodyByTypeComponent(
                world, typeComponent, viewComponent.getMainSprite().getWidth(),
                transformComponent.position, transformComponent.rotation);
        body.setUserData(entity);
        physicComponent.setBody(body);
        body.setLinearVelocity(physicComponent.startVelocity);
        body.setAngularVelocity(MathUtils.random(-1, 1));
    }

    @Override
    public void entityRemoved(Entity entity)
    {

    }

    @Override
    public void beginContact(Contact contact)
    {
        Entity entityA = (Entity) contact.getFixtureA().getBody().getUserData();
        Entity entityB = (Entity) contact.getFixtureB().getBody().getUserData();
        PooledEngine pooledEngine = (PooledEngine) engine;

        entityA.add(pooledEngine.createComponent(CollisionComponent.class).init(entityB, true));
        entityB.add(pooledEngine.createComponent(CollisionComponent.class).init(entityA, true));
    }

    @Override
    public void endContact(Contact contact)
    {
        Entity entityA = (Entity) contact.getFixtureA().getBody().getUserData();
        Entity entityB = (Entity) contact.getFixtureB().getBody().getUserData();
        PooledEngine pooledEngine = (PooledEngine) engine;

        entityA.add(pooledEngine.createComponent(CollisionComponent.class).init(entityB, false));
        entityB.add(pooledEngine.createComponent(CollisionComponent.class).init(entityA, false));
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
