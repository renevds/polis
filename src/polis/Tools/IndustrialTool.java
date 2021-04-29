package polis.Tools;

import polis.GameController;
import polis.tiles.IndustrialTile;
import polis.tiles.MultiTile;
import polis.tiles.Tile;

public class IndustrialTool extends MultiTileBuilder{
    public IndustrialTool(GameController GC, String type) {
        super(GC, type);
    }

    @Override
    public MultiTile getTypeInstance(Tile tile) {
        return new IndustrialTile(tile.getX(), tile.getY(), GC);
    }
}
