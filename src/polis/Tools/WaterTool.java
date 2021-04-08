package polis.Tools;

import polis.gameController;
import polis.tiles.MultiTileFiller;
import polis.tiles.StandardTile;
import polis.tiles.Street;
import polis.tiles.Tile;
import views.DeletePoly;

public class WaterTool extends PolygonTool {

    public WaterTool(gameController GC) {
        super(GC);
    }

    @Override
    public void hover(Tile tile) {
        hidePolygon();
        polygon = new DeletePoly(tile);
    }

    @Override
    public void clicked(Tile tile) {
        if (tile.removable()) {
            if (tile instanceof MultiTileFiller) {
                clicked(((MultiTileFiller) tile).getParentZone());
            } else {
                StandardTile standardTile = new StandardTile(tile.getX(), tile.getY(), GC);
                gameGrid.replaceTile(standardTile);
                gameGrid.getBackgroundTileBehindTile(standardTile).setWater();
                if (tile instanceof Street) {
                    ((Street) tile).calculateOrientationNumber(true);
                }
                polygon.toFront();
            }
        }

    }

}

