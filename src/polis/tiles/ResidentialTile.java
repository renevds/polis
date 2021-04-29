package polis.tiles;

import polis.actors.Actor;
import javafx.scene.image.Image;
import polis.GameController;

import java.util.Properties;

public class
ResidentialTile extends ZoneTile {
    private static double FACTOR_JOB_FOUND;
    private static double FACTOR_JOB_NOT_FOUND;
    private static double FACTOR_SHOP_FOUND;
    private static double FACTOR_SHOP_NOT_FOUND;
    private static double CAPACITY_INITIAL;
    private static double CAPACITY_MINIMAL;
    private static double LEVEL1TO2;
    private static double LEVEL2TO1;
    private static double LEVEL2TO3;
    private static double LEVEL3TO2;

    private static Image[] images = new Image[]{
            new Image("/polis/tiles/residence-0.png"),
            new Image("/polis/tiles/residence-1.png"),
            new Image("/polis/tiles/residence-2.png"),
            new Image("/polis/tiles/residence-3.png")
    };

    public ResidentialTile(int x, int y, GameController GC) {
        super(x, y, GC);
        width = 2;
        height = 2;
        capacity = CAPACITY_INITIAL;
        gameController.getStatistics().registerResidential(this);
    }

    public void floorCapacity(){
        capacity = Math.max(capacity, CAPACITY_MINIMAL);
    }

    public boolean hasResident(Actor actor){
        return residents.contains(actor);
    }

    public void jobFound(){
        capacity *= FACTOR_JOB_FOUND;
        updateImage();
    }

    public void jobNotFound(){
        capacity *= FACTOR_JOB_NOT_FOUND;
        floorCapacity();
        kickOut();
        updateImage();
    }

    public void shopFound(){
        capacity *= FACTOR_SHOP_FOUND;
        updateImage();
    }

    public void shopNotFound(){
        capacity *= FACTOR_SHOP_NOT_FOUND;
        floorCapacity();
        kickOut();
        updateImage();
    }

    public void updateImage() {
        if(residents.size() != 0 || level != 0){
            if(level == 0){
                level = 1;
                updateImage();
            }
            else if(level == 1){
                if(capacity > LEVEL1TO2){
                    level = 2;
                    updateImage();
                }
            }
            else if(level == 2){
                if(capacity < LEVEL2TO1){
                    level = 1;
                }
                else if(capacity > LEVEL2TO3){
                    level = 3;
                }
            }
            else  if(level == 3){
                if(capacity < LEVEL3TO2){
                    level = 2;
                    updateImage();
                }
            }
        }
        image = images[level];
        fireInvalidationEvent();
    }

    public static void setProperties(Properties engine, Properties levels){
        CAPACITY_MINIMAL = Double.parseDouble(engine.getProperty("residential.capacity.minimal"));
        CAPACITY_INITIAL = Double.parseDouble(engine.getProperty("residential.capacity.initial"));

        LEVEL1TO2 = Double.parseDouble(levels.getProperty("residential.level1to2"));
        LEVEL2TO1 = Double.parseDouble(levels.getProperty("residential.level2to1"));
        LEVEL2TO3 = Double.parseDouble(levels.getProperty("residential.level2to3"));
        LEVEL3TO2 = Double.parseDouble(levels.getProperty("residential.level3to2"));
        FACTOR_JOB_FOUND = Double.parseDouble(engine.getProperty("factor.job.found"));
        FACTOR_JOB_NOT_FOUND = Double.parseDouble(engine.getProperty("factor.job.not.found"));
        FACTOR_SHOP_FOUND = Double.parseDouble(engine.getProperty("factor.shop.found"));
        FACTOR_SHOP_NOT_FOUND = Double.parseDouble(engine.getProperty("factor.shop.not.found"));

    }

    @Override
    public void remove() {
        gameController.getStatistics().removeResidential(this);
        super.remove();
    }

}
