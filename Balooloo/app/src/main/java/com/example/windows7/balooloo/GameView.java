package com.example.windows7.balooloo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Windows7 on 21.02.2015.
 */
public class GameView extends MainView  {
    private GameActivity game;

    private final int BackColor = Color.WHITE;

    private final Random random = new Random();

    private final Controller controller = new Controller();

    private final float radius = 32; // Get percent from window sizes?
    private final Source source = new Source(radius);
    private final Target target = new Target(2 * radius);

    private List<MenuItem> menuItems;

    //background
    Rect backgroundRect = new Rect(0,0,(int)GameActivity.currentScreenWidth, (int)GameActivity.currentScreenHeight);
    Bitmap backgroundBmp =  ImageManager.Grass_BMP;


    private void InitGame() {
        this.source.Init(this.random, GameActivity.currentScreenWidth, GameActivity.currentScreenHeight);

        this.InitRound();
    }

    private void InitRound() {
        this.target.Init(this.random, GameActivity.currentScreenWidth, GameActivity.currentScreenHeight);
    }

    public GameView(Context context, GameActivity game) {
        super(context);

        this.game = game;
        this.menuItems = new ArrayList<MenuItem>();

        GameActivity.getScreenSize(getContext());

        this.InitGame();
        this.InitMenuElements();


    }

    @Override
    public void update(long elapsedMilliseconds) {
        super.update(elapsedMilliseconds);

        if (GameActivity.currentScreenWidth < radius || GameActivity.currentScreenHeight < radius) {
            return;
        }

        this.source.Move(GameActivity.currentScreenWidth, GameActivity.currentScreenHeight, elapsedMilliseconds);

        boolean isMove = this.source.IsMove();
        boolean isTarget = Circle.Distance(this.source, this.target) < 0;
        if (!isMove && isTarget) {
            this.InitRound();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (canvas == null) {
            return;
        }

        canvas.drawBitmap(backgroundBmp, null, backgroundRect, null);
       // canvas.drawColor(this.BackColor);

        for (int i = 0; i< this.menuItems.size(); i++) {
            this.menuItems.get(i).draw(canvas);
        }

        this.target.Draw(canvas, Color.RED);

        this.source.Draw(canvas, Color.BLUE);

        this.controller.Draw(canvas, Color.LTGRAY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        int eventX =  (int)event.getX();
        int eventY = (int)event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                this.controller.SetSource(eventX, eventY);

                this.optionMenuSelected(eventX, eventY);
                break;
            }
            case MotionEvent.ACTION_UP:
                int x = this.controller.GetVectorX();
                int y = this.controller.GetVectorY();
                this.source.Push(x, y);
                this.controller.Clear();

                break;
            case MotionEvent.ACTION_MOVE:
                this.controller.SetTarget(eventX, eventY);


                break;
        }

        return true;
    }


    //menu

    private void InitMenuElements()
    {

        float scaleX =  GameActivity.scaleX;
        float scaleY = GameActivity.scaleY;

        Rect rectRestart = new Rect();

        rectRestart.left = (int)(10 * scaleX);
        rectRestart.top = (int)(10 * scaleY);
        rectRestart.right = (int)(90 * scaleX);
        rectRestart.bottom = (int)(80 * scaleY);


        Rect rectMenu = new Rect();

        rectMenu.left = (int)(110 * scaleX);
        rectMenu.top = (int)(10 * scaleY);
        rectMenu.right = (int)(180 * scaleX);
        rectMenu.bottom = (int)(80 * scaleY);

        Rect rectNumber = new Rect();
        rectNumber.top = (int)(10 * scaleY);
        rectNumber.bottom = (int)(80 * scaleY);
        rectNumber.right = (int)(780 * scaleX);
        rectNumber.left = (int)(700 * scaleX);


        Rect rectBear = new Rect();
        rectBear.top = (int)(10 * scaleY);
        rectBear.bottom = (int)(80 * scaleY);
        rectBear.right = (int)(680 * scaleX);
        rectBear.left = (int)(600 * scaleX);

        this.menuItems.add(new MenuItem(ImageManager.MenuRestart_BMP, rectRestart));
        this.menuItems.add(new MenuItem(ImageManager.MenuItem_BMP, rectMenu));

        this.menuItems.add(new MenuItem(ImageManager.Bear_BMP, rectBear));
        this.menuItems.add(new MenuItem(ImageManager.NumberOne_BMP, rectNumber));


    }

    private void optionMenuSelected(float  x, float y)
    {

        int index = -1;

        for (int i = 0; i< this.menuItems.size(); i++) {
            if (GameActivity.inBounds(this.menuItems.get(i).getRect(), x, y)) {
                index = i;
                break;
            }
        }

        switch(index)
        {
            case 0: // restart

                break;

            case 1: // mainmenu
                GameActivity.game.changeScreen(Screens.MAIN_MENU);
                break;

            default:break;


        }
    }
}
