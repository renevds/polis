package polis.tiles;

import javafx.scene.image.Image;
import polis.gameController;

public class
IndustrialTile extends ZoneTile {

    static int width = 2;
    static int height = 2;

    static Image[] images = new Image[]{
            new Image("/polis/tiles/industry-0.png"),
            new Image("/polis/tiles/industry-1.png"),
            new Image("/polis/tiles/industry-2.png"),
            new Image("/polis/tiles/industry-3.png")
    };

    public IndustrialTile(int x, int y, gameController GC) {
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
