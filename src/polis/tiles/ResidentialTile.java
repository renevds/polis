package polis.tiles;

import polis.gameController;

public class
ResidentialTile extends ZoneTile {

    static String[] images = new String[]{"/polis/tiles/residence-0.png", "/polis/tiles/residence-1.png", "/polis/tiles/residence-2.png", "/polis/tiles/residence-3.png"};

    public ResidentialTile(int x, int y, gameController GC) {
        super(x, y, GC);
    }

    public void updateImageLink() {
        imageLink = images[level - 1];
    }
}
