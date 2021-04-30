package polis.tiles;

import javafx.scene.Node;
import polis.GameController;
import polis.actors.Actor;
import polis.views.GameGrid;

abstract public class Tile {
    protected final GameController gameController;
    protected final int x;
    protected final int y;

    protected Node eventNode;

    protected final GameGrid gameGrid;

    //Deze klasse stelt een tegel voor

    public Tile(int x, int y, GameController gameController) {
        this.x = x;
        this.y = y;
        this.gameController = gameController;
        gameGrid = gameController.getPC().getGameGrid();

    }

    public enum TileType {
        COMMERCIAL, INDUSTRIAL, STREET, RESIDENTIAL, HELICOPTER, STANDARD, FILLER
    }

    //te gebruiken met bovenstaande enum
    public abstract TileType getTileType();

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    //om zichzelf van de simulatie te verwijderen
    public abstract void remove();

    //can tegels onverwijderbaar maken zoals de initiële straat
    public abstract Boolean removable();

    /* onderstaande functies worden allemaal gebonden aan een muisevent
    * de GameController wordt dan geupdate wanneer een tegel een bepaalde event ontvangt
    * de GameController verwittigd dan de momenteel gekozen Tool */

    private void hover(){
        gameController.setCurrentHover(this);
    }

    private void clicked(){
        gameController.setClicked(this);
    }

    private void drag(){
        gameController.setDrag(this);
    }

    private void release(){
        gameController.setRelease(this);
    }

    private void startDrag(Node node){
        if(gameController.getPC().getGameGrid().getChildren().contains(eventNode)){
            node.startFullDrag();
        }
    }

    public void createEvents(Node node){
        node.setOnMouseEntered(mouseEvent  -> hover());
        node.setOnMousePressed(mouseEvent  -> clicked());
        node.setOnDragDetected(mouseEvent -> startDrag(node));
        node.setOnMouseDragOver(mouseEvent -> drag());
        node.setOnMouseReleased(mouseEvent -> release());
    }

    public GameGrid getGameGrid(){
        return gameGrid;
    }

    //simulatie stap elke frame
    public abstract void step();


    public boolean acceptsResident(Actor actor){
        return false;
    }

    //sommige tegels maken gebruik van Filler (zie MultiTileFiller) deze methode geeft de Parent terug indien deze tegel een Filler is
    public Tile getParentTile(){
        return this;
    }

}
