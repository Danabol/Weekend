package com.example.windows7.balooloo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by Windows7 on 21.02.2015.
 */
public class GameView extends MainView implements View.OnTouchListener {


    private  GameActivity game;

    //variables
    private Random random = new Random();

    private float px, py;
    private float vx, vy;
    private float radius = 32; // Get percent from window sizes?

    private float a = 0.96f; // Find correct value and expresion.

    private float tx, ty;

    private float eps = 0.5f;

    private int mx0, my0, mx1, my1;


    private void Init()
    {
        float Width =  GameActivity.currentScreenWidth;
        float Height = GameActivity.currentScreenHeight;

        this.px = this.radius + (Width - 2 * this.radius) * (float)this.random.nextDouble();
        this.py = this.radius + (Height - 2 * this.radius) * (float)this.random.nextDouble();

        this.tx = 2 * this.radius + (Width - 4 * this.radius) * (float)this.random.nextDouble();
        this.ty = 2 * this.radius + (Height - 4 * this.radius) * (float)this.random.nextDouble();
    }

    private void Finish()
    {
        double x = tx - px;
        double y = ty - py;
        double d = Math.sqrt(x * x + y * y) - 3 * radius;

        if (d < 0)
        {
            this.Init();
        }
    }

    public GameView(Context context, GameActivity game) {
        super(context);

        this.Init();
        this.game = game;

        setOnTouchListener(this);

        GameActivity.getScreenSize(getContext());
    }

    @Override
    public void update(long elapsedMilliseconds) {
        super.update(elapsedMilliseconds);


    }

    private void DrawFieldItems(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        RectF rectBlue = new RectF(this.px - this.radius, this.py - this.radius, 2 * this.radius, 2 * this.radius);

        canvas.drawOval(rectBlue, paint);

      // e.Graphics.FillEllipse(Brushes.Blue, this.px - this.radius, this.py - this.radius, 2 * this.radius, 2 * this.radius);

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);

        //draw target
        RectF rectRed =  new RectF(this.tx - 2 * this.radius, this.ty - 2 * this.radius, 4 * this.radius, 4 * this.radius);
        canvas.drawOval(rectRed, paint);

        //fill target
        paint.setStyle(Paint.Style.FILL);
        rectRed =  new RectF( this.tx - this.radius, this.ty - this.radius, 2 * this.radius, 2 * this.radius);
        canvas.drawOval(rectRed, paint);

      //  e.Graphics.DrawEllipse(Pens.Red, this.tx - 2 * this.radius, this.ty - 2 * this.radius, 4 * this.radius, 4 * this.radius);
       //e.Graphics.FillEllipse(Brushes.Red, this.tx - this.radius, this.ty - this.radius, 2 * this.radius, 2 * this.radius);

        // draw trjectory
        paint.setColor(Color.BLACK);
       // canvas.drawLine(mx0, this.my0, this.mx1, this.my1);


    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (canvas == null) return;

        canvas.drawColor(Color.WHITE);
        DrawFieldItems(canvas);



    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
