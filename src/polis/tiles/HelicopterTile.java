package polis.tiles;

import polis.GameController;
import polis.actors.Immigrant;
import polis.views.HelicopterTileView;

import java.util.List;
import java.util.Random;

public class HelicopterTile extends MultiTile{

    private final HelicopterTileView helicopterTileView;

    private int counter;

    //extra toevoeging, deze Tegel plaatsts meer Immigranten

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
    public void step() {
        if(counter == 0) {
            List<Street> streets = getNeigbouringStreets();
            if(streets.size() != 0) {
                new Immigrant(gameController, streets.get(0));
            }
            counter -= 1;
        }
        else if(counter < 0){
            Random random = new Random();
            counter = 50 + random.nextInt(50);
        }
        else {
            counter -= 1;
        }
    }

    @Override
    public TileType getTileType() {
        return TileType.HELICOPTER;
    }
}
