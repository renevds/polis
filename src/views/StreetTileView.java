package views;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import polis.tiles.Street;

public class StreetTileView extends ImageView implements InvalidationListener {
    private Street street;

    static Image[] images = new Image[]{
            new Image("/polis/tiles/road-0.png"),
            new Image("/polis/tiles/road-1.png"),
            new Image("/polis/tiles/road-2.png"),
            new Image("/polis/tiles/road-3.png"),
            new Image("/polis/tiles/road-4.png"),
            new Image("/polis/tiles/road-5.png"),
            new Image("/polis/tiles/road-6.png"),
            new Image("/polis/tiles/road-7.png"),
            new Image("/polis/tiles/road-8.png"),
            new Image("/polis/tiles/road-9.png"),
            new Image("/polis/tiles/road-10.png"),
            new Image("/polis/tiles/road-11.png"),
            new Image("/polis/tiles/road-12.png"),
            new Image("/polis/tiles/road-13.png"),
            new Image("/polis/tiles/road-14.png"),
            new Image("/polis/tiles/road-15.png")
    };

    public StreetTileView(Street street) {
        this.street = street;
        setImage(images[0]);
        street.getGameGrid().addChildrenToGrid(this, street.getX(), street.getY(), getImage().getWidth()/2, 0);
    }

    @Override
    public void invalidated(Observable observable) {
        setImage(images[street.getOrientationNumber()]);
    }
}
