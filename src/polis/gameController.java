package polis;

import polis.Tiles.StandardTile;
import polis.Tiles.Street;
import polis.Tiles.Tile;
import polis.tools.Selector;
import polis.tools.Tool;

import java.util.ArrayList;
import java.util.List;

public class gameController {
    polisController PC;
    static int MAP_SIZE = 32;
    List<Tile> tiles = new ArrayList<>();
    Tool tool;

    public gameController(polisController PC){
        this.PC = PC;
        createTiles();
    }

    public void createTiles(){
        for (int x = 1; x <= MAP_SIZE; x++){
            for (int y = 1; y <= MAP_SIZE; y++){
                Tile temp = new StandardTile(x, y, this);
                tiles.add(temp);
            }
        }
        Street immigrantRoad = new Street(16, 1, this);
        replaceTile(16, 1, immigrantRoad);
    }

    public static int getMAPSIZE(){
        return MAP_SIZE;
    }

    List<Tile> getTiles(){
        return tiles;
    }

    void drawTiles(){
        tiles.forEach(Tile::draw);
    }

    public polisController getPC(){
        return PC;
    }

    public int coordToIndex(int x, int y){
        return x*MAP_SIZE - 1 + y;
    }

    public void replaceTile(int x, int y, Tile newTile){
        Tile oldtile = getTileAtCoord(x, y);
        if (oldtile.removable()){
            oldtile.remove();
            tiles.set(coordToIndex(x, y), newTile);
        }

    }

    public Tile getTileAtCoord(int x, int y){
        return tiles.get(coordToIndex(x, y));
    }

    public boolean validCoord(int coord){
        return coord > 0 && coord <= MAP_SIZE;
    }

    public void setTool(Tool tool){
        this.tool = tool;
    }
}
