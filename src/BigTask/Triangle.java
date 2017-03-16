package BigTask;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import javax.vecmath.Vector2d;

/**
 * v3.reated by MUtemov on 16.03.2017.
 */
class Triangle extends Shape {
    private Vector2d s1, s2, s3;

    private Vector2d v1 = new Vector2d();
    private Vector2d v2 = new Vector2d();
    private Vector2d v3 = new Vector2d();
    private PixelWriter pixelWriter;

    Triangle(Vector2d s1, Vector2d s2, Vector2d s3, PixelWriter pixelWriter) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.pixelWriter = pixelWriter;
    }

    @Override
    public void draw() {
        transform2d(this.s1, v1);
        transform2d(this.s2, v2);
        transform2d(this.s3, v3);

        int minX = (int) Math.min(Math.min(v1.x, v2.x), v3.x);
        int minY = (int) Math.min(Math.min(v1.y, v2.y), v3.y);
        int maxX = (int) Math.max(Math.max(v1.x, v2.x), v3.x);
        int maxY = (int) Math.max(Math.max(v1.y, v2.y), v3.y);

        final double width = 0.01;
        double det = 1.0 / ((v2.y - v3.y) * (v1.x - v3.x) + (v3.x - v2.x) * (v1.y - v3.y));
        for (int i = minX; i < maxX; i++) {
            for (int j = minY; j < maxY; j++) {
                double l1 = ((v2.y - v3.y) * (i - v3.x) + (v3.x - v2.x) * (j - v3.y)) * det;
                double l2 = ((v3.y - v1.y) * (i - v3.x) + (v1.x - v3.x) * (j - v3.y)) * det;
                double l3 = 1 - l1 - l2;

                if (l1 < 0 || l2 < 0 || l3 < 0) continue;

                if ((l1 < width) || (l2 < width) || (l3 < width)) {
                    pixelWriter.setColor(i, j, Color.BLACK);
                } else {
                    pixelWriter.setColor(i, j, Color.GREEN);
                }
            }
        }
    }
}
