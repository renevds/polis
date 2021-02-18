package polis;

import polis.Tools.Selector;
import polis.tiles.StandardTile;
import polis.tiles.Street;
import polis.tiles.Tile;
import polis.Tools.Tool;

import java.util.ArrayList;
import java.util.List;

public class gameController {
    polisController PC;
    static int MAP_SIZE = 32;
    List<Tile> tiles = new ArrayList<>();
    Tool tool;
    Tile currentHover;

    public gameController(polisController PC){
        this.PC = PC;
        createTiles();
        this.tool = new Selector(this);
    }

    public void createTiles(){
        for (int x = 1; x <= MAP_SIZE; x++){
            for (int y = 1; y <= MAP_SIZE; y++){
                Tile temp = new StandardTile(x, y, this);
                tiles.add(temp);
            }
        }
    }

    public void createImmigrantRoad(){
        Street immigrantRoad = new Street(16, 1, this);
        immigrantRoad.makeUnRemovable();
        replaceTile(immigrantRoad);
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
        return (x-1)*MAP_SIZE + y - 1;
    }

    public void replaceTile(Tile newTile){
        int x = newTile.getX();
        int y = newTile.getY();
        Tile oldtile = getTileAtCoord(x, y);
        if (oldtile.removable()){
            oldtile.remove();
            tiles.set(coordToIndex(x, y), newTile);
        }
        newTile.draw();

    }

    public Tile getTileAtCoord(int x, int y){
        return tiles.get(coordToIndex(x, y));
    }

    public boolean validCoord(int coord){
        return coord > 0 && coord <= MAP_SIZE;
    }

    public void setTool(Tool tool){
        if(tool.getClass() != this.tool.getClass()){
            this.tool.close();
            this.tool = tool;
        }
    }

    public void setCurrentHover(Tile tile){
        currentHover = tile;
        tool.hover(currentHover);
    }

    public void setClicked(Tile tile) {
        tool.clicked(tile);
    }
}
