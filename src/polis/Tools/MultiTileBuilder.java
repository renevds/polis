package polis.Tools;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polis.Drawers.Square;
import polis.gameController;
import polis.tiles.*;

public class MultiTileBuilder extends MultiPolyTool {
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
        MultiTile temp = getTypeInstance(new StandardTile(0, 0, GC));
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
                    hoverTiles.add(createPolyOutsideMap(x, y));
                }
            }
        }
        checkValid(tile);
    }

    public void checkValid(Tile tile) {
        MultiTile temp = getTypeInstance(new StandardTile(0, 0, GC));
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
