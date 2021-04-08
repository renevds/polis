package views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polygon;
import polis.polisController;
import polis.tiles.HelicopterTile;

public class HelicopterTileView extends ImageView {
    private Polygon ret;
    static final Image image = new Image("polis/tiles/airfield.png");

    public HelicopterTileView(HelicopterTile helicopterTile){
        setImage(image);
        helicopterTile.getGameGrid().addChildrenToGrid(this, helicopterTile.getX(), helicopterTile.getY(), 192, getImage().getHeight() - 128);
        setMouseTransparent(true);
        int CELL_SIZE = polisController.getCELLSIZE();
        ret = new Polygon(
                0, 0,
                CELL_SIZE, 0.5 * CELL_SIZE,
                0, CELL_SIZE,
                -CELL_SIZE, 0.5 * CELL_SIZE
        );
        ret.setStyle("-fx-fill: transparent;");
        helicopterTile.getGameGrid().addChildrenToGrid(ret, helicopterTile.getX(), helicopterTile.getY());
        ret.toFront();
    }

    public Polygon getRet(){
        return ret;
    }
}
