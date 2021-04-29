package polis.Tools;

import polis.tiles.MultiTile;
import polis.tiles.Tile;
import polis.GameController;
import polis.tiles.MultiTileFiller;
import polis.tiles.ZoneTile;
import views.SelectorPoly;

public class Selector extends PolygonTool {
    
    public Selector(GameController GC){
        super(GC);
    }

    public void hover(Tile tile) {
        hidePolygon();
        polygon = new SelectorPoly(tile);
    }

    @Override
    public void clicked(Tile tile){

    }

}
