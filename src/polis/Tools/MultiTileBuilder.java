package polis.Tools;

import polis.GameController;
import polis.tiles.*;
import polis.views.ValidPoly;

public abstract class MultiTileBuilder extends MultiPolyTool {

    int width;
    int height;

    public MultiTileBuilder(GameController gameController, int width, int height) {
        super(gameController);
        this.width = width;
        this.height = height;
    }


    @Override
    public void hover(Tile tile) {
        hidePolys();
        for(int dx = 0; dx< width; dx++){
            for(int dy = 0; dy< height; dy++){
                int x = tile.getX() + dx;
                int y = tile.getY() + dy;
                if(gameGrid.validCoord(x) && gameGrid.validCoord(y)){
                    Tile newTile = gameGrid.getTileAtCoord(x, y);
                    hoverTiles.add(createPolyOnTile(newTile));
                }
                else {
                    hoverTiles.add(new ValidPoly(gameGrid, x, y, false));
                }
            }
        }
        checkValid(tile);
    }

    private void checkValid(Tile tile) {
        valid = true;
        for(int dx = 0; dx< width; dx++){
            for(int dy = 0; dy< height; dy++){
                int x = tile.getX() + dx;
                int y = tile.getY() + dy;
                if(gameGrid.validCoord(x) && gameGrid.validCoord(y)){
                    Tile newTile = gameGrid.getTileAtCoord(x, y);
                    valid &= newTile.getTileType() == Tile.TileType.STANDARD;
                }
                else {
                    valid = false;
                }
            }
        }
    }

    @Override
    public void clicked(Tile tile) {
        if(valid){
            MultiTile newTile = getTypeInstance(tile);
            gameGrid.replaceMultiTile(newTile);
        }
    }

    public abstract MultiTile getTypeInstance(Tile tile);


}
