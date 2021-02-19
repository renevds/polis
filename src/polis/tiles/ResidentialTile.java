package polis.tiles;

import polis.gameController;

public class ResidentialTile extends zoneTile{

    static String[] images = new String[]{"/polis/tiles/residence-0.png", "/polis/tiles/residence-1.png", "/polis/tiles/residence-2.png", "/polis/tiles/residence-3.png"};

    public ResidentialTile(int x, int y, gameController GC) {
        super(x, y, GC);
    }

    @Override
    public void remove() {
        GC.getPC().getGamePane().getChildren().remove(mainNode);
    }

    @Override
    public Boolean removable() {
        return null;
    }

    public void updateImage(){
        imageLink = images[level - 1];
    }
}
