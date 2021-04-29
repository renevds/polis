package polis.tiles;

import polis.GameController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class MultiTile extends Tile {
    protected int width;
    protected int height;

    private List<MultiTileFiller> childrenTiles = new ArrayList<>();

    public MultiTile(int x, int y, GameController gameController) {
        super(x, y, gameController);
    }

    public void addFillerTile(MultiTileFiller multiTileFiller) {
        childrenTiles.add(multiTileFiller);
    }

    public int getWidth(){
        return width;
    };

    public int getHeight(){
        return height;
    };

    @Override
    public void remove(){
        for (MultiTileFiller multiTileFiller : childrenTiles) {
            multiTileFiller.remove();
        }
        gameController.getPC().getGameGrid().getChildren().remove(eventNode);
    }

    public List<Street> getNeigbouringFreeStreets(){
        List<Street> neighbours = new ArrayList<>();
        Set<Tile> tilesToCheck = new HashSet<>(gameGrid.getNeighbours(this));
        for(MultiTileFiller multiTileFiller: childrenTiles){
            tilesToCheck.addAll(gameGrid.getNeighbours(multiTileFiller));
        }
        for(Tile tile: tilesToCheck){
            if(tile.getTileType() == TileType.STREET){
                neighbours.add((Street) tile);
            }
        }
        return neighbours;
    }
}
