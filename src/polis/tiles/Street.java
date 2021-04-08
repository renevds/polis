package polis.tiles;

import polis.gameController;
import views.StreetTileView;

public class Street extends Tile {
    private boolean removable = true;
    StreetTileView streetTileView;

    public Street(int x, int y, gameController GC) {
        super(x, y, GC);
        streetTileView = new StreetTileView(this);
        createEvents(streetTileView);
    }

    @Override
    public void draw() {

    }

    public void remove() {
        gameGrid.getChildren().remove(streetTileView);
    }

    @Override
    public Boolean removable() {
        return removable;
    }

    @Override
    public void toFront() {
        streetTileView.toFront();
    }

    public void makeUnRemovable() {
        removable = false;
    }

    public void calculateOrientationNumber(Boolean starter) {
        System.out.println("test");
        int n = 0;
        if (gameGrid.validCoord(y - 1)) {
            Tile neighbourTile = gameGrid.getTileAtCoord(x, y - 1);
            System.out.println(neighbourTile);
            if (neighbourTile instanceof polis.tiles.Street) {
                System.out.println("top");
                n += 1;
                if (starter){
                    ((polis.tiles.Street) neighbourTile).calculateOrientationNumber(false);
                }
            }
        }
        if(!removable){
            n = 1;
        }

        if (gameGrid.validCoord(y + 1)) {
            Tile neighbourTile = gameGrid.getTileAtCoord(x, y + 1);
            System.out.println(neighbourTile);
            if (neighbourTile instanceof polis.tiles.Street) {
                System.out.println("bottom");
                n += 4;
                if (starter){
                    ((polis.tiles.Street) neighbourTile).calculateOrientationNumber(false);
                }
            }
        }

        if (gameGrid.validCoord(x - 1)) {
            Tile neighbourTile = gameGrid.getTileAtCoord(x - 1, y);
            System.out.println(neighbourTile);
            if (neighbourTile instanceof polis.tiles.Street) {
                System.out.println("right");
                n += 8;
                if (starter){
                    ((polis.tiles.Street) neighbourTile).calculateOrientationNumber(false);
                }
            }
        }

        if (gameGrid.validCoord(x + 1)) {
            Tile neighbourTile = gameGrid.getTileAtCoord(x + 1, y);
            System.out.println(neighbourTile);
            if (neighbourTile instanceof polis.tiles.Street) {
                System.out.println("left");
                n += 2;
                if (starter){
                    ((polis.tiles.Street) neighbourTile).calculateOrientationNumber(false);
                }
            }
        }

        streetTileView.setOrientationNumber(n);
    }
}
