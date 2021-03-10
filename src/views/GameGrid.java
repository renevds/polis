package views;

import javafx.scene.layout.Pane;
import polis.tiles.StandardTile;
import polis.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public class GameGrid extends Pane {

    private static int MAP_SIZE = 32;
    private List<Tile> tiles;

    public GameGrid(){
         tiles = new ArrayList<>();
    }

    public void createTiles(){
        for (int x = 1; x <= MAP_SIZE; x++){
            for (int y = 1; y <= MAP_SIZE; y++){
                Tile temp = new StandardTile(x, y, this);
                tiles.add(temp);
            }
        }
    }
}
