package BigTask;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import javax.vecmath.Vector2d;

/**
 * Created by MUtemov on 16.03.2017.
 */
public class Circle extends Shape {
    Vector2d centre;
    Double radius;
    PixelWriter pixelWriter;

    public Circle(Vector2d centre, Double radius, PixelWriter pixelWriter) {
        this.centre = centre;
        this.radius = radius;
        this.pixelWriter = pixelWriter;
    }

    @Override
    public void draw() {
        Vector2d centre = this.centre;
        int minX = (int)(centre.x - radius);
        int minY = (int)(centre.y - radius);
        int maxX = (int)(centre.x + radius);
        int maxY = (int)(centre.y + radius);
        for (int i = minX; i < maxX; i++) {
            for (int j = minY; j < maxY; j++) {
                if (Math.pow((i - centre.x), 2) + Math.pow((j - centre.y), 2) <= Math.pow(radius, 2)) {
                    pixelWriter.setColor(i, j, Color.RED);
                }
            }
        }

    }
}
