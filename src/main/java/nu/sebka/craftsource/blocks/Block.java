package nu.sebka.craftsource.blocks;

import java.util.Random;

import nu.sebka.craftsource.CraftSource;
import nu.sebka.craftsource.World;

import nu.sebka.craftsource.core.Entity;
import nu.sebka.craftsource.core.ModelBank;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;


public class Block extends Entity {

    private static float size = 0.04f;
    private BlockType blockType;
    Random random = new Random();
    float r = 1,g = 1, b = 1;



    public Block(BlockType type, World world, float x, float y, float z) {
        super(world, x, y, z);
        blockType = type;
        r = random.nextFloat();
        g = random.nextFloat();
        b = random.nextFloat();

    }



    public void tick() {
        Block facingBlock = CraftSource.getCurrentWorld().cam.getFacingBlock();


        if(random.nextInt(5) == 0){
            r = random.nextFloat();
            g = random.nextFloat();
            b = random.nextFloat();
        }
    }

    public void draw() {
        Block facingBlock = CraftSource.getCurrentWorld().cam.getFacingBlock();




        GL11.glColor3f(1,1,1);

        if(this.equals(facingBlock)){
            GL11.glColor3f(r,g,b);
        }

        ModelBank.cube(x,y,z,size,blockType);
    }

    public static float getSize() {
        return size * 2;
    }
    public BlockType getType(){
        return blockType;
    }



}
