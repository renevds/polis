package polis.Tools;

import polis.tiles.MultiTile;
import polis.tiles.Tile;
import polis.gameController;
import polis.tiles.MultiTileFiller;
import polis.tiles.ZoneTile;
import views.SelectorPoly;

public class Selector extends PolygonTool {
    
    public Selector(gameController GC){
        super(GC);
    }

    public void hover(Tile tile) {
        hidePolygon();
        polygon = new SelectorPoly(tile);
        //System.out.print("(" + tile.getX() + ", " + tile.getY() + ") " + tile);
        //System.out.println(" hovered.");
    }

    @Override
    public void clicked(Tile tile){
        if(tile instanceof ZoneTile){
            ((ZoneTile)tile).increaseLevel();
        }
        else if(tile instanceof MultiTileFiller){
            MultiTile parent = ((MultiTileFiller)tile).getParentZone();
            if(parent instanceof ZoneTile){
                ((ZoneTile) parent).increaseLevel();
            }
        }
    }

}
