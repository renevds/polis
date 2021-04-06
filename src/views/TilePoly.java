package views;

import javafx.scene.shape.Polygon;
import polis.gameController;
import polis.polisController;
import polis.tiles.Tile;

public class TilePoly extends Polygon{
    static int CELL_SIZE = polisController.getCELLSIZE();
    static int size = polisController.getSize();

    public TilePoly(Tile tile){
        super(
                0, 0,
                CELL_SIZE * size, 0.5 * CELL_SIZE * size,
                0, CELL_SIZE * size,
                -CELL_SIZE * size, 0.5 * CELL_SIZE * size
        );
        tile.getGameGrid().addChildrenToGrid(this, tile.getX(), tile.getY());
    }
}
