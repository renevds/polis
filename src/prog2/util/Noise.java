package prog2.util;

import java.util.Random;

public class Noise {
    /* Perlin Noise generator genomen en aangepast van https://stackoverflow.com/questions/5531019/perlin-noise-in-java door jt78
    * deze wordt gebruikt voor het genereren van mooiere patronen voor de achtergrondtegels */

    private final Random rand_;

    private final float roughness_;

    private final float[][] grid;

    public Noise(Random rand, float roughness, int width, int height) {
        roughness_ = roughness / width;
        grid = new float[width][height];
        rand_ = (rand == null) ? new Random() : rand;
    }

    public void initialise() {
        int xh = grid.length - 1;
        int yh = grid[0].length - 1;

        grid[0][0] = rand_.nextFloat() - 0.5f;
        grid[0][yh] = rand_.nextFloat() - 0.5f;
        grid[xh][0] = rand_.nextFloat() - 0.5f;
        grid[xh][yh] = rand_.nextFloat() - 0.5f;

        generate(0, 0, xh, yh);
    }

    private float roughen(float v, int l, int h) {
        return v + roughness_ * (float) (rand_.nextGaussian() * (h - l));
    }

    private void generate(int xl, int yl, int xh, int yh) {
        int xm = (xl + xh) / 2;
        int ym = (yl + yh) / 2;
        if ((xl == xm) && (yl == ym)) return;

        grid[xm][yl] = 0.5f * (grid[xl][yl] + grid[xh][yl]);
        grid[xm][yh] = 0.5f * (grid[xl][yh] + grid[xh][yh]);
        grid[xl][ym] = 0.5f * (grid[xl][yl] + grid[xl][yh]);
        grid[xh][ym] = 0.5f * (grid[xh][yl] + grid[xh][yh]);

        float v = roughen(0.5f * (grid[xm][yl] + grid[xm][yh]), xl + yl, yh
                + xh);
        grid[xm][ym] = v;
        grid[xm][yl] = roughen(grid[xm][yl], xl, xh);
        grid[xm][yh] = roughen(grid[xm][yh], xl, xh);
        grid[xl][ym] = roughen(grid[xl][ym], yl, yh);
        grid[xh][ym] = roughen(grid[xh][ym], yl, yh);

        generate(xl, yl, xm, ym);
        generate(xm, yl, xh, ym);
        generate(xl, ym, xm, yh);
        generate(xm, ym, xh, yh);
    }


    public float[][] getGrid() {
        return grid;
    }
}
