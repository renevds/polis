package polis.actors;

import polis.GameController;
import polis.tiles.ResidentialTile;
import polis.tiles.Tile;
import polis.tiles.ZoneTile;

public abstract class ActorWithHome extends Actor{

    protected ResidentialTile parentResidential;

    public ActorWithHome(GameController gameController, ResidentialTile parentResidential, int maxAge) {
        super(gameController, maxAge);
        this.parentResidential = parentResidential;
    }

    public ResidentialTile getParentZone(){
        return parentResidential;
    }

    public void replaceSelfInParentResidential(Actor actor){
        parentResidential.replaceResident(this, actor);
    };

    @Override
    public ResidentialTile getHomeResidential() {
        return parentResidential;
    }
}
