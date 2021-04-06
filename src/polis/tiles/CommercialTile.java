package polis.tiles;

import javafx.beans.InvalidationListener;
import polis.gameController;

public class
CommercialTile extends ZoneTile {

    static String[] images = new String[]{"/polis/tiles/commerce-0.png", "/polis/tiles/commerce-1.png", "/polis/tiles/commerce-2.png", "/polis/tiles/commerce-3.png"};

    public CommercialTile(int x, int y, gameController GC) {
        super(x, y, GC);
    }

    public void updateImageLink() {
        imageLink = images[level - 1];
    }
}
