package ru.vsu.cs.kiselev.cg;

public class Vector3f {
    public float x;
    public float y;
    public float z;

    @Override
    public String toString() {
        return String.format("(%.6f, %.6f)", x, y);
    }


    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
