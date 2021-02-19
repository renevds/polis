package polis.tiles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import polis.Drawers.Square;
import polis.gameController;

public abstract class zoneTile extends ImageTile {
    static int MAX_LEVEL = 4;
    int level;


    public zoneTile(int x, int y, gameController GC) {
        super(x, y, GC);
        level = 1;
        updateImage();
    }

    public void increaseLevel() {
        level = level%MAX_LEVEL + 1;
        updateImage();
    }

    public abstract void updateImage();

}
