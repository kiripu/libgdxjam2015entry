package ru.kiripu.lifeinspace.world.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import ru.kiripu.lifeinspace.Main;
import ru.kiripu.lifeinspace.world.BodyEditorLoader;
import ru.kiripu.lifeinspace.world.ComponentMappers;
import ru.kiripu.lifeinspace.world.PhysicObjectCreator;
import ru.kiripu.lifeinspace.world.components.PhysicComponent;
import ru.kiripu.lifeinspace.world.components.TransformComponent;
import ru.kiripu.lifeinspace.world.components.TypeComponent;
import ru.kiripu.lifeinspace.world.components.ViewComponent;

/**
 * Created by kiripu on 06.01.2016.
 */
public class PhysicSystem extends EntitySystem implements EntityListener
{
    private Engine engine;
    private BodyEditorLoader bodyEditorLoader;
    private World world;
    private Box2DDebugRenderer debugRenderer;

    private Family family;
    private OrthographicCamera debugCamera;
    private ImmutableArray<Entity> entities;

    public PhysicSystem()
    {
        Box2D.init();
        world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();
        bodyEditorLoader = new BodyEditorLoader(Gdx.files.internal("physics/liveInSpace"));

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
        debugCamera.update();
        Main.batch.setProjectionMatrix(debugCamera.combined);
        debugRenderer.render(world, debugCamera.combined);
        world.step(deltaTime, 6, 2);

        int length = entities.size();
        Body body;
        TransformComponent transform;
        Entity entity;
        for (int i = 0; i < length; ++i)
        {
            entity = entities.get(i);
            body = ComponentMappers.PHYSIC.get(entity).body;
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
        physicComponent.init(body);
    }

    @Override
    public void entityRemoved(Entity entity)
    {

    }
}
