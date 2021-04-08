package polis.Tools;

import polis.gameController;
import polis.tiles.StandardTile;
import polis.tiles.Street;
import polis.tiles.Tile;
import polis.tiles.MultiTileFiller;
import views.DeletePoly;

public class DeleteTool extends PolygonTool {

    public DeleteTool(gameController GC) {
        super(GC);
    }

    @Override
    public void hover(Tile tile) {
        hidePolygon();
        polygon = new DeletePoly(tile);
    }

    @Override
    public void clicked(Tile tile) {
        System.out.println("a " + tile);
        System.out.println("b " + polygon);
        System.out.println("c " + GC);
        if (tile.removable()) {
            if (tile instanceof MultiTileFiller) {
                clicked(((MultiTileFiller)tile).getParentZone());
            } else {
                gameGrid.replaceTile(new StandardTile(tile.getX(), tile.getY(), GC));

                if (tile instanceof Street) {
                    ((Street) tile).calculateOrientationNumber(true);
                }
                polygon.toFront();
            }
        }

    }

}
