package polis.Tools;

import polis.GameController;
import polis.tiles.MultiTile;
import polis.tiles.ResidentialTile;
import polis.tiles.Tile;

public class ResidentialTool extends MultiTileBuilder{
    public ResidentialTool(GameController gameController) {
        super(gameController, 2, 2);
    }

    @Override
    public MultiTile getTypeInstance(Tile tile) {
        return new ResidentialTile(tile.getX(), tile.getY(), gameController);
    }
}
