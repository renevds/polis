package views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import polis.tiles.ImageTile;

public class ImageTileView extends ImageView{
    ImageTile imageTile;

    public ImageTileView(ImageTile imageTile) {
        super();
        this.imageTile = imageTile;
        Image img = new Image(imageTile.getImageLink());
        super.setImage(img);
        imageTile.getGameGrid().addChildrenToGrid(this, imageTile.getX(), imageTile.getY(), img.getWidth()/2, 0);
    }
}
