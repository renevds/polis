package views;

import polis.tiles.Tile;

public class StandardTileView extends TilePoly {

    public StandardTileView(Tile tile) {
        super(tile);
        setStyle("-fx-fill: transparent;");
    }
}
