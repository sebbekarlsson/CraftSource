package  nu.sebka.craftsource;


import  nu.sebka.craftsource.blocks.GrassBlock;


public class Light extends GrassBlock {


    public Light(float x, float y, float z) {
        super(x, y, z);

    }

    @Override
    public void tick() {
        y += 0.01f;

    }


    @Override
    public void draw() {


    }


}
