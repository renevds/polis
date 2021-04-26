package actors;
import javafx.scene.Node;
import polis.GameController;
import polis.tiles.Tile;


public abstract class Actor{
    GameController gameController;
    Tile simParent;
    Node view;

    public Actor(GameController gameController, Tile simParent){
        this.gameController = gameController;
        this.simParent = simParent;
    }

    public void remove(){
        gameController.removeActor(this);
        if(view != null && gameController.getGameGrid().getChildren().contains(view)) {
            gameController.getGameGrid().removeChildren(view);
        }
    }
    public abstract void step();

    public Node getView(){
        return view;
    }

    public GameController getGameController() {
        return gameController;
    }

    public Tile getSimParent(){
        return simParent;
    }
}
