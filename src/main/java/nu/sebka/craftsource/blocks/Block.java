package nu.sebka.craftsource.blocks;

import java.util.Random;

import nu.sebka.craftsource.CraftSource;
import nu.sebka.craftsource.World;

import nu.sebka.craftsource.core.Entity;
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
    }



    public void tick() {
        if(this.equals(CraftSource.getCurrentWorld().cam.getFacingBlock())){
            r = 1; g = 0; b = 1;
        }else{
            r = 1; g = 1; b = 1;
        }
    }

    public void draw() {




        if (blockType.getBack() != null) {
            blockType.getBack().bind();
        }


        // White side - BACK
        GL11.glBegin(GL11.GL_POLYGON);


        GL11.glColor3f(r, g, b);
        GL11.glNormal3f(-1, 0, 0);

        GL11.glTexCoord2f(1, 0);
        GL11.glVertex3f(x + size, y + -size, z + size);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex3f(x + size, y + size, z + size);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex3f(x + -size, y + size, z + size);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex3f(x + -size, y + -size, z + size);
        GL11.glEnd();


        if (blockType.getFront() != null) {
            blockType.getFront().bind();
        }

        // White side - FRONT
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glColor3f(r, g, b);
        GL11.glNormal3f(1, 0, 0);

        GL11.glTexCoord2f(1, 0);
        GL11.glVertex3f(x + size, y + -size, z - size);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex3f(x + size, y + size, z - size);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex3f(x + -size, y + size, z - size);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex3f(x + -size, y + -size, z - size);
        GL11.glEnd();


        if (blockType.getRight() != null) {
            blockType.getRight().bind();
        }

        // Purple side - RIGHT
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glColor3f(r, g, b);
        GL11.glNormal3f(0, -1, 0);

        GL11.glTexCoord2f(1, 0);
        GL11.glVertex3f(x + size, y + -size, z + -size);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex3f(x + size, y + size, z + -size);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex3f(x + size, y + size, z + size);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex3f(x + size, y + -size, z + size);
        GL11.glEnd();


        if (blockType.getLeft() != null) {
            blockType.getLeft().bind();
        }


        // Green side - LEFT
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glColor3f(r, g, b);
        GL11.glNormal3f(0, 0, -1);

        GL11.glTexCoord2f(1, 0);
        GL11.glVertex3f(x + -size, y + -size, z + size);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex3f(x + -size, y + size, z + size);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex3f(x + -size, y + size, z + -size);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex3f(x + -size, y + -size, z + -size);
        GL11.glEnd();


        if (blockType.getBottom() != null) {
            blockType.getBottom().bind();
        }

        // Blue side - Bottom
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glColor3f(r, g, b);
        GL11.glNormal3f(0, 0, 1);

        GL11.glTexCoord2f(1, 0);
        GL11.glVertex3f(x + size, y + size, z + size);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex3f(x + size, y + size, z + -size);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex3f(x + -size, y + size, z + -size);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex3f(x + -size, y + size, z + size);
        GL11.glEnd();

        if (blockType.getTop() != null) {
            blockType.getTop().bind();
        }

        // Red side - Top
        GL11.glBegin(GL11.GL_POLYGON);

        GL11.glColor3f(r, g, b);
        GL11.glNormal3f(0, 1, 0);


        for (int i = 0; i < 32; i++) {
            if (!(CraftSource.getCurrentWorld().getBlockAtPrecise(x, y - Block.getSize() - Block.getSize() * i, z).getType().equals(BlockType.AIR))){
                GL11.glColor3f(0.6f, 0.6f, 0.6f);
                break;

            } else {
                GL11.glColor3f(r, g, b);
            }


            //else if(!(Main.getCurrentWorld().getBlockAtPrecise(x,y-Block.getSize()*3, z) instanceof AirBlock)){
            //GL11.glColor3f(0.8f, 0.8f, 0.8f);

            //}

        }


        GL11.glTexCoord2f(1, 0);
        GL11.glVertex3f(x + size, y + -size, z + -size);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex3f(x + size, y + -size, z + size);
        GL11.glTexCoord2f(0, 1);
        GL11.glVertex3f(x + -size, y + -size, z + size);
        GL11.glTexCoord2f(1, 1);
        GL11.glVertex3f(x + -size, y + -size, z + -size);
        GL11.glEnd();


    }

    public static float getSize() {
        return size * 2;
    }
    public BlockType getType(){
        return blockType;
    }



}
