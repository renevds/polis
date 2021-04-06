package polis.Tools;

import polis.tiles.StandardTile;
import polis.tiles.Street;
import polis.tiles.Tile;
import polis.gameController;

import java.util.ArrayList;
import java.util.List;

public class RoadTool extends MultiPolyTool {

    private Tile firstTile;
    private Tile lastTile;

    private List<Tile> editTiles = new ArrayList<>();



    public RoadTool(gameController GC) {
        super(GC);
    }

    @Override
    public void hover(Tile tile) {
        GC.getPC().getGameGrid().getChildren().remove(cursorPoly);
        cursorPoly = createPolyOnTile(tile);
    }

    void createPolys(){
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
        GC.getPC().getGameGrid().getChildren().remove(cursorPoly);
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
            if(editTile instanceof StandardTile) {
                Street newStreet = new Street(editTile.getX(), editTile.getY(), GC);
                gameGrid.replaceTile(newStreet);
                newStreet.setImageString(true);
            }
        }
        hidePolys();
        editTiles.clear();
    }

}
