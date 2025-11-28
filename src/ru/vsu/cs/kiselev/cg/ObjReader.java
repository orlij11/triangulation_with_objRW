package ru.vsu.cs.kiselev.cg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ObjReader {

    /**
     * Статический метод для чтения и парсинга содержимого OBJ-файла.
     * @param fileContent Полное строковое содержимое OBJ-файла.
     * @return Заполненный объект Model.
     */
    public static Model read(String fileContent) {
        Model model = new Model();
        Scanner scanner = new Scanner(fileContent);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            String[] tokens = line.split("\\s+");
            if (tokens.length == 0) continue;

            String type = tokens[0];
            String[] data = Arrays.copyOfRange(tokens, 1, tokens.length);

            try {
                switch (type) {
                    case "v":
                        parseVertex(data, model);
                        break;
                    case "f":
                        parseFace(data, model);
                        break;
                }
            } catch (Exception e) {
                System.err.println("Ошибка парсинга строки: " + line + ". " + e.getMessage());
            }
        }
        scanner.close();
        return model;
    }

    private static void parseVertex(String[] data, Model model) {
        if (data.length < 3) {
            throw new IllegalArgumentException("Недостаточно данных для вершины.");
        }
        float x = Float.parseFloat(data[0]);
        float y = Float.parseFloat(data[1]);
        float z = Float.parseFloat(data[2]);
        model.vertices.add(new Vector3f(x, y, z));
    }

    private static void parseFace(String[] data, Model model) {
        if (data.length < 3) {
            throw new IllegalArgumentException("Полигон должен содержать минимум 3 вершины.");
        }

        ArrayList<Integer> polygon = new ArrayList<>();

        for (String facePart : data) {
            String[] indices = facePart.split("/");
            int vertexIndex = Integer.parseInt(indices[0]);

            if (vertexIndex > 0) {
                polygon.add(vertexIndex - 1);
            } else if (vertexIndex < 0) {
                throw new UnsupportedOperationException("Относительная индексация не поддерживается.");
            }
        }

        if (polygon.size() >= 3) {
            model.polygons.add(polygon);
        }
    }
}