package BigTask;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.stage.Stage;

import javax.vecmath.Matrix3d;
import javax.vecmath.Vector2d;

/**
 * Created by MUtemov on 16.03.2017.
 */
public class BTMain extends Application {

    private Matrix3d globalTransform = new Matrix3d();
    private double angle;
    private double angle2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        final int width = 700;
        final int height = 700;
        primaryStage.setTitle("Big madness");
        primaryStage.setScene(new Scene(root, width, height));

        Canvas canvas = new Canvas(width, height);
        root.getChildren().add(canvas);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        PixelWriter pixelWriter = graphicsContext.getPixelWriter();

        final double[] mat = {1, 0, width / 2, 0, -1, height / 2, 0, 0, 1};
        globalTransform.set(mat);

        Triangle tr = new Triangle(new Vector2d(0, 0), new Vector2d(50, 0), new Vector2d(0, 50), pixelWriter);

        Matrix3d rotate = new Matrix3d();
        Matrix3d rotate2 = new Matrix3d();

        Matrix3d translate = new Matrix3d();
        translate.setIdentity();
        translate.setColumn(2, 0, -300, 1);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                graphicsContext.clearRect(0, 0, width, height);

                rotate.rotZ(angle += 0.01);
                rotate2.rotZ(angle2 -= 0.5);

                tr.matrix.set(globalTransform);
                tr.matrix.mul(rotate);
                tr.matrix.mul(translate);
                tr.matrix.mul(rotate2);
                tr.draw();
            }
        };

        timer.start();

        /*
        Circle cir = new Circle(new Vector2d(150, 100), 100.0, pixelWriter);
        cir.draw();
*/

        primaryStage.show();
    }
}
