package ru.kiripu.lifeinspace.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.kiripu.lifeinspace.enums.BitmapFonts;

/**
 * Created by kiripu on 05.01.2016.
 */
public class AssetsController
{
    private TextureAtlas atlas;

    public void init()
    {
        atlas = new TextureAtlas(Gdx.files.internal("gameSpriteSheet.atlas"));
        BitmapFonts.create();
    }

    public void dispose()
    {
        atlas.dispose();
        BitmapFonts.dispose();
    }

    public TextureAtlas getTextureAtlas()
    {
        return atlas;
    }
}
