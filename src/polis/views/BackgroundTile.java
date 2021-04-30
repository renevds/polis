package polis.views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import prog2.util.Noise;

import java.util.ArrayList;

public class BackgroundTile extends ImageView {

    private static final Image grass = new Image("polis/tiles/backgrounds/grass.png");
    private static final Image forest1 = new Image("polis/tiles/backgrounds/forest1.png");
    private static final Image forest2 = new Image("polis/tiles/backgrounds/forest2.png");
    private static final Image forest3 = new Image("polis/tiles/backgrounds/forest3.png");
    private static final Image transmitter = new Image("polis/tiles/backgrounds/transmitter.png");
    private static final Image grass_garbage = new Image("polis/tiles/backgrounds/grass_garbage.png");
    private static final Image water = new Image("polis/tiles/backgrounds/water.png");
    private static final Image[] forestImages = new Image[]{
            forest3,
            forest2,
            forest2,
            forest1,
            forest1,
            grass,
    };

    private static final ArrayList<Image> allImages = new ArrayList<>() {
        {
            add(grass);
            add(forest1);
            add(forest2);
            add(forest3);
            add(transmitter);
            add(grass_garbage);
        }
    };

    private final int x;
    private final int y;

    private static float[][] noise;

    /* deze klasse stelt een achtergrondtegel voor, achtergrondtegels hebben geen effect op de simulatie maar zijn er puur
    * voor het visueel effect, de vele images worden als statische variabelen opgeslaan in een pogin om Vram te besparen
    * JavaFX is hier heel slecht in en de standaard Vram hoeveelheid is te klein voor meer als 32x32 tegels */

    public BackgroundTile(int x, int y, GameGrid gameGrid) {
        super();

        if(noise == null){
            regenNoise(gameGrid);
        }


        setImage(grass);

        double decoChance = Math.random();
        if (decoChance < 0.995) {
            float chance = ((noise[x-1][y-1]+1)/2)*forestImages.length;
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
        this.x = x;
        this.y = y;

        setViewOrder();
    }

    public static void regenNoise(GameGrid gameGrid){
        Noise temp = new Noise(null, 1f, gameGrid.getMAP_SIZE(), gameGrid.getMAP_SIZE());
        temp.initialise();
        noise = temp.getGrid();
    }

    public void clear() {
        fixTranslateY(grass);
        setImage(grass);
        setViewOrder();
    }

    public void changeDecoration() {
        Image newImage = allImages.get((allImages.indexOf(getImage()) + 1)%allImages.size());
        fixTranslateY(newImage);
        setImage(newImage);
        setViewOrder();
    }

    private void fixTranslateY(Image newImage){
        setTranslateY(getTranslateY() + getImage().getHeight() - newImage.getHeight());
    }

    public void setWater() {
        fixTranslateY(water);
        setImage(water);
        setViewOrder();
    }

    public void setViewOrder() {
        if(getImage() != grass) {
            setViewOrder(-x - y - 2);
        }
        else {
            setViewOrder(-x - y );
        }
    }

}
