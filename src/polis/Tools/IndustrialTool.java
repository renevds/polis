package polis.Tools;

import polis.GameController;
import polis.tiles.IndustrialTile;
import polis.tiles.MultiTile;
import polis.tiles.Tile;

public class IndustrialTool extends MultiTileBuilder{
    public IndustrialTool(GameController gameController) {
        super(gameController, 2, 2);
    }

    @Override
    public MultiTile getTypeInstance(Tile tile) {
        return new IndustrialTile(tile.getX(), tile.getY(), gameController);
    }
}
