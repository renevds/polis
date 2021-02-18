package polis.tiles;

import javafx.scene.image.ImageView;
import polis.gameController;
import polis.polisController;

abstract public class ImageTile extends Tile{
    String imageLink;
    ImageView img;

    public ImageTile(int x, int y, gameController GC) {
        super(x, y, GC);
    }

    public void draw(){
        img = new ImageView(imageLink);
        img.setTranslateX(getTileRenderX());
        img.setTranslateY(getTileRenderY());
        GC.getPC().gamePane.getChildren().add(img);
        img.setOnMouseEntered(mouseEvent  -> hover());
        img.setOnMouseClicked(mouseEvent  -> clicked());
    }

    public double getTileRenderX(){
        return -((double)CELLSIZE*gameController.getMAPSIZE())/2 + CELLSIZE * (size - y + x) - CELLSIZE;
    }

    public double getTileRenderY(){
        return (double) polisController.getCELLSIZE() * (y + x) / 2 - CELLSIZE/2;
    }
}
