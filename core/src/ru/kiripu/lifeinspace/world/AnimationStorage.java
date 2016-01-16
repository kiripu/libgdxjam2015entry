package ru.kiripu.lifeinspace.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import ru.kiripu.lifeinspace.Main;
import ru.kiripu.lifeinspace.world.data.AnimationData;

/**
 * Created by kiripu on 14.01.2016.
 */
public class AnimationStorage {

    private static JsonValue map;
    private static AnimationStorage _instance;

    public static AnimationStorage getInstance()
    {
        if (_instance == null)
        {
            JsonReader jsonReader = new JsonReader();
            map = jsonReader.parse(Gdx.files.internal("animationConfigs.json"));
            _instance = new AnimationStorage();
        }
        return _instance;
    }

    public AnimationData getAnimation(String objectName, String animationName, Animation.PlayMode playMode)
    {
        JsonValue child = map.get(objectName).get(animationName);
        TextureAtlas atlas = Main.assetsController.getTextureAtlas();
        Animation animation = new Animation(0.03f, atlas.findRegions(child.asString()));
        animation.setPlayMode(playMode);
        AnimationData animationData = new AnimationData();
        animationData.animation = animation;
        if (animationName == "die_2") animationData.offset = new Vector2(-78, -65);
        else animationData.offset = Vector2.Zero;
        return animationData;
    }
}
