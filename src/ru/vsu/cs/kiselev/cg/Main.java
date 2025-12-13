package ru.vsu.cs.kiselev.cg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        try {
            String fileContent = Files.readString(Path.of("C:/Users/orlij/Desktop/test.obj"));
            Model originalModel = ObjReader.read(fileContent);

            System.out.println("Исходные полигоны: " + originalModel.polygons.size());

            TriangulatedModel triangulatedModel = new TriangulatedModel(originalModel);

            System.out.println("Треугольники после обработки: " + triangulatedModel.polygons.size());

            ObjWriter.write("cube_triangulated.obj", triangulatedModel);

            System.out.println("Файл успешно сохранен!");

        } catch (IOException e) {
            System.err.println("Ошибка при чтении или записи файла.");
            e.printStackTrace();
        }
    }
}