package polis.Tools;

import polis.GameController;
import polis.tiles.StandardTile;
import polis.tiles.Tile;
import polis.views.TreePoly;

public class WaterTool extends PolygonTool {

    public WaterTool(GameController GC) {
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

