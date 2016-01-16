package ru.kiripu.lifeinspace.world.data;

/**
 * Created by kiripu on 16.01.2016.
 */
public class OxygenModificator
{
    public static final OxygenModificator PERMANENT_DEFAULT = new OxygenModificator("default", -1, true);
    public static final OxygenModificator PERMANENT_JETPACK_USE = new OxygenModificator("jetpack", -1, true);
    public static final OxygenModificator PERMANENT_SAFE_CAPSUEL_USE = new OxygenModificator("safeCapsule", 20, true);

    public String name;
    public int value;
    public Boolean permanent;

    public OxygenModificator(String name, int value, Boolean permanent)
    {
        this.name = name;
        this.value = value;
        this.permanent = permanent;
    }

    public static OxygenModificator createInstantAsteroidModificator()
    {
        return new OxygenModificator("asteroid", -5, false);
    }


}
