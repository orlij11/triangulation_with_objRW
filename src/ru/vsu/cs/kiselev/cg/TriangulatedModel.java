package ru.vsu.cs.kiselev.cg;

import java.util.*;

public class TriangulatedModel extends Model {


    public TriangulatedModel(Model source) {
        this.vertices = source.vertices;
        this.textureVertices = source.textureVertices;
        this.normals = source.normals;
        this.polygons = new ArrayList<>();

        triangulate(source.polygons);
    }

    private void triangulate(ArrayList<Polygon> src) {
        for (Polygon p : src) {

            int n = p.getVertexIndices().size();

            if (n < 3) continue;

            if (n == 3) {
                polygons.add(p);
                continue;
            }

            int v0 = p.getVertexIndices().get(0);

            for (int i = 1; i < n - 1; i++) {
                Polygon tri = new Polygon();

                ArrayList<Integer> v = new ArrayList<>(3);
                v.add(v0);
                v.add(p.getVertexIndices().get(i));
                v.add(p.getVertexIndices().get(i + 1));
                tri.setVertexIndices(v);

                polygons.add(tri);
            }
        }
    }
}
