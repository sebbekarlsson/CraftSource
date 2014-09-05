package nu.sebka.craftsource.core;


import nu.sebka.craftsource.TextureBank;
import nu.sebka.craftsource.blocks.BlockType;

import org.lwjgl.opengl.GL11;

public class ModelBank {

    public static void cube(float x, float y, float z,float size, BlockType type){

        GL11.glPushMatrix();
        if (type.getBack() != null) {
            type.getBack().bind();
        }else{
        	TextureBank.EMPTY.bind();
        }
        GL11.glMatrixMode(GL11.GL_TEXTURE);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.5f,0.5f,0.0f);
        GL11.glRotatef(90f,0.0f,0.0f,1.0f);
        GL11.glTranslatef(-0.5f,-0.5f,0.0f);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        // White side - BACK

        GL11.glBegin(GL11.GL_POLYGON);

        GL11.glNormal3f(-1, 0, 0);
        GL11.glTexCoord2f(-1, 0);
        GL11.glVertex3f(x + size, y + -size, z + size);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex3f(x + size, y + size, z + size);
        GL11.glTexCoord2f(0, -1);
        GL11.glVertex3f(x + -size, y + size, z + size);
        GL11.glTexCoord2f(-1, -1);
        GL11.glVertex3f(x + -size, y + -size, z + size);
        GL11.glEnd();
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        if (type.getFront() != null) {
            type.getFront().bind();
        }else{
        	TextureBank.EMPTY.bind();
        }

        // White side - FRONT
        GL11.glBegin(GL11.GL_POLYGON);

        GL11.glNormal3f(1, 0, 0);
        GL11.glTexCoord2f(-1, 0);
        GL11.glVertex3f(x + size, y + -size, z - size);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex3f(x + size, y + size, z - size);
        GL11.glTexCoord2f(0, -1);
        GL11.glVertex3f(x + -size, y + size, z - size);
        GL11.glTexCoord2f(-1, -1);
        GL11.glVertex3f(x + -size, y + -size, z - size);
        GL11.glEnd();
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        if (type.getRight() != null) {
            type.getRight().bind();
        }else{
        	TextureBank.EMPTY.bind();
        }

        // Purple side - RIGHT
        GL11.glBegin(GL11.GL_POLYGON);

        GL11.glNormal3f(0, -1, 0);
        GL11.glTexCoord2f(-1, 0);
        GL11.glVertex3f(x + size, y + -size, z + -size);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex3f(x + size, y + size, z + -size);
        GL11.glTexCoord2f(0, -1);
        GL11.glVertex3f(x + size, y + size, z + size);
        GL11.glTexCoord2f(-1, -1);
        GL11.glVertex3f(x + size, y + -size, z + size);
        GL11.glEnd();
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        if (type.getLeft() != null) {
            type.getLeft().bind();
        }else{
        	TextureBank.EMPTY.bind();
        }


        // Green side - LEFT
        GL11.glBegin(GL11.GL_POLYGON);

        GL11.glNormal3f(0, 0, -1);
        GL11.glTexCoord2f(-1, 0);
        GL11.glVertex3f(x + -size, y + -size, z + size);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex3f(x + -size, y + size, z + size);
        GL11.glTexCoord2f(0, -1);
        GL11.glVertex3f(x + -size, y + size, z + -size);
        GL11.glTexCoord2f(-1, -1);
        GL11.glVertex3f(x + -size, y + -size, z + -size);
        GL11.glEnd();
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        if (type.getBottom() != null) {
            type.getBottom().bind();
        }else{
        	TextureBank.EMPTY.bind();
        }

        // Blue side - Bottom
        GL11.glBegin(GL11.GL_POLYGON);

        GL11.glNormal3f(0, 0, 1);
        GL11.glTexCoord2f(-1, 0);
        GL11.glVertex3f(x + size, y + size, z + size);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex3f(x + size, y + size, z + -size);
        GL11.glTexCoord2f(0, -1);
        GL11.glVertex3f(x + -size, y + size, z + -size);
        GL11.glTexCoord2f(-1, -1);
        GL11.glVertex3f(x + -size, y + size, z + size);
        GL11.glEnd();
        GL11.glPopMatrix();


        GL11.glPushMatrix();
        if (type.getTop() != null) {
            type.getTop().bind();
        }else{
        	TextureBank.EMPTY.bind();
        }

        // Red side - Top

        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glNormal3f(0, 1, 0);
        GL11.glTexCoord2f(-1, 0);
        GL11.glVertex3f(x + size, y + -size, z + -size);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex3f(x + size, y + -size, z + size);
        GL11.glTexCoord2f(0, -1);
        GL11.glVertex3f(x + -size, y + -size, z + size);
        GL11.glTexCoord2f(-1, -1);
        GL11.glVertex3f(x + -size, y + -size, z + -size);
        GL11.glEnd();

        GL11.glPopMatrix();


    }
}
