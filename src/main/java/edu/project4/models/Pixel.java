package edu.project4.models;

public class Pixel {
    private int r;
    private int g;
    private int b;
    private int hitCount;
    private double normal;

    public Pixel(int r, int g, int b, int hitCount) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.hitCount = hitCount;
        this.normal = 0.0;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    public void addHit() {
        ++this.hitCount;
    }
}
