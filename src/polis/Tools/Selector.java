package polis.Tools;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polis.Drawers.Square;
import polis.tiles.Tile;
import polis.gameController;

public class Selector extends Tool {

    Polygon polygon;


    public Selector(gameController GC){
        super(GC);
    }

    public void hover(Tile tile) {
        hidePolygon();
        polygon = Square.drawOnTile(tile, GC);
        polygon.setStyle("-fx-fill: transparent; -fx-stroke: rgba(255,255,255,0.75); -fx-stroke-width: 4;");
        polygon.setFill(Color.rgb(0,0,0,0));
        //System.out.print("(" + tile.getX() + ", " + tile.getY() + ") " + tile);
        //System.out.println(" hovered.");
    }

    public void hidePolygon(){
        if (polygon != null){
            gamePane.getChildren().remove(polygon);
        }
    }

    public void close(){
        hidePolygon();
    }

    @Override
    public void clicked(Tile tile){

    }

}
