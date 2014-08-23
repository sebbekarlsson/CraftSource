package  nu.sebka.craftsource.blocks;

import  nu.sebka.craftsource.Block;
import  nu.sebka.craftsource.TextureBank;

public class GrassBlock extends Block {

    public GrassBlock(float x, float y, float z) {
        super(x, y, z);
        textures[0] = TextureBank.DIRT_TEXTURE;
        textures[1] = TextureBank.DIRT_TEXTURE;
        textures[2] = TextureBank.DIRT_TEXTURE;
        textures[3] = TextureBank.DIRT_TEXTURE;
        textures[4] = TextureBank.DIRT_TEXTURE;
        textures[5] = TextureBank.GRASS_TEXTURE;
    }


}
