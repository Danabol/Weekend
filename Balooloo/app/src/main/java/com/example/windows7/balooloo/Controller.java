package com.example.windows7.balooloo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class Controller {
    private boolean isRun = false;
    private Point point;
    private Point point_;

    public int GetVectorX() {
        return this.point_.x - this.point.x;
    }

    public int GetVectorY() {
        return this.point_.y - this.point.y;
    }

    public void SetSource(int x, int y) {
        this.isRun = true;
        this.point = new Point(x, y);
        this.point_ = new Point(x, y);
    }

    public void SetTarget(int x, int y) {
        if (this.isRun) {
            this.point_ = new Point(x, y);
        }
    }

    public void Clear() {
        this.isRun = false;
        this.point = new Point();
        this.point_ = new Point();
    }

    public void Draw(Canvas canvas, int color) {
        if (this.isRun) {
            Paint paint = new Paint();
            paint.setColor(color);
            paint.setStyle(Paint.Style.FILL);

            canvas.drawLine(this.point.x, this.point.y, this.point_.x, this.point_.y, paint);
        }
    }
}
