package com.example.windows7.balooloo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Random;

public class Circle {
    protected float radius;
    protected float px, py;

    public Circle(float radius) {
        this.radius = radius;
    }

    public void Init(Random random, float maxX, float maxY) {
        this.px = this.radius + (maxX - 2 * this.radius) * random.nextFloat();
        this.py = this.radius + (maxY - 2 * this.radius) * random.nextFloat();
    }

    public void Draw(Canvas canvas, int color) {
        RectF rectF = new RectF(this.px - this.radius, this.py - this.radius, 2 * this.radius, 2 * this.radius);

        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawOval(rectF, paint);
    }

    public static double Distance(Circle source, Circle target) {
        double x = target.px - source.px;
        double y = target.py - source.py;
        return Math.sqrt(x * x + y * y) - target.radius - source.radius;
    }
}
