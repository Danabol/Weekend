package com.example.windows7.balooloo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Windows7 on 22.02.2015.
 */
public class LevelManager {

    private List<LevelItem> listItems = new ArrayList<LevelItem>();


    public LevelManager()
    {

    }

    public List<LevelItem> getLevelItems(int level)
    {
        return this.listItems;
    }
}
