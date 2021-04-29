package polis.Tools;

import polis.GameController;
import polis.tiles.*;
import polis.views.ValidPoly;

public abstract class MultiTileBuilder extends MultiPolyTool {
    private String type;
    int width;
    int height;

    public MultiTileBuilder(GameController GC, String type) {
        super(GC);
        this.type = type;

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
        MultiTile temp = getTypeInstance(tile);
        valid = true;
        for(int dx = 0; dx< temp.getWidth(); dx++){
            for(int dy = 0; dy< temp.getHeight(); dy++){
                int x = tile.getX() + dx;
                int y = tile.getY() + dy;
                if(gameGrid.validCoord(x) && gameGrid.validCoord(y)){
                    Tile newTile = gameGrid.getTileAtCoord(x, y);
                    valid &= newTile instanceof StandardTile;
                }
                else {
                    valid = false;
                }
            }
        }
        temp.remove();
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
