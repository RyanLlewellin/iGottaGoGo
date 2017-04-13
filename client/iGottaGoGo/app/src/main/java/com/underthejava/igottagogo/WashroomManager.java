package com.underthejava.igottagogo;

import java.util.ArrayList;

/**
 * Created by Ryan on 2017-03-30.
 */

public class WashroomManager
{
    private static WashroomManager instance;

    ArrayList<Washroom> washrooms = new ArrayList<Washroom>();

    /* Static 'instance' method */
    public static WashroomManager getInstance( ) {
        if(instance == null) {
            instance = new WashroomManager();
        }
        return instance;
    }

    private WashroomManager() {
    }

    public void addWashroom(Washroom newWashroom) {
        washrooms.add(newWashroom);
    }

    public ArrayList<Washroom> getWashrooms() {
        return washrooms;
    }

}
