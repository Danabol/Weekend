package com.example.windows7.balooloo;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Windows7 on 21.02.2015.
 */
public class MainMenuView extends MainView implements View.OnTouchListener {


    List<MenuItem> menuItems;


    public MainMenuView(Context context) {
        super(context);

        this.menuItems =  new ArrayList<MenuItem>();

        float scaleX = GameActivity.scaleX;
        float scaleY = GameActivity.scaleY;

        Rect rectPlay = new Rect();
        rectPlay.top = (int)(650 * scaleY);
        rectPlay.left = (int)(200 * scaleX);
        rectPlay.right = (int)(600 * scaleX);
        rectPlay.bottom = (int)(730 * scaleY);
        this.menuItems.add(new MenuItem(ImageManager.NewGame_BMP, rectPlay));

        Rect rectScore = new Rect();
        rectScore.top = (int)(750 * scaleY);
        rectScore.left = (int)(200 * scaleX);
        rectScore.right = (int)(600 * scaleX);
        rectScore.bottom = (int)(830 * scaleY);

        this.menuItems.add(new MenuItem(ImageManager.Score_BMP, rectScore));

        Rect rectSound = new Rect();
        rectSound.left = (int)(100 * scaleX);
        rectSound.right = (int)(175 * scaleX);
        rectSound.top = (int)(1100 * scaleY);
        rectSound.bottom = (int)(1175 * scaleY);

        this.menuItems.add(new MenuItem(ImageManager.Sound_BMP, rectSound));

        setOnTouchListener(this);

    }


   private void optionMenuSelected(float  x, float y) {

       int index = -1;

       for (int i = 0; i < this.menuItems.size(); i++) {
           if (GameActivity.inBounds(this.menuItems.get(i).getRect(), x, y)) {
               index = i;
               break;
           }
       }

       switch (index) {
           case 0: // play
               GameActivity.game.changeScreen(Screens.GAME);
               break;

           case 1: // score
               GameActivity.game.changeScreen(Screens.SCORE);
               break;

           case 2:// sound
               if (this.menuItems.get(index).getImage() == ImageManager.Sound_BMP) {
                   this.menuItems.get(index).setImage(ImageManager.SoundOff_BMP);
               } else {
                   this.menuItems.get(index).setImage(ImageManager.Sound_BMP);
               }
               break;
           default:
               break;
       }
   }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (canvas == null) {
            Log.d("MAINMENU", "NULL CANVAS");
            return;
        }

        canvas.drawColor(Color.GREEN);

        for (int i = 0; i < this.menuItems.size(); i++) {
            this.menuItems.get(i).draw(canvas);
        }


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {

                float x =  event.getX();
                float y = event.getY();
                this.optionMenuSelected(x,y);

                break;
            }
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
        }

        return true;
    }
}
