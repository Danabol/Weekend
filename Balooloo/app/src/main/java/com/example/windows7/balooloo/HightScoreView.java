package com.example.windows7.balooloo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Windows7 on 21.02.2015.
 */
public class HightScoreView extends MainView implements View.OnTouchListener {


    public HightScoreView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    canvas.drawColor(Color.MAGENTA);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
