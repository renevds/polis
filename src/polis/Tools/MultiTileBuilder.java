package polis.Tools;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polis.Drawers.Square;
import polis.gameController;
import polis.tiles.ResidentialTile;
import polis.tiles.StandardTile;
import polis.tiles.Tile;

import java.lang.reflect.Method;

public class MultiTileBuilder extends MultiPolyTool {
    int tileSize = 2;
    private String type;

    public MultiTileBuilder(gameController GC, String type) {
        super(GC);
        this.type = type;
    }

    public Polygon createPolyOutsideMap(int x, int y){
        Polygon polygon = Square.draw();
        polygon.setFill(Color.rgb(255, 0, 0, 0.5));
        polygon.setTranslateX(Tile.getRenderX(x, y));
        polygon.setTranslateY(Tile.getRenderY(x, y));
        GC.getPC().getGamePane().getChildren().add(polygon);
        return polygon;
    }


    @Override
    public void hover(Tile tile) {
        hidePolys();
        for(int dx = 0; dx< tileSize; dx++){
            for(int dy = 0; dy< tileSize; dy++){
                int x = tile.getX() + dx;
                int y = tile.getY() + dy;
                if(GC.validCoord(x) && GC.validCoord(y)){
                    Tile newTile = GC.getTileAtCoord(x, y);
                    hoverTiles.add(createPolyOnTile(newTile));
                }
                else {
                    hoverTiles.add(createPolyOutsideMap(x, y));
                }
            }
        }
        checkValid(tile);
    }


    @Override
    public void close() {

    }

    public void checkValid(Tile tile) {
        valid = true;
        for(int dx = 0; dx< tileSize; dx++){
            for(int dy = 0; dy< tileSize; dy++){
                int x = tile.getX() + dx;
                int y = tile.getY() + dy;
                if(GC.validCoord(x) && GC.validCoord(y)){
                    Tile newTile = GC.getTileAtCoord(x, y);
                    valid = newTile instanceof StandardTile;
                }
                else {
                    valid = false;
                }
            }
        }
    }

    @Override
    public void clicked(Tile tile) {
        Tile newTile = null;
        if(valid){
            if(type.equals("residential")){
                newTile = new ResidentialTile(tile.getX(), tile.getY(), GC);
            }
            GC.replaceMultiTile(newTile, tileSize);
        }
    }
}
