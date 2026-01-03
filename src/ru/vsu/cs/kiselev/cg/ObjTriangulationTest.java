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
    @Test
    void testFemaleObj() throws Exception {
        testObjFile("test-files/WrapedFemale.obj", "test-files/WrapedFemale_out.obj");
    }
    @Test
    void testSkullObj() throws Exception {
        testObjFile("test-files/WrapedSkull.obj", "test-files/WrapedSkull_out.obj");
    }

    @Test
    void testBodyObj() throws Exception {
        testObjFile("test-files/WrapBody.obj", "test-files/WrapBody_out.obj");
    }

    @Test
    void testWrapHandObj() throws Exception {
        testObjFile("test-files/WrapHand.obj", "test-files/WrapHand_out.obj");
    }

    @Test
    void testWrapHeadObj() throws Exception {
        testObjFile("test-files/WrapHead.obj", "test-files/WrapHead_out.obj");
    }

    @Test
    void testWrapJawObj() throws Exception {
        testObjFile("test-files/WrapJaw.obj", "test-files/WrapJaw_out.obj");
    }

    @Test
    void testWrapHeadWithObj() throws Exception {
        testObjFile("test-files/WrapHeadWithMouthSocket.obj", "test-files/WrapHeadWith_out.obj");
    }

}


