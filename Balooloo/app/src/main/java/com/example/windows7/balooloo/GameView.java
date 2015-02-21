package com.example.windows7.balooloo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by Windows7 on 21.02.2015.
 */
public class GameView extends MainView implements View.OnTouchListener {
    private GameActivity game;

    private final int BackColor = Color.WHITE;

    private final Random random = new Random();

    private final Controller controller = new Controller();

    private final float radius = 32; // Get percent from window sizes?
    private final Source source = new Source(radius);
    private final Target target = new Target(2 * radius);

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

        this.setOnTouchListener(this);

        GameActivity.getScreenSize(getContext());

        this.InitGame();
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

        canvas.drawColor(this.BackColor);

        this.target.Draw(canvas, Color.RED);

        this.source.Draw(canvas, Color.BLUE);

        this.controller.Draw(canvas, Color.LTGRAY);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
//        switch (e.KeyData)
//        {
//            case Keys.Escape:
//                this.Close();
//                break;
//            case Keys.F5:
//                this.InitGame();
//                break;
//        }

//        private void Down()
//        {
//            this.controller.SetSource(e.X, e.Y);
//        }
//        private void Move()
//        {
//            this.controller.SetTarget(e.X, e.Y);
//        }
//        private void Up()
//        {
//            int x = this.controller.GetVectorX();
//            int y = this.controller.GetVectorY();
//            this.source.Push(x, y);
//            this.controller.Clear();
//        }

        return false;
    }
}
