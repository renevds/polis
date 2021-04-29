package polis.tiles;

import javafx.scene.image.Image;
import polis.GameController;
import polis.actors.Actor;
import polis.actors.Customer;
import polis.actors.Trader;
import polis.ui.Statistics;

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

    public CommercialTile(int x, int y, GameController gameController) {
        super(x, y, gameController);
        width = 2;
        height = 2;
        traderList = new ArrayList<>();
        capacity = CAPACITY_INITIAL;
        gameController.getStatistics().registerCommercial(this);
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

    public void sellGood(){
        goods -= 1;
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

    public boolean canTakeCustomer(){
        if(residents.size() + 1 <= capacity){
            System.out.println("goods: " + goods);
            System.out.println("traders: " + traderList.size());
            System.out.println("shopers: " + residents.size());
            if(residents.size() + 1 <= goods && residents.size() + 1 <= traderList.size()){
                return true;
            }else {
                badTrade();
                return false;
            }
        }
        return false;
    }

    public boolean canTakeTrader(){
        if(traderList.size() + 1 <= capacity){
            return true;
        }
        return false;
    }

    public void addTrader(Trader trader){
        traderList.add(trader);
    }

    public void removeTrader(Trader trader){
        traderList.remove(trader);
    }


    public void addCustomer(Customer customer) {
        super.addResident(customer);
        if(residents.size() + 1 > capacity){
            goodTrade();
        }
    }

    public void removeCustomer(Customer customer){
        residents.remove(customer);
    }

    public void updateImage() {
        if(residents.size() != 0 || level != 0 || goods!=0){
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

    @Override
    public void remove() {
        gameController.getStatistics().removeCommercial(this);
        super.remove();
    }

    public int getAmountOfTraders(){
        return traderList.size();
    }

    public int getAmountOfGoods(){
        return goods;
    }



}
