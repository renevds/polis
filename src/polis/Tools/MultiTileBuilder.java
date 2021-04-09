package polis.Tools;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polis.gameController;
import polis.tiles.*;
import views.ValidPoly;

import java.util.Arrays;

public class MultiTileBuilder extends MultiPolyTool {
    private String type;

    public MultiTileBuilder(gameController GC, String type) {
        super(GC);
        this.type = type;

    }


    @Override
    public void hover(Tile tile) {
        MultiTile temp = getTypeInstance(tile);
        hidePolys();
        for(int dx = 0; dx< temp.getWidth(); dx++){
            for(int dy = 0; dy< temp.getHeight(); dy++){
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
        temp.remove();
    }

    public void checkValid(Tile tile) {
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

    public MultiTile getTypeInstance(Tile tile){
        switch (type){
            case "residential":
                return new ResidentialTile(tile.getX(), tile.getY(), GC);
            case "industrial":
                return new IndustrialTile(tile.getX(), tile.getY(), GC);
            case "commercial":
                return new CommercialTile(tile.getX(), tile.getY(), GC);
            case "helicopter":
                return new HelicopterTile(tile.getX(), tile.getY(), GC);
        }
        return null;
    }

}
