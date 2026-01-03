package ru.vsu.cs.kiselev.cg;

import java.util.*;

public class TriangulatedModel extends Model {

    public TriangulatedModel(Model sourceModel) {
        this.vertices = new ArrayList<>(sourceModel.vertices);
        this.textureVertices = new ArrayList<>(sourceModel.textureVertices);
        this.normals = new ArrayList<>(sourceModel.normals);
        this.polygons = new ArrayList<>();

        triangulate(sourceModel.polygons);
    }

    private void triangulate(ArrayList<Polygon> sourcePolygons) {
        for (Polygon polygon : sourcePolygons) {

            int n = polygon.getVertexIndices().size();
            if (n < 3) continue;

            if (n == 3) {
                polygons.add(copyPolygon(polygon));
                continue;
            }

            int v0 = polygon.getVertexIndices().get(0);

            for (int i = 1; i < n - 1; i++) {
                Polygon tri = new Polygon();

                tri.setVertexIndices(new ArrayList<>(List.of(
                        v0,
                        polygon.getVertexIndices().get(i),
                        polygon.getVertexIndices().get(i + 1)
                )));

                if (!polygon.getTextureVertexIndices().isEmpty()) {
                    tri.setTextureVertexIndices(new ArrayList<>(List.of(
                            polygon.getTextureVertexIndices().get(0),
                            polygon.getTextureVertexIndices().get(i),
                            polygon.getTextureVertexIndices().get(i + 1)
                    )));
                }

                if (!polygon.getNormalIndices().isEmpty()) {
                    tri.setNormalIndices(new ArrayList<>(List.of(
                            polygon.getNormalIndices().get(0),
                            polygon.getNormalIndices().get(i),
                            polygon.getNormalIndices().get(i + 1)
                    )));
                }

                polygons.add(tri);
            }
        }
    }

    private Polygon copyPolygon(Polygon src) {
        Polygon p = new Polygon();
        p.setVertexIndices(new ArrayList<>(src.getVertexIndices()));
        p.setTextureVertexIndices(new ArrayList<>(src.getTextureVertexIndices()));
        p.setNormalIndices(new ArrayList<>(src.getNormalIndices()));
        return p;
    }
}
