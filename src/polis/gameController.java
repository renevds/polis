package polis;

import polis.Tools.Selector;
import polis.tiles.*;
import polis.Tools.Tool;

import java.util.ArrayList;
import java.util.List;

public class gameController {
    private polisController PC;
    private static int MAP_SIZE = 32;
    private List<Tile> tiles = new ArrayList<>();
    private Tool tool;

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
        System.out.println("test");
        Tile oldtile = getTileAtCoord(x, y);
        System.out.println("removing from zoneTile: " + oldtile);
        tiles.set(coordToIndex(x, y), newTile);
        oldtile.remove();
        newTile.draw();
        fixLayers();
        System.out.println(tiles);
    }

    public void replaceMultiTile(ZoneTile newTile, int width){
        System.out.println(tiles);
        int x = newTile.getX();
        int y = newTile.getY();
        for(int dx = 0; dx < width; dx++){
            for(int dy = 0; dy < width; dy++){
                if(dx == 0 && dy == 0 ){
                    replaceTile(newTile);
                }
                else {
                    replaceTile(new ZoneFiller(x + dx, y + dy, this, newTile));
                }
            }
        }
        System.out.println(tiles);
    }

    public Tile getTileAtCoord(int x, int y){
        return tiles.get(coordToIndex(x, y));
    }

    public boolean validCoord(int coord){
        return coord > 0 && coord <= MAP_SIZE;
    }

    public void setTool(Tool tool){
            this.tool.close();
            this.tool = tool;
    }

    public void setCurrentHover(Tile tile){
        tool.hover(tile);
    }

    public void setClicked(Tile tile) {
        tool.clicked(tile);
    }

    public void setDrag(Tile tile) {
        tool.drag(tile);
    }

    public void setRelease(Tile tile) {
        tool.release(tile);
    }

    public void setTile(Tile newTile){
        int x = newTile.getX();
        int y = newTile.getY();
        tiles.set(coordToIndex(x, y), newTile);
        newTile.draw();
    }

    public void fixLayers(){
        for(int a = 1; a <= MAP_SIZE; a++){
            for (int b = 1; b <= MAP_SIZE; b++){
                Tile tile = getTileAtCoord(b, a);
                if(!(tile instanceof Street)){
                    tile.toFront();
                }
            }
        }
    }

}
