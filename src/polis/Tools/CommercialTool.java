package polis.Tools;

import polis.GameController;
import polis.tiles.CommercialTile;
import polis.tiles.MultiTile;
import polis.tiles.Tile;

public class CommercialTool extends MultiTileBuilder{
    public CommercialTool(GameController gameController) {
        super(gameController, 2, 2);
    }

    @Override
    public MultiTile getTypeInstance(Tile tile) {
        return new CommercialTile(tile.getX(), tile.getY(), gameController);
    }
}
