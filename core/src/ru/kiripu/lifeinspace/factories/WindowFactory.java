package ru.kiripu.lifeinspace.factories;

import com.badlogic.gdx.scenes.scene2d.Stage;
import ru.kiripu.lifeinspace.enums.WindowType;
import ru.kiripu.lifeinspace.windows.GameOverWindow;
import ru.kiripu.lifeinspace.windows.PauseWindow;

/**
 * Created by kiripu on 16.01.2016.
 */
public class WindowFactory
{
    public static void createWindow(Stage stage, int windowType)
    {
        switch (windowType)
        {
            case WindowType.GAME_OVER:
                new GameOverWindow(stage);
                break;
            case WindowType.PAUSE:
                new PauseWindow(stage);
                break;
        }

    }
}
