package com.example.windows7.balooloo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Target extends Circle {
    public Target(float radius) {
        super(radius);
    }

    public void Draw(Canvas canvas, int color) {
        RectF rectF1 = new RectF(this.px - this.radius, this.py - this.radius, this.px + this.radius, this.py + this.radius);

        Paint paint1 = new Paint();
        paint1.setColor(color);
        paint1.setStyle(Paint.Style.STROKE);

        canvas.drawOval(rectF1, paint1);

        RectF rectF2 = new RectF(this.px - 0.5f * this.radius, this.py - 0.5f * this.radius, this.px + 0.5f * this.radius, this.py + 0.5f * this.radius);

        Paint paint2 = new Paint();
        paint2.setColor(color);
        paint2.setStyle(Paint.Style.FILL);

        canvas.drawOval(rectF2, paint2);
    }
}

