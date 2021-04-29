package polis.actors;
import javafx.scene.Node;
import polis.GameController;
import polis.tiles.ResidentialTile;
import polis.tiles.Tile;
import polis.tiles.ZoneTile;


public abstract class Actor{
    GameController gameController;

    Node view;
    protected int age = 0;
    private int MAX_AGE;

    public Actor(GameController gameController, int maxAge){
        this.gameController = gameController;
        this.MAX_AGE = maxAge;
        gameController.addActor(this);
    }

    public void remove(){
        gameController.removeActor(this);
        if(view != null && gameController.getGameGrid().getChildren().contains(view)) {
            gameController.getGameGrid().removeChildren(view);
        }
    }
    public void step(){
        age += 1;
        if(age > MAX_AGE){
            gameController.removeActor(this);
            gameController.getGameGrid().getChildren().remove(view);
            dieEffect();
        }
        else {
            notDeadStep();
        }
    };

    protected abstract void dieEffect();

    protected abstract void notDeadStep();

    public Node getView(){
        return view;
    }

    public GameController getGameController() {
        return gameController;
    }

}
