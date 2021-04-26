package polis.Tools;

import polis.GameController;
import polis.tiles.StandardTile;
import polis.tiles.Tile;
import views.TreePoly;

public class TreeTool extends PolygonTool{
    public TreeTool(GameController GC) {
        super(GC);
    }
    @Override
    public void hover(Tile tile) {
        hidePolygon();
        polygon = new TreePoly(tile);
    }

    @Override
    public void clicked(Tile tile) {
        if(tile instanceof StandardTile){
            gameGrid.getBackgroundTileBehindTile(tile).changeDecoration();
        }
    }
}
