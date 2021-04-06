package polis.tiles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import polis.gameController;
import views.ImageTileView;

abstract public class
ImageTile extends Tile {
    protected String imageLink = "/polis/tiles/test.png";

    public ImageTile(int x, int y, gameController GC) {
        super(x, y, GC);
        mainNode = new ImageTileView(this);
        createEvents(mainNode);
    }

    public void draw() {
    }

    protected void refreshImage() {
        if (mainNode != null) {
            ((ImageView) mainNode).setImage(new Image(imageLink));
        }
    }

    public String getImageLink(){
        return imageLink;
    }

    @Override
    public void toFront() {
        mainNode.toFront();
    }
}
