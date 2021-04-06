package polis.Tools;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polis.Drawers.Square;
import polis.gameController;
import polis.tiles.*;

public class MultiTileBuilder extends MultiPolyTool {
    private int tileSize = 2;
    private String type;

    public MultiTileBuilder(gameController GC, String type) {
        super(GC);
        this.type = type;
    }

    public Polygon createPolyOutsideMap(int x, int y){
        Polygon polygon = Square.draw();
        polygon.setFill(Color.rgb(255, 0, 0, 0.5));
        polygon.setTranslateX(gameGrid.getRenderX(x, y));
        polygon.setTranslateY(gameGrid.getRenderY(x, y));
        GC.getPC().getGameGrid().getChildren().add(polygon);
        return polygon;
    }


    @Override
    public void hover(Tile tile) {
        hidePolys();
        for(int dx = 0; dx< tileSize; dx++){
            for(int dy = 0; dy< tileSize; dy++){
                int x = tile.getX() + dx;
                int y = tile.getY() + dy;
                if(gameGrid.validCoord(x) && gameGrid.validCoord(y)){
                    Tile newTile = gameGrid.getTileAtCoord(x, y);
                    hoverTiles.add(createPolyOnTile(newTile));
                }
                else {
                    hoverTiles.add(createPolyOutsideMap(x, y));
                }
            }
        }
        checkValid(tile);
    }

    public void checkValid(Tile tile) {
        valid = true;
        for(int dx = 0; dx< tileSize; dx++){
            for(int dy = 0; dy< tileSize; dy++){
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
    }

    @Override
    public void clicked(Tile tile) {
        ZoneTile newTile = null;
        if(valid){
            switch (type){
                case "residential":
                    newTile = new ResidentialTile(tile.getX(), tile.getY(), GC);
                    break;
                case "industrial":
                    newTile = new IndustrialTile(tile.getX(), tile.getY(), GC);
                    break;
                case "commercial":
                    System.out.println();
                    newTile = new CommercialTile(tile.getX(), tile.getY(), GC);
                    break;
            }
            gameGrid.replaceMultiTile(newTile, tileSize);
        }
    }
}
