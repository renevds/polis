package polis.tiles;

import javafx.scene.image.Image;
import polis.gameController;

public class Street extends ImageTile{
    boolean removable = true;

    public Street(int x, int y, gameController GC){
        super(x, y, GC);
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

    public void setImageString(Boolean starter) {
        int n = 0;
        if (GC.validCoord(y - 1)){
            Tile neighbourTile = GC.getTileAtCoord(x, y - 1);
            if(neighbourTile instanceof Street){
                n += 1;
            }
        }
        else if(!removable()){
            n += 1;
        }

        if (GC.validCoord(y + 1)){
            Tile neighbourTile = GC.getTileAtCoord(x, y + 1);
            if(neighbourTile instanceof Street){
                n += 4;
            }
        }

        if (GC.validCoord(x - 1)){
            Tile neighbourTile = GC.getTileAtCoord(x - 1, y);
            if(neighbourTile instanceof Street){
                n += 8;
            }
        }

        if (GC.validCoord(x + 1)){
            Tile neighbourTile = GC.getTileAtCoord(x + 1, y);
            if(neighbourTile instanceof Street){
                n += 2;
            }
        }

        if(starter){
            makeNeighboursRecalulate();
        }

        System.out.println(this + " image:" + n);

        imageLink = "polis/tiles/road-" + n + ".png";
        refreshImage();
    }

    public void makeNeighboursRecalulate(){
        if (GC.validCoord(y - 1)){
            Tile neighbourTile = GC.getTileAtCoord(x, y - 1);
            if(neighbourTile instanceof Street){
                ((Street) neighbourTile).setImageString(false);
            }
        }

        if (GC.validCoord(y + 1)){
            Tile neighbourTile = GC.getTileAtCoord(x, y + 1);
            if(neighbourTile instanceof Street){
                ((Street) neighbourTile).setImageString(false);
            }
        }

        if (GC.validCoord(x - 1)){
            Tile neighbourTile = GC.getTileAtCoord(x - 1, y);
            if(neighbourTile instanceof Street){
                ((Street) neighbourTile).setImageString(false);
            }
        }

        if (GC.validCoord(x + 1)){
            Tile neighbourTile = GC.getTileAtCoord(x + 1, y);
            if(neighbourTile instanceof Street){
                ((Street) neighbourTile).setImageString(false);
            }
        }
    }
}
