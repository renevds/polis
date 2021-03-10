package polis.tiles;

import polis.gameController;

public class Street extends ImageTile {
    private boolean removable = true;

    public Street(int x, int y, gameController GC) {
        super(x, y, GC);
        imageLink = "polis/tiles/road-0.png";
    }

    public void remove() {
        GC.getPC().gamePane.getChildren().remove(mainNode);
    }

    @Override
    public Boolean removable() {
        return removable;
    }

    public void makeUnRemovable() {
        removable = false;
        setImageString(true);
    }

    public void setImageString(Boolean starter) {
        int n = 0;
        if (removable) {
            if (GC.validCoord(y - 1)) {
                Tile neighbourTile = GC.getTileAtCoord(x, y - 1);
                if (neighbourTile instanceof polis.tiles.Street) {
                    n += 1;
                }
            }
        } else {
            n += 1;
        }

        if (GC.validCoord(y + 1)) {
            Tile neighbourTile = GC.getTileAtCoord(x, y + 1);
            if (neighbourTile instanceof polis.tiles.Street) {
                n += 4;
            }
        }

        if (GC.validCoord(x - 1)) {
            Tile neighbourTile = GC.getTileAtCoord(x - 1, y);
            if (neighbourTile instanceof polis.tiles.Street) {
                n += 8;
            }
        }

        if (GC.validCoord(x + 1)) {
            Tile neighbourTile = GC.getTileAtCoord(x + 1, y);
            if (neighbourTile instanceof polis.tiles.Street) {
                n += 2;
            }
        }

        if (starter) {
            makeNeighboursRecalculate();
        }

        System.out.println(this + " image:" + n);

        imageLink = "polis/tiles/road-" + n + ".png";
        refreshImage();
    }

    public void makeNeighboursRecalculate() {
        if (GC.validCoord(y - 1)) {
            Tile neighbourTile = GC.getTileAtCoord(x, y - 1);
            if (neighbourTile instanceof polis.tiles.Street) {
                ((polis.tiles.Street) neighbourTile).setImageString(false);
            }
        }

        if (GC.validCoord(y + 1)) {
            Tile neighbourTile = GC.getTileAtCoord(x, y + 1);
            if (neighbourTile instanceof polis.tiles.Street) {
                ((polis.tiles.Street) neighbourTile).setImageString(false);
            }
        }

        if (GC.validCoord(x - 1)) {
            Tile neighbourTile = GC.getTileAtCoord(x - 1, y);
            if (neighbourTile instanceof polis.tiles.Street) {
                ((polis.tiles.Street) neighbourTile).setImageString(false);
            }
        }

        if (GC.validCoord(x + 1)) {
            Tile neighbourTile = GC.getTileAtCoord(x + 1, y);
            if (neighbourTile instanceof polis.tiles.Street) {
                ((polis.tiles.Street) neighbourTile).setImageString(false);
            }
        }
    }
}
