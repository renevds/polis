package polis.Tools;

import polis.tiles.MultiTileFiller;
import polis.tiles.Tile;
import polis.GameController;
import polis.tiles.ZoneTile;
import polis.views.SelectorPoly;

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
        if(tile instanceof MultiTileFiller){
            tile = ((MultiTileFiller) tile).getParentZone();
        }
        if(tile instanceof ZoneTile) {
            GC.getStatistics().setSelected((ZoneTile) tile);
        }
        else {
            GC.getStatistics().setSelected(null);
        }
    }

}
