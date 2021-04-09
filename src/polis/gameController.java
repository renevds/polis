package polis;

import polis.Tools.Selector;
import polis.tiles.*;
import polis.Tools.Tool;
import views.GameGrid;

import java.util.ArrayList;
import java.util.List;

public class gameController {
    private GameGrid gameGrid;
    private polisController PC;
    private static int MAP_SIZE = 32;
    private List<Tile> tiles = new ArrayList<>();
    private Tool tool;
    private Tile lastHoverTile;

    public gameController(polisController PC) {
        this.PC = PC;
    }

    public void setGameGrid(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
    }

    public polisController getPC() {
        return PC;
    }

    public void setTool(Tool tool) {
        if (this.tool != null) {
            this.tool.close();
        }
        this.tool = tool;
        if(lastHoverTile != null) {
            tool.hover(lastHoverTile);
        }
    }

    public void setCurrentHover(Tile tile) {
        lastHoverTile = tile;
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


}
