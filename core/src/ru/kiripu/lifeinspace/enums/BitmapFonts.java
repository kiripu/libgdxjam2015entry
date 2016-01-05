package ru.kiripu.lifeinspace.enums;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by kiripu on 06.01.2016.
 */
public class BitmapFonts {
    public static BitmapFont SPACE_AGE = null;
    public static BitmapFont OLIVER = null;

    public static void create()
    {
        SPACE_AGE = new BitmapFont(Gdx.files.internal("fonts/space_age.fnt"),
                Gdx.files.internal("fonts/space_age.png"), false);

        OLIVER = new BitmapFont(Gdx.files.internal("fonts/oliver.fnt"),
                Gdx.files.internal("fonts/oliver.png"), false);
    }

    public static void dispose()
    {
        SPACE_AGE.dispose();
        OLIVER.dispose();
    }
}
