package polis.tiles;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polis.gameController;
import polis.polisController;

public class StandardTile extends Tile{

    Polygon polygon;

    public StandardTile(int x, int y, gameController GC) {
        super(x, y, GC);
    }

    @Override
    public void draw(){
        polygon = drawSquare();
        polygon.setTranslateX(getTileRenderX());
        polygon.setTranslateY(getTileRenderY());
        gamePane.getChildren().add(polygon);
        polygon.setOnMouseEntered(mouseEvent  -> hover());
        polygon.setOnMousePressed(mouseEvent  -> clicked());
        //Text text = new Text("(" + x + ", " + y + ")");
        //text.setTranslateX(getTileRenderX());
        //text.setTranslateY(getTileRenderY() + polisController.getCELLSIZE()/2);
        //gamePane.getChildren().add(text);
    }

    Polygon drawSquare() {
        int CELL_SIZE = polisController.getCELLSIZE();
        int size = polisController.getSize();

        Polygon ret = new Polygon(
                0, 0,
                CELL_SIZE * size, 0.5 * CELL_SIZE * size,
                0, CELL_SIZE * size,
                -CELL_SIZE * size, 0.5 * CELL_SIZE * size
        );
        ret.setFill(Color.rgb(204, 249, 170));
        return ret;
    }

    public void remove(){
        gamePane.getChildren().remove(polygon);
    }

    @Override
    public Boolean removable() {
        return true;
    }
}