package views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import polis.polisController;
import prog2.util.Noise;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BackgroundTile extends ImageView {

    static Image grass = new Image("polis/tiles/backgrounds/grass.png");
    static Image forest1 = new Image("polis/tiles/backgrounds/forest1.png");
    static Image forest2 = new Image("polis/tiles/backgrounds/forest2.png");
    static Image forest3 = new Image("polis/tiles/backgrounds/forest3.png");
    static Image transmitter = new Image("polis/tiles/backgrounds/transmitter.png");
    static Image grass_garbage = new Image("polis/tiles/backgrounds/grass_garbage.png");
    static Image water = new Image("polis/tiles/backgrounds/water.png");
    static Image[] forestImages = new Image[]{
            forest3,
            forest2,
            forest2,
            forest1,
            forest1,
            grass,
    };

    static ArrayList<Image> allImages = new ArrayList<>() {
        {
            add(grass);
            add(forest1);
            add(forest2);
            add(forest3);
            add(transmitter);
            add(grass_garbage);
        }
    };

    static float[][] noise;

    public BackgroundTile(int x, int y, GameGrid gameGrid) {
        super();

        if(noise == null){
            regenNoise(gameGrid);
        }


        setImage(grass);

        double decoChance = Math.random();
        if (decoChance < 0.995) {
            float chance = ((noise[x-1][y-1]+1)/2)*forestImages.length;
            System.out.println(chance);
            if(chance < 0.1){
                setImage(water);
            }else{
                setImage(forestImages[Math.max((int)(chance + 0.5)%forestImages.length, 0)]);
            }
        } else if (decoChance < 0.9975) {
            setImage(grass_garbage);
        } else {
            setImage(transmitter);
        }
        gameGrid.addChildrenToGrid(this, x, y, 64, getImage().getHeight() - 64);
        toFront();
        setMouseTransparent(true);
    }

    public static void regenNoise(GameGrid gameGrid){
        Noise temp = new Noise(null, 1f, gameGrid.getMAP_SIZE(), gameGrid.getMAP_SIZE());
        temp.initialise();
        noise = temp.getGrid();
        System.out.println(Arrays.deepToString(noise));
    }

    public void clear() {
        Image newImage = grass;
        setTranslateY(getTranslateY() + getImage().getHeight() - newImage.getHeight());
        setImage(newImage);
    }

    public void changeDecoration() {
        Image newImage = allImages.get((allImages.indexOf(getImage()) + 1)%allImages.size());
        setTranslateY(getTranslateY() + getImage().getHeight() - newImage.getHeight());
        setImage(newImage);
    }

    public void setWater() {
        setImage(water);
    }
}
