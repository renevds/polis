package polis.tiles;

import javafx.scene.image.Image;
import polis.gameController;

public class
IndustrialTile extends ZoneTile {

    static Image[] images = new Image[]{
            new Image("/polis/tiles/industry-0.png"),
            new Image("/polis/tiles/industry-1.png"),
            new Image("/polis/tiles/industry-2.png"),
            new Image("/polis/tiles/industry-3.png")
    };

    public IndustrialTile(int x, int y, gameController GC) {
        super(x, y, GC);
        width = 2;
        height = 2;
    }

    public void updateImageLink() {
        image = images[level - 1];
    }
}
