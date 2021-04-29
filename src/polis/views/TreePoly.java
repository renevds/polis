package polis.views;

import polis.tiles.StandardTile;
import polis.tiles.Tile;

public class TreePoly extends ValidPoly{
    public TreePoly(Tile tile) {
        super(tile, (tile instanceof StandardTile));
    }
}
