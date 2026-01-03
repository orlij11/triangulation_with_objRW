package ru.vsu.cs.kiselev.cg;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class ObjTriangulationTest {

    private void testObjFile(String inputPath, String outputPath) throws Exception {
        File input = new File(inputPath);
        File output = new File(outputPath);

        String objContent = Files.readString(input.toPath());

        Model source = ObjReader.read(objContent);
        TriangulatedModel triangulated = new TriangulatedModel(source);

        ObjWriter.write(triangulated, output.getPath());

        assertTrue(output.exists(), "Файл не создан: " + outputPath);
        assertFalse(triangulated.polygons.isEmpty(),
                "Триангуляция не сработала: " + outputPath);
    }

    @Test
    void testTriangleObj() throws Exception {
        testObjFile("test-files/triangle.obj", "test-files/triangle_out.obj");
    }

    @Test
    void testSquareObj() throws Exception {
        testObjFile("test-files/square.obj", "test-files/square_out.obj");
    }

    @Test
    void testPentagonObj() throws Exception {
        testObjFile("test-files/pentagon.obj", "test-files/pentagon_out.obj");
    }
}
