package com.example.windows7.balooloo;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Windows7 on 21.02.2015.
 */
public class MainView extends SurfaceView implements IUpdatable {


    protected SurfaceHolder holder;

    public MainView(Context context) {
        super(context);


        // GameActivity.gameLoopThread = new GameManager();

        holder = getHolder();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }

    @Override
    public void update(long elapsedMilliseconds) {

    }
}
