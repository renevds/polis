package polis.tiles;

import javafx.scene.image.Image;
import polis.GameController;
import polis.actors.Actor;
import polis.actors.Trader;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class
CommercialTile extends ZoneTile {

    private static double CAPACITY_MINIMAL;
    private static double CAPACITY_INITIAL;
    private static double LEVEL1TO2;
    private static double LEVEL2TO1;
    private static double LEVEL2TO3;
    private static double LEVEL3TO2;
    private static double FACTOR_GOOD_TRADE;
    private static double FACTOR_BAD_TRADE;

    private int goods;
    private List<Trader> traderList;

    private static Image[] images = new Image[]{
            new Image("/polis/tiles/commerce-0.png"),
            new Image("/polis/tiles/commerce-1.png"),
            new Image("/polis/tiles/commerce-2.png"),
            new Image("/polis/tiles/commerce-3.png")
    };

    public CommercialTile(int x, int y, GameController GC) {
        super(x, y, GC);
        width = 2;
        height = 2;
        traderList = new ArrayList<>();
    }

    public void floorCapacity(){
        capacity = Math.max(capacity, CAPACITY_MINIMAL);
    }

    public boolean hasSpaceLeftForGood(){
        if(goods + 1 < capacity){
            return true;
        }
        return false;
    }

    public void addGood(){
        goods += 1;
    }

    public void goodTrade(){
        capacity *= FACTOR_GOOD_TRADE;
        updateImage();
    }

    public void badTrade(){
        capacity *= FACTOR_BAD_TRADE;
        floorCapacity();
        updateImage();
    }

    @Override
    public void addResident(Actor actor) {
        super.addResident(actor);
        if(residents.size() + 1 > capacity){
            goodTrade();
        }
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
        CAPACITY_MINIMAL = Double.parseDouble(engine.getProperty("commercial.capacity.minimal"));
        CAPACITY_INITIAL = Double.parseDouble(engine.getProperty("commercial.capacity.initial"));

        LEVEL1TO2 = Double.parseDouble(levels.getProperty("commercial.level1to2"));
        LEVEL2TO1 = Double.parseDouble(levels.getProperty("commercial.level2to1"));
        LEVEL2TO3 = Double.parseDouble(levels.getProperty("commercial.level2to3"));
        LEVEL3TO2 = Double.parseDouble(levels.getProperty("commercial.level3to2"));
        FACTOR_GOOD_TRADE = Double.parseDouble(engine.getProperty("factor.good.trade"));
        FACTOR_BAD_TRADE = Double.parseDouble(engine.getProperty("factor.bad.trade"));

    }
}
