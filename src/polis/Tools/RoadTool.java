package polis.Tools;

import polis.tiles.StandardTile;
import polis.tiles.Street;
import polis.tiles.Tile;
import polis.GameController;

import java.util.ArrayList;
import java.util.List;

public class RoadTool extends MultiPolyTool {

    private Tile firstTile;
    private Tile lastTile;

    private List<Tile> editTiles = new ArrayList<>();



    public RoadTool(GameController gameController) {
        super(gameController);
    }

    @Override
    public void hover(Tile tile) {
        gameController.getPC().getGameGrid().getChildren().remove(cursorPoly);
        cursorPoly = createPolyOnTile(tile);
    }

    private void createPolys(){
        hidePolys();
        editTiles.clear();
        int firstTileX = firstTile.getX();
        int lastTileX = lastTile.getX();
        int Xlength = lastTileX - firstTileX;
        int Xsign = (0>(Xlength))?-1:1;

        int firstTileY = firstTile.getY();
        int lastTileY = lastTile.getY();
        int Ylength = lastTileY - firstTileY;
        int Ysign = (0>(Ylength))?-1:1;

        for(int x = 0; x <= Xsign*Xlength; x++){
            Tile temp = gameGrid.getTileAtCoord(firstTileX + Xsign*x, firstTileY);
            hoverTiles.add(createPolyOnTile(temp));
            editTiles.add(temp);
        }

        for(int y = 1; y <= Ysign*Ylength; y++){
            Tile temp = gameGrid.getTileAtCoord(lastTileX, firstTileY + Ysign*y);
            hoverTiles.add(createPolyOnTile(temp));
            editTiles.add(temp);
        }
    }

    public void clicked(Tile tile){
        drag(tile);
    }

    @Override
    public void drag(Tile tile) {
        gameController.getPC().getGameGrid().getChildren().remove(cursorPoly);
        if(firstTile == null){
            firstTile = tile;
            lastTile = tile;
        }
        else {
            lastTile = tile;
        }
        createPolys();
    }


    @Override
    public void release(Tile tile) {
        firstTile = null;
        lastTile = null;
        for(Tile editTile: editTiles){
            if(editTile.getTileType() == Tile.TileType.STANDARD) {
                Street newStreet = new Street(editTile.getX(), editTile.getY(), gameController);
                gameGrid.replaceTile(newStreet);
                newStreet.calculateOrientationNumber(true);
            }
        }
        hidePolys();
        editTiles.clear();
    }

}
