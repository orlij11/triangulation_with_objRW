package ru.vsu.cs.kiselev.cg;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TriangulatedModelTest {

    @Test
    void testTriangleRemainsTriangle() {
        Model model = new Model();
        model.vertices.add(new Vector3f(0, 0, 0));
        model.vertices.add(new Vector3f(1, 0, 0));
        model.vertices.add(new Vector3f(0, 1, 0));

        ArrayList<Integer> triangle = new ArrayList<>();
        triangle.add(0);
        triangle.add(1);
        triangle.add(2);
        model.polygons.add(triangle);

        TriangulatedModel triangulated = new TriangulatedModel(model);

        assertEquals(1, triangulated.polygons.size());
        assertEquals(3, triangulated.polygons.get(0).size());
    }

    @Test
    void testQuadTriangulation() {
        Model model = new Model();
        model.vertices.add(new Vector3f(0, 0, 0));
        model.vertices.add(new Vector3f(1, 0, 0));
        model.vertices.add(new Vector3f(1, 1, 0));
        model.vertices.add(new Vector3f(0, 1, 0));

        ArrayList<Integer> quad = new ArrayList<>();
        quad.add(0);
        quad.add(1);
        quad.add(2);
        quad.add(3);
        model.polygons.add(quad);

        TriangulatedModel triangulated = new TriangulatedModel(model);

        assertEquals(2, triangulated.polygons.size());

        for (ArrayList<Integer> poly : triangulated.polygons) {
            assertEquals(3, poly.size());
        }
    }

    @Test
    void testPentagonTriangulation() {
        Model model = new Model();
        for (int i = 0; i < 5; i++) {
            model.vertices.add(new Vector3f(i, 0, 0));
        }

        ArrayList<Integer> pentagon = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            pentagon.add(i);
        }
        model.polygons.add(pentagon);

        TriangulatedModel triangulated = new TriangulatedModel(model);

        assertEquals(3, triangulated.polygons.size());
    }

    @Test
    void testDegeneratePolygonIgnored() {
        Model model = new Model();
        model.vertices.add(new Vector3f(0, 0, 0));
        model.vertices.add(new Vector3f(1, 0, 0));

        ArrayList<Integer> line = new ArrayList<>();
        line.add(0);
        line.add(1);
        model.polygons.add(line);

        TriangulatedModel triangulated = new TriangulatedModel(model);

        assertEquals(0, triangulated.polygons.size());
    }
}
