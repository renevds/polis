package polis.tiles;

import javafx.scene.image.Image;
import polis.gameController;

public class
CommercialTile extends ZoneTile {

    static int width = 2;
    static int height = 2;

    static Image[] images = new Image[]{
            new Image("/polis/tiles/commerce-0.png"),
            new Image("/polis/tiles/commerce-1.png"),
            new Image("/polis/tiles/commerce-2.png"),
            new Image("/polis/tiles/commerce-3.png")
    };

    public CommercialTile(int x, int y, gameController GC) {
        super(x, y, GC);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void updateImageLink() {
        image = images[level - 1];
    }
}
