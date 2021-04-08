package polis.tiles;

import javafx.scene.image.Image;
import polis.gameController;

public class
ResidentialTile extends ZoneTile {

    static Image[] images = new Image[]{
            new Image("/polis/tiles/residence-0.png"),
            new Image("/polis/tiles/residence-1.png"),
            new Image("/polis/tiles/residence-2.png"),
            new Image("/polis/tiles/residence-3.png")
    };

    public ResidentialTile(int x, int y, gameController GC) {
        super(x, y, GC);
        width = 2;
        height = 2;
    }

    public void updateImageLink() {
        image = images[level - 1];
    }
}
