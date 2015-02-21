package com.example.windows7.balooloo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Windows7 on 21.02.2015.
 */
public class MenuItem implements IDrawable {

    private Bitmap bmp;
    private Rect rect;

    public MenuItem(Bitmap bmp, Rect rect) {
        this.bmp = bmp;
        this.rect = rect;
    }

    public Rect getRect()
    {
        return this.rect;
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawBitmap(this.bmp,  null, this.rect, null);
    }
}
