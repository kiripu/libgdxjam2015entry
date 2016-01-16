package ru.kiripu.lifeinspace.managers;

/**
 * Created by kiripu on 16.01.2016.
 */
public class GameMaster
{
    private static GameMaster _instance;
    private float curOxygenProgress = 100f;
    private int gameType;
    private float gameTime;

    public static GameMaster getInstance()
    {
        if (_instance == null) _instance = new GameMaster();
        return _instance;
    }

    public void init(int gameType)
    {
        this.gameType = gameType;
        this.gameTime = 0;
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

    public String getGameTime()
    {
        int min = (int) gameTime / 60;
        int sec = (int) gameTime % 60;
        String minString = (min < 10) ? "0" + min : "" + min;
        String secString = (sec < 10) ? "0" + sec : "" + sec;
        return minString + ":" + secString;
    }

    public void updateGameTime(float deltaTime)
    {
        if (!gameOver()) gameTime += deltaTime;
    }

    public boolean gameOver()
    {
        return curOxygenProgress == 0;
    }


}
