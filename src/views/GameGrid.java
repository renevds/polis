package views;

import javafx.scene.layout.Pane;
import polis.gameController;
import polis.polisController;
import polis.tiles.*;

import java.util.ArrayList;
import java.util.List;

public class GameGrid extends Pane {

    private int MAP_SIZE;
    private List<Tile> tiles;
    private gameController GC;

    public GameGrid(gameController GC, int MAP_SIZE) {
        this.MAP_SIZE = MAP_SIZE;
        tiles = new ArrayList<>();
        this.GC = GC;
    }

    public void createTiles() {
        for (int x = 1; x <= MAP_SIZE; x++) {
            for (int y = 1; y <= MAP_SIZE; y++) {
                Tile temp = new StandardTile(x, y, GC);
                tiles.add(temp);
            }
        }
    }

    public int getMAP_SIZE() {
        return MAP_SIZE;
    }

    public void createImmigrantRoad() {
        Street immigrantRoad = new Street(16, 1, GC);
        immigrantRoad.makeUnRemovable();
        replaceTile(immigrantRoad);
    }

    List<Tile> getTiles() {
        return tiles;
    }

    public void drawTiles() {
        tiles.forEach(Tile::draw);
    }

    public int coordToIndex(int x, int y) {
        return (x - 1) * MAP_SIZE + y - 1;
    }


    public void replaceTile(Tile newTile) {
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

    public void replaceMultiTile(ZoneTile newTile, int width) {
        System.out.println(tiles);
        int x = newTile.getX();
        int y = newTile.getY();
        for (int dx = 0; dx < width; dx++) {
            for (int dy = 0; dy < width; dy++) {
                if (dx == 0 && dy == 0) {
                    replaceTile(newTile);
                } else {
                    replaceTile(new ZoneFiller(x + dx, y + dy, GC, newTile));
                }
            }
        }
        System.out.println(tiles);
    }

    public Tile getTileAtCoord(int x, int y) {
        return tiles.get(coordToIndex(x, y));
    }

    public boolean validCoord(int coord) {
        return coord > 0 && coord <= MAP_SIZE;
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

    public double getRenderY(int x, int y){
        return (double)polisController.getCELLSIZE() * (y + x) / 2 - polisController.getCELLSIZE()/2.0;
    }

    public double getRenderX(int x, int y){
        return -((double)polisController.getCELLSIZE()*MAP_SIZE)/2 + polisController.getCELLSIZE() * (polisController.getSize() - y + x);
    }

}
