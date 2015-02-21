package com.example.windows7.balooloo;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import java.util.Hashtable;


public class GameActivity extends ActionBarActivity {

    public static float TARGET_WIDTH = 800;
    public static float TARGET_HEIGHT = 1280;
    public static float currentScreenWidth;
    public static float currentScreenHeight;
    public static float scaleX;
    public static float scaleY;



    //loop variables
    public static GameManager gameLoopThread;

    private MainView currentView;

    public static GameActivity game;

    private Hashtable<Integer,MainView> viewDictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        game = this;
     //   ImageManager.init();

        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();


                requestWindowFeature(Window.FEATURE_NO_TITLE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                getScreenSize(getApplicationContext());
            }
        };

        thread.start();

        this.viewDictionary = new Hashtable();



        GameView gameView = new GameView(getApplicationContext(), this);
        this.game = this;
        this.viewDictionary.put(Screens.GAME,gameView);
        this.viewDictionary.put(Screens.MAIN_MENU,new MainMenuView(getApplicationContext()));
        this.viewDictionary.put(Screens.SCORE, new HightScoreView(getApplicationContext()));


        GameActivity.gameLoopThread = new GameManager();

        synchronized (this)
        {

            this.changeScreen(Screens.MAIN_MENU);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();

        boolean retry = true;
        GameActivity.gameLoopThread.setRunning(false);
        while (retry) {
            try {
                GameActivity.gameLoopThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        GameActivity.gameLoopThread.setRunning(true);
        GameActivity.gameLoopThread.start();
    }


    public static void getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        currentScreenWidth = size.x;
        currentScreenHeight = size.y;

        scaleY = currentScreenHeight / TARGET_HEIGHT;
        scaleX = currentScreenWidth / TARGET_WIDTH;

    }

    public static boolean inBounds(Rect rect, float x, float y)
    {
        return rect.top <= y && rect.bottom >= y && rect.left <= x && rect.right >= x;
    }

    public void changeScreen(int screen_id)
    {


        this.currentView = this.viewDictionary.get(screen_id);
        if(currentView == null)
        {
            Log.d("GAME ACTIVITY", "NULL VIEW");
        }
        setContentView(currentView);
        GameActivity.gameLoopThread.setView(currentView);

    }
}
