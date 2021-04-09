package views;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polygon;
import polis.PolisController;
import polis.tiles.ZoneTile;

public class ZoneTileView extends ImageView implements InvalidationListener {
    private ZoneTile zoneTile;
    private Polygon ret;

    public ZoneTileView(ZoneTile zoneTile) {
        super();
        this.zoneTile = zoneTile;
        Image img = zoneTile.getImage();
        super.setImage(img);
        zoneTile.getGameGrid().addChildrenToGrid(this, zoneTile.getX(), zoneTile.getY(), img.getWidth()/2, img.getHeight() - 128);
        setMouseTransparent(true);
        int CELL_SIZE = PolisController.getCELLSIZE();
        ret = new Polygon(
                0, 0,
                CELL_SIZE, 0.5 * CELL_SIZE,
                0, CELL_SIZE ,
                -CELL_SIZE, 0.5 * CELL_SIZE
        );
        ret.setStyle("-fx-fill: transparent;");
        zoneTile.getGameGrid().addChildrenToGrid(ret, zoneTile.getX(), zoneTile.getY());
        ret.toFront();


    }

    @Override
    public void invalidated(Observable observable) {
        Image img = zoneTile.getImage();
        setTranslateY(getTranslateY() + getImage().getHeight() - img.getHeight());
        setImage(img);
    }

    public Polygon getRet() {
        return ret;
    }
}
