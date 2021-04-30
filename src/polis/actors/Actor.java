package polis.actors;
import javafx.scene.Node;
import polis.GameController;
import polis.tiles.ResidentialTile;


public abstract class Actor{
    // generale Actor zoals slapers of goederen

    protected final GameController gameController;

    Node view;
    protected int age;
    protected final int MAX_AGE;

    protected final ResidentialTile parentResidential;

    public Actor(GameController gameController, ResidentialTile parentResidential, int maxAge){
        this.gameController = gameController;
        this.MAX_AGE = maxAge;
        gameController.addActor(this);
        this.parentResidential = parentResidential;
    }

    public enum ActorType{
        CUSTOMER, GOOD, IMMIGRANT, JOBSEEKER, SHOPPER, SLEEPER, TRADER, WORKER
    }

    //dit verwijdert de Actor van de simulatie
    public void remove(){
        gameController.removeActor(this);
        if(view != null && gameController.getGameGrid().getChildren().contains(view)) {
            gameController.getGameGrid().removeChildren(view);
        }
    }

    //deze functie wordt elke frame opgeroepen
    public void step(){
        age += 1;
        if(age > MAX_AGE){
            dieEffect();
        }
        else {
            notDeadStep();
        }
    }

    //deze functie wordt opgeroepen als de Actor sterft door zijn leeftijd
    protected abstract void dieEffect();

    //deze functie wordt opgeroepen elke stap dat de Actor leeft
    protected abstract void notDeadStep();

    public Node getView(){
        return view;
    }

    public GameController getGameController() {
        return gameController;
    }

    //om te gebruiken met de enum
    public abstract ActorType getType();

    //zichzelf vervangen in zijn thuisplaats
    public void replaceSelfInParentResidential(Actor actor){
        parentResidential.replaceResident(this, actor);
    }

    public ResidentialTile getHomeResidential() {
        return parentResidential;
    }
}
