package ru.vsu.cs.kiselev.cg;

import java.io.*;
import java.util.ArrayList;

public class ObjReader {

    public static Model read(File file) throws IOException {
        Model model = new Model();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("v ")) {
                    String[] parts = line.split("\\s+");
                    float x = Float.parseFloat(parts[1]);
                    float y = Float.parseFloat(parts[2]);
                    float z = Float.parseFloat(parts[3]);
                    model.vertices.add(new Vector3f(x, y, z));
                }

                if (line.startsWith("f ")) {
                    String[] parts = line.split("\\s+");
                    ArrayList<Integer> polygon = new ArrayList<>();

                    for (int i = 1; i < parts.length; i++) {
                        String indexStr = parts[i].split("/")[0];
                        int index = Integer.parseInt(indexStr) - 1;
                        polygon.add(index);
                    }

                    model.polygons.add(polygon);
                }
            }
        }

        return model;
    }
}
