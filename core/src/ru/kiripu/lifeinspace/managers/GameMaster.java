package ru.kiripu.lifeinspace.managers;

/**
 * Created by kiripu on 16.01.2016.
 */
public class GameMaster
{
    private static GameMaster _instance;
    private float curOxygenProgress = 100f;
    private int gameType;

    public static GameMaster getInstance()
    {
        if (_instance == null) _instance = new GameMaster();
        return _instance;
    }

    public void setGameType(int gameType)
    {
        this.gameType = gameType;
    }

    public int getGameType()
    {
        return gameType;
    }

    public void setOxygenProgress(float value)
    {
        curOxygenProgress = value;
    }

    public float getOxygenProgress()
    {
        return curOxygenProgress;
    }

    public boolean gameOver()
    {
        return curOxygenProgress == 0;
    }


}
