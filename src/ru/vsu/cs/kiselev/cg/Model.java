package ru.vsu.cs.kiselev.cg;

import java.util.ArrayList;
import java.util.List;

public class Model {
    public ArrayList<Vector3f> vertices = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> polygons = new ArrayList<>();

    public Model() {}

    public Model(Model other) {
        this.vertices = new ArrayList<>(other.vertices);
        for (ArrayList<Integer> poly : other.polygons) {
            this.polygons.add(new ArrayList<>(poly));
        }
    }
}