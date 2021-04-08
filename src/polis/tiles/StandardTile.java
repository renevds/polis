package polis.tiles;

import polis.Drawers.Square;
import polis.gameController;
import views.StandardTileView;

public class StandardTile extends Tile {


    public StandardTile(int x, int y, gameController GC) {
        super(x, y, GC);
    }

    @Override
    public void draw() {
        mainNode = new StandardTileView(this);
        createEvents(mainNode);
        //Text text = new Text("(" + x + ", " + y + ")");
        //text.setTranslateX(getTileRenderX());
        //text.setTranslateY(getTileRenderY() + polisController.getCELLSIZE()/2);
        //gamePane.getChildren().add(text);
    }


    public void remove() {
        gameGrid.removeChildren(mainNode);
    }

    @Override
    public Boolean removable() {
        return true;
    }

    @Override
    public void toFront() {

    }
}
