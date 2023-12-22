package com.example.cg_task2_javafx;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class ProgramController {

    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));
        dda(canvas.getGraphicsContext2D(), 100, 500, 800, 600, Color.rgb(1, 254, 1), Color.rgb(127, 1, 254));
    }

    public static void dda(GraphicsContext graphicsContext, int x1, int y1, int x2, int y2, Color color1, Color color2) {

        float dx = x2 - x1;
        float dy = y2 - y1;

        float step = Math.max(Math.abs(dx), Math.abs(dy));

        dx = dx / step;
        dy = dy / step;

        float fx = x1;
        float fy = y1;

        int x = x1;
        int y = y1;

        int i = 0;
        PixelWriter pixelWriter = graphicsContext.getPixelWriter();

        while (i <= step) {

            //Интерполяция
            Color color = interpolation(fx, fy, x1, y1, x2, y2, color1, color2);

            /*
            Для наглядности на большой ширине
            for(int j = 0; j < 30; j++){
                pixelWriter.setColor(x, y + j, color);
            }*/

            pixelWriter.setColor(x, y, color);

            fx+=dx;
            fy+=dy;

            x = Math.round(fx);
            y = Math.round(fy);
            i = i + 1;
        }
    }

    private static Color interpolation(float fx, float fy, int x1, int y1, int x2, int y2, Color color1, Color color2){
        float sqrt1 = (float) Math.sqrt(Math.pow((fx - x1), 2) + Math.pow((fy - y1),2));
        float sqrt2 = (float) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1),2));
        double ratio = sqrt1 / sqrt2;

        double r = (color1.getRed() + ratio * (color2.getRed() - color1.getRed()));
        double g = (color1.getGreen()  + ratio * (color2.getGreen() - color1.getGreen()));
        double b = (color1.getBlue()  + ratio * (color2.getBlue() - color1.getBlue()));

        return Color.color(r, g, b);
    }

}