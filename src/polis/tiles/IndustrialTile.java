package polis.tiles;

import javafx.scene.image.Image;
import polis.GameController;

import java.util.Properties;

public class
IndustrialTile extends ZoneTile {
    private static double CAPACITY_MINIMAL;
    private static double CAPACITY_INITIAL;
    private static double LEVEL1TO2;
    private static double LEVEL2TO1;
    private static double LEVEL2TO3;
    private static double LEVEL3TO2;
    private static double FACTOR_GOODS_NOT_DELIVERED;
    private static double FACTOR_GOODS_DELIVERED;

    private static Image[] images = new Image[]{
            new Image("/polis/tiles/industry-0.png"),
            new Image("/polis/tiles/industry-1.png"),
            new Image("/polis/tiles/industry-2.png"),
            new Image("/polis/tiles/industry-3.png")
    };

    public IndustrialTile(int x, int y, GameController GC) {
        super(x, y, GC);
        width = 2;
        height = 2;
        capacity = CAPACITY_INITIAL;
    }

    public void floorCapacity(){
        capacity = Math.max(capacity, CAPACITY_MINIMAL);
    }

    public void goodsNotDelivered(){
        capacity*= FACTOR_GOODS_NOT_DELIVERED;
        floorCapacity();
        kickOut();
    }

    public void goodsDelivered(){
        capacity*= FACTOR_GOODS_DELIVERED;
        kickOut();
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
        CAPACITY_MINIMAL = Double.parseDouble(engine.getProperty("industrial.capacity.minimal"));
        CAPACITY_INITIAL = Double.parseDouble(engine.getProperty("industrial.capacity.initial"));

        LEVEL1TO2 = Double.parseDouble(levels.getProperty("industrial.level1to2"));
        LEVEL2TO1 = Double.parseDouble(levels.getProperty("industrial.level2to1"));
        LEVEL2TO3 = Double.parseDouble(levels.getProperty("industrial.level2to3"));
        LEVEL3TO2 = Double.parseDouble(levels.getProperty("industrial.level3to2"));
        FACTOR_GOODS_DELIVERED = Double.parseDouble(engine.getProperty("factor.goods.delivered"));
        FACTOR_GOODS_NOT_DELIVERED = Double.parseDouble(engine.getProperty("factor.goods.not.delivered"));

    }
}
