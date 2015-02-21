package com.example.windows7.balooloo;

public class Source extends Circle {
    private final float a = -1.0f;
    private float v;
    private float vx, vy;

    public Source(float radius) {
        super(radius);
    }

    public void Push(float vx, float vy) {
        this.v = (float) Math.sqrt(vx * vx + vy * vy);
        if (this.v > 0.0f) {
            this.vx = vx / this.v;
            this.vy = vy / this.v;
        }
    }

    public void Move(float maxX, float maxY, long elapsedMilliseconds) {
        this.px += this.v * this.vx * elapsedMilliseconds;
        this.py += this.v * this.vy * elapsedMilliseconds;

        this.v = this.v + a * elapsedMilliseconds > 0 ? this.v + a * elapsedMilliseconds : 0.0f;

        boolean isRun;
        do {
            isRun = false;
            if (this.px - this.radius < 0) {
                this.px = 2 * this.radius - this.px;
                this.vx = +Math.abs(this.vx);
                isRun = true;
            }
            if (this.px + this.radius > maxX) {
                this.px = 2 * (maxX - this.radius) - this.px;
                this.vx = -Math.abs(this.vx);
                isRun = true;
            }

            if (this.py - this.radius < 0) {
                this.py = 2 * this.radius - this.py;
                this.vy = +Math.abs(this.vy);
                isRun = true;
            }
            if (this.py + this.radius > maxY) {
                this.py = 2 * (maxY - this.radius) - this.py;
                this.vy = -Math.abs(this.vy);
                isRun = true;
            }
        } while (isRun);
    }

    public boolean IsMove() {
        return this.v != 0.0f;
    }

    public float CalculateTime(float s) {
        if (s > 2 * this.v * this.v / this.a) {
            return Float.POSITIVE_INFINITY;
        }
        float t = (-this.v + (float) Math.sqrt(this.v * this.v + 4 * this.a * s)) / 2 / this.a;
        return t;
    }
}
