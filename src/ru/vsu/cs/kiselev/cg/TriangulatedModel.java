package ru.vsu.cs.kiselev.cg;

import java.util.ArrayList;
import java.util.List;

public class TriangulatedModel extends Model {


    public TriangulatedModel(Model sourceModel) {
        this.vertices = new ArrayList<>(sourceModel.vertices);
        this.polygons = new ArrayList<>();
        triangulate(sourceModel.polygons);
    }

    private void triangulate(ArrayList<ArrayList<Integer>> sourcePolygons) {
        for (ArrayList<Integer> polygon : sourcePolygons) {
            if (polygon.size() < 3) {
                continue;
            }

            if (polygon.size() == 3) {
                this.polygons.add(new ArrayList<>(polygon));
            } else {

                Integer v0 = polygon.get(0); // Первая вершина - "центр веера"

                for (int i = 1; i < polygon.size() - 1; i++) {
                    ArrayList<Integer> triangle = new ArrayList<>();
                    triangle.add(v0);
                    triangle.add(polygon.get(i));
                    triangle.add(polygon.get(i + 1));

                    this.polygons.add(triangle);
                }
            }
        }
    }
}