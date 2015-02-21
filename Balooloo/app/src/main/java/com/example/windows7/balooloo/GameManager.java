package com.example.windows7.balooloo;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by Windows7 on 21.02.2015.
 */
public class GameManager extends Thread {
    /**
     * Объект класса
     */
    private MainView view = null;
    private MainView bufferView = null;


    /**
     * Переменная задавания состояния потока рисования
     */
    private boolean running = false;

    public void setView(MainView view) {
        if(bufferView== null)
        {
            Log.d("GAME MANAGER", "NULL BUFFER (METHOD)");
        }
        this.bufferView = view;
    }

    public void setRunning(boolean run) {
        running = run;
    }


    public void run() {
        long curTime = System.currentTimeMillis();
        long oldTime;


        this.running = true;
        while (this.running) {
            try {


                oldTime = curTime;
                curTime = System.currentTimeMillis();

                if (this.view != null) {

                    if (!view.getHolder().getSurface().isValid()) {
                        Log.d("GAME MANAGER", "ZERO HOLDER");
                    }

                    SurfaceHolder surfaceHolder = this.view.getHolder();

                    Canvas canvas = null;
                    try {
                        canvas = surfaceHolder.lockCanvas();
                        if (canvas == null) {
                            Log.d("GAME MANAGER", "NULL CANVAS");
                        } else {
                            Log.d("GAME MANAGER", "NOT NULL CANVAS");
                        }
                        synchronized (surfaceHolder) {
                            this.view.update(curTime - oldTime);
                            this.view.onDraw(canvas);
                            // surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    } finally {
                        if (canvas != null) {
                            surfaceHolder.unlockCanvasAndPost(canvas);

                        }
                    }
                }

                this.view = this.bufferView;
                if (bufferView == null) {
                    Log.d("GAME MANAGER", "NULL BUFFER");
                }

            } catch (Exception ex) {
                Log.d("GAME MANAGER", ex.getMessage());
            }


        }

    }
}
