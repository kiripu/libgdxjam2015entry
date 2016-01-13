package ru.kiripu.lifeinspace.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import ru.kiripu.lifeinspace.Main;

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

    public Animation getAnimation(String objectName, String animationName)
    {
        JsonValue child = map.get(objectName).get(animationName);
        TextureAtlas atlas = Main.assetsController.getTextureAtlas();
        Animation animation = new Animation(0.03f, atlas.findRegions(child.asString()));
        animation.setPlayMode(Animation.PlayMode.LOOP);
        return animation;
    }
}
