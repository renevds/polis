package polis.views;

import polis.tiles.Tile;

public class TreePoly extends ValidPoly{
    public TreePoly(Tile tile) {
        super(tile, (tile.getTileType() == Tile.TileType.STANDARD));
    }
}
