package polis.tiles;

import polis.GameController;
import polis.actors.Immigrant;
import polis.views.HelicopterTileView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelicopterTile extends MultiTile{

    private HelicopterTileView helicopterTileView;

    private int counter;

    public HelicopterTile(int x, int y, GameController gameController) {
        super(x, y, gameController);
        helicopterTileView = new HelicopterTileView(this);
        eventNode = helicopterTileView.getRet();
        createEvents(eventNode);
        width = 1;
        height = 3;
        counter = -1;
    }

    @Override
    public void remove() {
        super.remove();
        gameGrid.removeChildren(helicopterTileView);
    }

    @Override
    public Boolean removable() {
        return true;
    }

    @Override
    public void toFront() {
        helicopterTileView.toFront();
    }

    @Override
    public void step() {
        if(counter == 0) {
            List<Street> streets = getNeigbouringFreeStreets();
            if(streets.size() != 0) {
                new Immigrant(gameController, streets.get(0));
            }
            counter -= 1;
        }
        else if(counter < 0){
            Random random = new Random();
            counter = 100 + random.nextInt(100);
        }
        else {
            counter -= 1;
        }
    }

    @Override
    public TileType getTileType() {
        return TileType.HELICOPTER;
    }

    @Override
    public void setViewOrder() {
        helicopterTileView.setViewOrder (- x - y - 3.0);
    }
}
