package ru.kiripu.lifeinspace.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by kiripu on 17.01.2016.
 */
public class LocalDataStorage {
    private Preferences preferences;
    private static final String storageName = "liveInSpaceData";
    public LocalDataStorage()
    {
        preferences = Gdx.app.getPreferences(storageName);
    }

    public void saveInt(String key, int data)
    {
        preferences.putInteger(key, data);
        preferences.flush();
    }

    public void saveBoolean(String key, Boolean data)
    {
        preferences.putBoolean(key, data);
        preferences.flush();
    }

    public int getInt(String key) { return preferences.getInteger(key); }
    public Boolean getBoolean(String key) { return preferences.getBoolean(key); }
}
