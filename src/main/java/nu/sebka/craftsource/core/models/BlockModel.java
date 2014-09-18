package nu.sebka.craftsource.core.models;

import java.awt.Color;

import nu.sebka.craftsource.blocks.BlockType;

import org.lwjgl.opengl.GL11;

public class BlockModel {
	public Color[] colors = new Color[]{
			new Color(1,1,1),
			new Color(1,1,1),
			new Color(1,1,1),
			new Color(1,1,1),
			new Color(1,1,1),
			new Color(1,1,1),
	};
	
	
	public void draw(float x, float y, float z, float size, BlockType type){
		
        if (type.getBack() != null) {
            type.getBack().bind();
        }

        
        // White side - BACK

        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glColor3f(colors[0].getRed(), colors[0].getGreen(), colors[0].getBlue());
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
        

        
        if (type.getFront() != null) {
            type.getFront().bind();
        }

        // White side - FRONT
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glColor3f(colors[1].getRed(), colors[1].getGreen(), colors[1].getBlue());
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
        

        
        if (type.getRight() != null) {
            type.getRight().bind();
        }

        // Purple side - RIGHT
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glColor3f(colors[2].getRed(), colors[2].getGreen(), colors[2].getBlue());
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
        

        
        if (type.getLeft() != null) {
            type.getLeft().bind();
        }


        // Green side - LEFT
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glColor3f(colors[3].getRed(), colors[3].getGreen(), colors[3].getBlue());
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
        

        
        if (type.getBottom() != null) {
            type.getBottom().bind();
        }

        // Blue side - Bottom
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glColor3f(colors[4].getRed(), colors[4].getGreen(), colors[4].getBlue());
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
        


        
        if (type.getTop() != null) {
            type.getTop().bind();
        }

        // Red side - Top

        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glColor3f(colors[5].getRed(), colors[5].getGreen(), colors[5].getBlue());
        GL11.glNormal3f(0, 1, 0);
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
	
}
