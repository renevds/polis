package polis.tiles;

import polis.gameController;

public class
IndustrialTile extends ZoneTile {

    static String[] images = new String[]{"/polis/tiles/industry-0.png", "/polis/tiles/industry-1.png", "/polis/tiles/industry-2.png", "/polis/tiles/industry-3.png"};

    public IndustrialTile(int x, int y, gameController GC) {
        super(x, y, GC);
    }

    public void updateImageLink() {
        imageLink = images[level - 1];
    }
}
