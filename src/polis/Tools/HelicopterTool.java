package polis.Tools;

import polis.GameController;
import polis.tiles.HelicopterTile;
import polis.tiles.MultiTile;
import polis.tiles.Tile;

public class HelicopterTool extends MultiTileBuilder{
    public HelicopterTool(GameController gameController) {
        super(gameController, 1 ,3);
    }

    @Override
    public MultiTile getTypeInstance(Tile tile) {
        return new HelicopterTile(tile.getX(), tile.getY(), gameController);
    }
}
