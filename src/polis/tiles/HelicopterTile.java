package polis.tiles;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import polis.gameController;
import polis.polisController;
import views.HelicopterTileView;

import java.util.Map;

public class HelicopterTile extends MultiTile{

    HelicopterTileView helicopterTileView;

    public HelicopterTile(int x, int y, gameController GC) {
        super(x, y, GC);
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 3;
    }

    @Override
    public void draw() {
        helicopterTileView = new HelicopterTileView(this);
        mainNode = helicopterTileView.getRet();
        createEvents(mainNode);
    }

    @Override
    public void remove() {
        super.remove();
        gameGrid.removeChildren(helicopterTileView);
    }

    @Override
    public Boolean removable() {
        return true;
    }

    @Override
    public void toFront() {
        helicopterTileView.toFront();
    }
}
