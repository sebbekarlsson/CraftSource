package  nu.sebka.craftsource.blocks;

import  nu.sebka.craftsource.TextureBank;

public class CobbleBlock extends Block {

    public CobbleBlock(World float x, float y, float z) {
        super(x, y, z);
        textures[0] = TextureBank.COBBLE_TEXTURE;
        textures[1] = TextureBank.COBBLE_TEXTURE;
        textures[2] = TextureBank.COBBLE_TEXTURE;
        textures[3] = TextureBank.COBBLE_TEXTURE;
        textures[4] = TextureBank.COBBLE_TEXTURE;
        textures[5] = TextureBank.COBBLE_TEXTURE;
    }

}
