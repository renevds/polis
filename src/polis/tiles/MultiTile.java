package polis.tiles;

import polis.GameController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class MultiTile extends Tile {
    protected int width;
    protected int height;

    private final List<MultiTileFiller> childrenTiles;

    /* MultiTile stelt een tegel voor die groter is als een vakje
    * om hier gemakkelijk mee om te gaan wordt in de rechterbovenhoek in de grid van een grote tegel een MultiTile gestoken
    * elke andere plek wordt dan gevuld met MultiTileFiller die deze class bijhoud */

    public MultiTile(int x, int y, GameController gameController) {
        super(x, y, gameController);
        childrenTiles  = new ArrayList<>();
    }

    public void addFillerTile(MultiTileFiller multiTileFiller) {
        childrenTiles.add(multiTileFiller);
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    @Override
    public void remove(){
        for (MultiTileFiller multiTileFiller : childrenTiles) {
            multiTileFiller.remove();
        }
        gameController.getPC().getGameGrid().getChildren().remove(eventNode);
    }

    //zoek een aangrenzende straat om Actoren op te plaatsen van deze Tile of een van de Fillers
    public List<Street> getNeigbouringStreets(){
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
