package ru.kiripu.lifeinspace.world.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pool;
import ru.kiripu.lifeinspace.Main;

/**
 * Created by kiripu on 07.01.2016.
 */
public class AnimationComponent implements Component, Pool.Poolable {

    private static JsonValue map;

    public Animation animation;

    private ObjectMap animMap;

    public AnimationComponent()
    {
        if (map == null)
        {
            JsonReader jsonReader = new JsonReader();
            map = jsonReader.parse(Gdx.files.internal("animationConfigs.json"));
        }
    }

    @Override
    public void reset() {

    }

    public AnimationComponent init(String objectName, String defaultAnimName)
    {
        JsonValue child = map.get(objectName).child;
        String animationName;
        Animation animation;
        TextureAtlas atlas = Main.assetsController.getTextureAtlas();
        animMap = new ObjectMap();
        do
        {
            animationName = child.name;
            animation = new Animation(0.03f, atlas.findRegions(child.asString()));
            animation.setPlayMode(Animation.PlayMode.LOOP);
            animMap.put(animationName, animation);
            child = child.next();
        }
        while (child != null);
        this.animation = (Animation) animMap.get(defaultAnimName);
        return this;
    }
}
