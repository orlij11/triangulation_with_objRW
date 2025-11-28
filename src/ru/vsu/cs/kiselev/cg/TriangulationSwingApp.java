package ru.vsu.cs.kiselev.cg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class TriangulationSwingApp extends JFrame {

    private final DrawPanel drawPanel;

    public TriangulationSwingApp() {
        setTitle("Триангуляция");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Центрирование окна

        setLayout(new BorderLayout());

        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton btnTriangulate = new JButton("Триангулировать");
        JButton btnClear = new JButton("Очистить");

        btnTriangulate.addActionListener(e -> drawPanel.triangulate());
        btnClear.addActionListener(e -> drawPanel.clear());

        buttonPanel.add(btnTriangulate);
        buttonPanel.add(btnClear);
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TriangulationSwingApp().setVisible(true);
        });
    }

    private static class DrawPanel extends JPanel {
        private final List<Vector3f> inputPoints = new ArrayList<>();

        private TriangulatedModel resultModel = null;

        public DrawPanel() {
            setBackground(Color.WHITE);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (resultModel != null) {
                        clear();
                    }

                    inputPoints.add(new Vector3f(e.getX(), e.getY(), 0));
                    repaint();
                }
            });
        }

        public void triangulate() {
            if (inputPoints.size() < 3) {
                JOptionPane.showMessageDialog(this, "Нужно минимум 3 точки!");
                return;
            }

            Model source = new Model();
            source.vertices.addAll(inputPoints);
            ArrayList<Integer> polygon = new ArrayList<>();
            for (int i = 0; i < inputPoints.size(); i++) {
                polygon.add(i);
            }
            source.polygons.add(polygon);

            try {
                resultModel = new TriangulatedModel(source);
                repaint();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Ошибка при триангуляции: " + ex.getMessage());
            }
        }

        public void clear() {
            inputPoints.clear();
            resultModel = null;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (!inputPoints.isEmpty()) {
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2));

                for (int i = 0; i < inputPoints.size(); i++) {
                    Vector3f p1 = inputPoints.get(i);

                    g2.fillOval((int)p1.x - 3, (int)p1.y - 3, 6, 6);

                    if (i < inputPoints.size() - 1) {
                        Vector3f p2 = inputPoints.get(i + 1);
                        g2.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
                    } else if (resultModel != null) {
                        Vector3f start = inputPoints.get(0);
                        g2.drawLine((int)p1.x, (int)p1.y, (int)start.x, (int)start.y);
                    }
                }
            }


            if (resultModel != null) {
                g2.setColor(Color.BLUE);
                g2.setStroke(new BasicStroke(1));

                for (ArrayList<Integer> poly : resultModel.polygons) {
                    if (poly.size() < 3) continue;


                    Vector3f v1 = resultModel.vertices.get(poly.get(0));
                    Vector3f v2 = resultModel.vertices.get(poly.get(1));
                    Vector3f v3 = resultModel.vertices.get(poly.get(2));


                    g2.drawLine((int)v1.x, (int)v1.y, (int)v2.x, (int)v2.y);
                    g2.drawLine((int)v2.x, (int)v2.y, (int)v3.x, (int)v3.y);
                    g2.drawLine((int)v3.x, (int)v3.y, (int)v1.x, (int)v1.y);
                    g2.setColor(new Color(0, 0, 255, 20)); // Прозрачный синий
                    Polygon awtPoly = new Polygon();
                    awtPoly.addPoint((int)v1.x, (int)v1.y);
                    awtPoly.addPoint((int)v2.x, (int)v2.y);
                    awtPoly.addPoint((int)v3.x, (int)v3.y);
                    g2.fillPolygon(awtPoly);

                    g2.setColor(Color.BLUE);
                }
            }
        }
    }
}
