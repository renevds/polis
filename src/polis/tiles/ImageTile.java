package polis.tiles;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polis.Drawers.Square;
import polis.gameController;
import polis.polisController;

abstract public class ImageTile extends Tile{
    String imageLink = "/polis/tiles/test.png";

    public ImageTile(int x, int y, gameController GC) {
        super(x, y, GC);
    }

    public void draw(){
        Image img = new Image(imageLink);
        mainNode = new ImageView(img);
        mainNode.setTranslateX(getTileRenderX() - img.getWidth()/2);
        mainNode.setTranslateY(getTileRenderY());
        GC.getPC().gamePane.getChildren().add(mainNode);
        createEvents(mainNode);
    }

    public void refreshImage(){
        if(mainNode != null){
            ((ImageView)mainNode).setImage(new Image(imageLink));
        }
    }

}
