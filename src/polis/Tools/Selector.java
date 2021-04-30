package polis.Tools;

import polis.tiles.Tile;
import polis.GameController;
import polis.tiles.ZoneTile;
import polis.views.SelectorPoly;

public class Selector extends PolygonTool {
    
    public Selector(GameController gameController){
        super(gameController);
    }

    @Override
    public void hover(Tile tile) {
        hidePolygon();
        polygon = new SelectorPoly(tile);
    }

    @Override
    public void clicked(Tile tile){
        tile = tile.getParentTile();
        if(tile.getTileType() == Tile.TileType.COMMERCIAL || tile.getTileType() == Tile.TileType.RESIDENTIAL  || tile.getTileType() == Tile.TileType.INDUSTRIAL) {
            gameController.getStatistics().setSelected((ZoneTile) tile);
        }
        else {
            gameController.getStatistics().setSelected(null);
        }
    }

}
