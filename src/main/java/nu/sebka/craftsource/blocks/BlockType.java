package nu.sebka.craftsource.blocks;

import nu.sebka.craftsource.TextureBank;
import org.newdawn.slick.opengl.Texture;

/**
 * Created by brandon on 8/23/14.
 */
public enum BlockType {

    AIR(null, null, null, null, null, null),
    LOG(TextureBank.LOG_TOP, TextureBank.LOG_TOP, TextureBank.LOG_SIDE, TextureBank.LOG_SIDE, TextureBank.LOG_SIDE, TextureBank.LOG_SIDE),
    COBBLE(TextureBank.COBBLE_TEXTURE, TextureBank.COBBLE_TEXTURE, TextureBank.COBBLE_TEXTURE, TextureBank.COBBLE_TEXTURE, TextureBank.COBBLE_TEXTURE ,TextureBank.COBBLE_TEXTURE),
    GRASS(TextureBank.GRASS_TEXTURE, TextureBank.DIRT_TEXTURE,TextureBank.DIRT_TEXTURE,TextureBank.DIRT_TEXTURE,TextureBank.DIRT_TEXTURE,TextureBank.DIRT_TEXTURE),
    PLANKS(TextureBank.PLANKS_TEXTURE, TextureBank.PLANKS_TEXTURE,TextureBank.PLANKS_TEXTURE,TextureBank.PLANKS_TEXTURE,TextureBank.PLANKS_TEXTURE,TextureBank.PLANKS_TEXTURE),
    GLASS(TextureBank.GLASS, TextureBank.GLASS,TextureBank.GLASS,TextureBank.GLASS,TextureBank.GLASS,TextureBank.GLASS),
    SAND(TextureBank.SAND, TextureBank.SAND,TextureBank.SAND,TextureBank.SAND,TextureBank.SAND,TextureBank.SAND),
    GRAVEL(TextureBank.GRAVEL, TextureBank.GRAVEL,TextureBank.GRAVEL,TextureBank.GRAVEL,TextureBank.GRAVEL,TextureBank.GRAVEL),
    LEAF(TextureBank.LEAF, TextureBank.LEAF,TextureBank.LEAF,TextureBank.LEAF,TextureBank.LEAF,TextureBank.LEAF),
    PUMPKIN(TextureBank.PUMPKIN_TOP, TextureBank.PUMPKIN_TOP,TextureBank.PUMPKIN_SIDE,TextureBank.PUMPKIN_SIDE,TextureBank.PUMPKIN_SIDE,TextureBank.PUMPKIN_SIDE),
    CHEST(TextureBank.PLANKS_TEXTURE, TextureBank.PLANKS_TEXTURE,TextureBank.CHEST_SIDE,TextureBank.CHEST_SIDE,TextureBank.CHEST_SIDE,TextureBank.CHEST_SIDE),
    FURNACE(TextureBank.FURNACE_TOP, TextureBank.FURNACE_TOP,TextureBank.FURNACE_FRONT,TextureBank.FURNACE_FRONT,TextureBank.FURNACE_FRONT,TextureBank.FURNACE_FRONT),
    WHEAT(null,null,TextureBank.WHEAT,TextureBank.WHEAT,TextureBank.WHEAT,TextureBank.WHEAT),
    WORKBENCH(TextureBank.WORKBENCH_TOP, TextureBank.PLANKS_TEXTURE,TextureBank.WORKBENCH_SIDE,TextureBank.WORKBENCH_SIDE,TextureBank.WORKBENCH_SIDE,TextureBank.WORKBENCH_SIDE);

    private Texture top, bottom, front, back, left, right;

    BlockType(Texture top, Texture bottom, Texture front, Texture back, Texture left, Texture right) {
        this.top = top;
        this.bottom = bottom;
        this.front = front;
        this.back = back;
        this.left = left;
        this.right = right;
    }

    public Texture getTop() {
        return top;
    }

    public Texture getBottom() {
        return bottom;
    }

    public Texture getFront() {
        return front;
    }

    public Texture getBack() {
        return back;
    }

    public Texture getLeft() {
        return left;
    }

    public Texture getRight() {
        return right;
    }
}
