package polis.tiles;

import polis.gameController;

public class Street extends ImageTile{
    boolean removable = true;

    public Street(int x, int y, gameController GC){
        super(x, y, GC);
        setImageString();
        //imageLink = "polis/tiles/test.png";
    }

    public void remove(){
        GC.getPC().gamePane.getChildren().remove(img);
    }

    @Override
    public Boolean removable() {
        return removable;
    }

    public void makeUnRemovable() {
        removable = false;
    }

    public void setImageString() {
        int ne = 0;
        int se = 0;
        int sw = 0;
        int nw = 0;
        if (GC.validCoord(x + 1) && GC.validCoord(y + 1) && GC.getTileAtCoord(x + 1, y + 1) instanceof Street){
            ne = 1;
            ((Street) GC.getTileAtCoord(x + 1, y + 1)).setImageString();
        }
        if (GC.validCoord(x + 1) && GC.validCoord(y - 1) && GC.getTileAtCoord(x + 1, y - 1) instanceof Street){
            se = 2;
            ((Street) GC.getTileAtCoord(x + 1, y - 1)).setImageString();
        }
        if (GC.validCoord(x - 1) && GC.validCoord(y - 1) && GC.getTileAtCoord(x - 1, y - 1) instanceof Street){
            sw = 4;
            ((Street) GC.getTileAtCoord(x + 1, y + 1)).setImageString();
        }
        if (GC.validCoord(x - 1) && GC.validCoord(y + 1) && GC.getTileAtCoord(x - 1, y + 1) instanceof Street){
            nw = 8;
            ((Street) GC.getTileAtCoord(x - 1, y + 1)).setImageString();
        }
        imageLink = "polis/tiles/road-" + (ne + se + sw + nw) + ".png";
    }
}