package polis.Tools;

import polis.gameController;
import polis.tiles.MultiTileFiller;
import polis.tiles.StandardTile;
import polis.tiles.Street;
import polis.tiles.Tile;
import views.DeletePoly;
import views.TreePoly;

public class WaterTool extends PolygonTool {

    public WaterTool(gameController GC) {
        super(GC);
    }

    @Override
    public void hover(Tile tile) {
        hidePolygon();
        polygon = new TreePoly(tile);
    }

    @Override
    public void clicked(Tile tile) {
        if (tile instanceof StandardTile) {
            gameGrid.getBackgroundTileBehindTile(tile).setWater();
        }

    }

}

