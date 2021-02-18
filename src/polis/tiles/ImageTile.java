package polis.tiles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polis.Drawers.Square;
import polis.gameController;
import polis.polisController;

abstract public class ImageTile extends Tile{
    String imageLink = "/polis/tiles/road-0.png";
    ImageView img;
    Polygon polygon;

    public ImageTile(int x, int y, gameController GC) {
        super(x, y, GC);
    }

    public void draw(){
        Polygon polygon = Square.draw();
        polygon.setFill(Color.rgb(204, 249, 170));
        img = new ImageView(imageLink);
        img.setTranslateX(getTileRenderX() - CELLSIZE);
        img.setTranslateY(getTileRenderY());
        polygon.setTranslateX(getTileRenderX());
        polygon.setTranslateY(getTileRenderY());
        GC.getPC().gamePane.getChildren().add(polygon);
        polygon.setMouseTransparent(true);
        GC.getPC().gamePane.getChildren().add(img);
        img.setOnMouseEntered(mouseEvent  -> hover());
        img.setOnMousePressed(mouseEvent  -> clicked());
        polygon.toBack();
        img.getStyleClass().add("green-background");


    }

    public void refreshImage(){
        img.setImage(new Image(imageLink));
    }

}
