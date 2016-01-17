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

    public void saveData(String key, String data)
    {
        preferences.putString(key, data);
        preferences.flush();
    }

    public String getData(String key)
    {
        return preferences.getString(key);
    }
}
