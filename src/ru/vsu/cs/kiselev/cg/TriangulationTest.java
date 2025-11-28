package ru.vsu.cs.kiselev.cg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

class TriangulationTest {

    @Test
    void testQuadTriangulation() {
        Model source = new Model();
        source.vertices.add(new Vector3f(0, 0, 0));
        source.vertices.add(new Vector3f(1, 0, 0));
        source.vertices.add(new Vector3f(1, 1, 0));
        source.vertices.add(new Vector3f(0, 1, 0));

        ArrayList<Integer> quad = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        source.polygons.add(quad);
        TriangulatedModel result = new TriangulatedModel(source);


        Assertions.assertEquals(4, result.vertices.size());


        Assertions.assertEquals(2, result.polygons.size());

        Assertions.assertEquals(Arrays.asList(0, 1, 2), result.polygons.get(0));
        Assertions.assertEquals(Arrays.asList(0, 2, 3), result.polygons.get(1));
    }
}