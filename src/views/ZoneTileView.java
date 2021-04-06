package views;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polygon;
import polis.polisController;
import polis.tiles.ImageTile;
import polis.tiles.ZoneTile;

public class ZoneTileView extends ImageView implements InvalidationListener {
    ZoneTile zoneTile;
    Polygon ret;

    public ZoneTileView(ZoneTile zoneTile) {
        super();
        this.zoneTile = zoneTile;
        Image img = new Image(zoneTile.getImageLink());
        super.setImage(img);
        zoneTile.getGameGrid().addChildrenToGrid(this, zoneTile.getX(), zoneTile.getY(), img.getWidth()/2, img.getHeight() - 128);
        setMouseTransparent(true);
        int CELL_SIZE = polisController.getCELLSIZE();
        int size = polisController.getSize();
        ret = new Polygon(
                0, 0,
                CELL_SIZE * size, 0.5 * CELL_SIZE * size,
                0, CELL_SIZE * size,
                -CELL_SIZE * size, 0.5 * CELL_SIZE * size
        );
        ret.setStyle("-fx-fill: transparent;");
        zoneTile.getGameGrid().addChildrenToGrid(ret, zoneTile.getX(), zoneTile.getY());


    }

    @Override
    public void invalidated(Observable observable) {
        Image img = new Image(zoneTile.getImageLink());
        setTranslateY(getTranslateY() + getImage().getHeight() - img.getHeight());
        setImage(img);
        //gameGrid.fixLayers();
    }

    public Polygon getRet() {
        return ret;
    }
}
