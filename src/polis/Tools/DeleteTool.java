package polis.Tools;

import polis.GameController;
import polis.tiles.StandardTile;
import polis.tiles.Street;
import polis.tiles.Tile;
import polis.tiles.MultiTileFiller;
import polis.views.DeletePoly;

public class DeleteTool extends PolygonTool {

    public DeleteTool(GameController GC) {
        super(GC);
    }

    @Override
    public void hover(Tile tile) {
        hidePolygon();
        polygon = new DeletePoly(tile);
    }

    @Override
    public void clicked(Tile tile) {
        tile = tile.getParentTile();
        if (tile.removable()) {
            gameGrid.replaceTile(new StandardTile(tile.getX(), tile.getY(), GC));

            if (tile.getTileType() == Tile.TileType.STREET) {
                ((Street) tile).calculateOrientationNumber(true);
            }
            polygon.toFront();
        }

    }

}
