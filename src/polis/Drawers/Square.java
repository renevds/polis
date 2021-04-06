package polis.Drawers;

import javafx.scene.shape.Polygon;
import polis.tiles.Tile;
import polis.gameController;
import polis.polisController;

public class Square {
    public static Polygon draw() {
        int CELL_SIZE = polisController.getCELLSIZE();
        int size = polisController.getSize();

        Polygon ret = new Polygon(
                0, 0,
                CELL_SIZE * size, 0.5 * CELL_SIZE * size,
                0, CELL_SIZE * size,
                -CELL_SIZE * size, 0.5 * CELL_SIZE * size
        );
        return ret;
    }

    public static Polygon drawOnTile(Tile tile, gameController GC){
        Polygon polygon = draw();
        polygon.setTranslateX(GC.getPC().getGameGrid().getRenderX(tile.getX(), tile.getY()));
        polygon.setTranslateY(GC.getPC().getGameGrid().getRenderY(tile.getX(), tile.getY()));
        GC.getPC().getGameGrid().getChildren().add(polygon);
        return polygon;
    }
}
