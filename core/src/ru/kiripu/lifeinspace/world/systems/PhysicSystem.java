package ru.kiripu.lifeinspace.world.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import ru.kiripu.lifeinspace.Main;
import ru.kiripu.lifeinspace.world.BodyEditorLoader;
import ru.kiripu.lifeinspace.world.ComponentMappers;
import ru.kiripu.lifeinspace.world.components.PhysicComponent;
import ru.kiripu.lifeinspace.world.components.PositionComponent;

/**
 * Created by kiripu on 06.01.2016.
 */
public class PhysicSystem extends EntitySystem
{
    private BodyEditorLoader bodyEditorLoader;
    private World world;
    private Box2DDebugRenderer debugRenderer;

    private Family family;
    private ImmutableArray<Entity> entities;

    @Override
    public void addedToEngine(Engine engine)
    {
        Box2D.init();
        world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();
        bodyEditorLoader = new BodyEditorLoader(Gdx.files.internal("physics/liveInSpace"));

        family = Family.all(PhysicComponent.class, PositionComponent.class).get();
        entities = engine.getEntitiesFor(family);

        int length = entities.size();
        Entity entity;
        PositionComponent position;
        PhysicComponent physic;

        for (int i = 0; i < length; ++i)
        {
            entity = entities.get(i);
            position = ComponentMappers.POSITION.get(entity);
            physic = ComponentMappers.PHYSIC.get(entity);

            BodyDef bd = new BodyDef();
            bd.position.set(position.x, position.y);
            bd.type = BodyDef.BodyType.DynamicBody;

            FixtureDef fd = new FixtureDef();
            fd.density = 1;
            fd.friction = 0.5f;
            fd.restitution = 0.3f;

            physic.body = world.createBody(bd);
            bodyEditorLoader.attachFixture(physic.body, physic.bodyName, fd, 50f);
        }
    }

    @Override
    public void update(float deltaTime)
    {
        world.step(deltaTime, 6, 2);
        debugRenderer.render(world, Main.camera.combined);
    }
}
