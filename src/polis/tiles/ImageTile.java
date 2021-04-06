package polis.tiles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import polis.gameController;

abstract public class
ImageTile extends Tile {
    protected String imageLink = "/polis/tiles/test.png";

    public ImageTile(int x, int y, gameController GC) {
        super(x, y, GC);
    }

    public void draw() {
        Image img = new Image(imageLink);
        mainNode = new ImageView(img);
        mainNode.setTranslateX(getTileRenderX() - img.getWidth() / 2);
        mainNode.setTranslateY(getTileRenderY());
        GC.getPC().getGameGrid().getChildren().add(mainNode);
        createEvents(mainNode);
    }

    protected void refreshImage() {
        if (mainNode != null) {
            ((ImageView) mainNode).setImage(new Image(imageLink));
        }
    }

    @Override
    public void toFront() {
        mainNode.toFront();
    }
}
