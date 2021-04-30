package polis.Tools;

import polis.GameController;
import polis.tiles.Tile;
import polis.views.TreePoly;

public class TreeTool extends PolygonTool{

    //deze tool laat de gebruiker de standaardtegels veranderen naar andere begroeing voor decoratie

    public TreeTool(GameController gameController) {
        super(gameController);
    }
    @Override
    public void hover(Tile tile) {
        hidePolygon();
        polygon = new TreePoly(tile);
    }

    @Override
    public void clicked(Tile tile) {
        if(tile.getTileType() == Tile.TileType.STANDARD){
            gameGrid.getBackgroundTileBehindTile(tile).changeDecoration();
        }
    }
}
