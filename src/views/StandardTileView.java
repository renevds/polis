package views;

import javafx.scene.shape.Polygon;
import polis.polisController;
import polis.tiles.Tile;

public class StandardTileView extends TilePoly {

    public StandardTileView(Tile tile) {
        super(tile);
        setStyle("-fx-fill: transparent;");
    }
}
