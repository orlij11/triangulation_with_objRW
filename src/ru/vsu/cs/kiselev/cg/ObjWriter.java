package ru.vsu.cs.kiselev.cg;

import java.io.*;
import java.util.Locale;

public class ObjWriter {

    public static void write(Model model, File file) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {

            for (Vector3f v : model.vertices) {
                // Locale.US гарантирует точку как разделитель
                pw.printf(Locale.US, "v %f %f %f%n", v.x, v.y, v.z);
            }

            for (var poly : model.polygons) {
                pw.print("f");
                for (int index : poly) {
                    pw.print(" " + (index + 1));
                }
                pw.println();
            }
        }
    }
}
