package ru.kiripu.lifeinspace.managers;

/**
 * Created by kiripu on 17.01.2016.
 */
public class GameTimeController
{
    private static GameTimeController _instance;
    private float timeModifer;

    public static GameTimeController getInstance()
    {
        if (_instance == null) _instance = new GameTimeController();
        return _instance;
    }

    public void setTimeModifer(float value)
    {
        this.timeModifer = value;
    }

    public float getTimeModifer() {
        return timeModifer;
    }
}
