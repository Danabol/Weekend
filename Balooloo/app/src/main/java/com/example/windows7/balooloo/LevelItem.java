package com.example.windows7.balooloo;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by Windows7 on 22.02.2015.
 */
public class LevelItem {

    private Bitmap itemBmp;
    private Rect itemRect;
    private int itemType;


    public LevelItem(Bitmap bmp, Rect rect, int type )
    {
        this.itemBmp = bmp;
        this.itemRect = rect;
        this.itemType = type;
    }

    public Rect getRect() {
        return this.itemRect;
    }



}
