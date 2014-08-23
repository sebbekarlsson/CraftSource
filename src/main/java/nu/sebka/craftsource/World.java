package  nu.sebka.craftsource;

import  nu.sebka.craftsource.blocks.AirBlock;
import nu.sebka.craftsource.blocks.Block;
import  nu.sebka.craftsource.blocks.GrassBlock;
import nu.sebka.craftsource.core.Entity;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class World {

    public ArrayList<Entity> locations = new ArrayList<Entity>();
    public Camera cam = new Camera(70, (float) CraftSource.WIDTH / (float) CraftSource.HEIGHT, 0.03f, 1000);

    public World() {

        cam.setY(-Block.getSize() * 2);

        for (int i = 0; i < 16; i += 1) {
            for (int ii = 0; ii < 16; ii += 1) {
                locations.add(new GrassBlock(i * Block.getSize(), 0, -ii * Block.getSize()));

            }
        }
    }

    public void tick() {
        for (Entity location : locations) {
            location.tick();
        }
    }

    public void draw() {


        GL11.glLoadIdentity();
        cam.tick();
        cam.useView();


        GL11.glPushMatrix();
        for (Entity location : locations) {

            location.draw();


        }
        GL11.glPopMatrix();


    }


    public Block getBlockAtPrecise(double x, double y, double z) {
        for (int i = 0; i < locations.size(); i++) {
            Entity location = locations.get(i);
            if (
                    x >= location.getX() && x <= location.getX() &&
                            y >= location.getY() && y <= location.getY() &&
                            z >= location.getZ() && z <= location.getZ()

                    ) {
                return (Block) location;
            }
        }

        return new AirBlock((float) x, (float) y, (float) z);
    }


    public Block getBlockAt(double x, double y, double z) {
        for (int i = 0; i < locations.size(); i++) {
            Entity location = locations.get(i);
            if (
                    x >= location.getX() - Block.getSize() / 2 && x <= location.getX() + Block.getSize() &&
                            y >= location.getY() - Block.getSize() / 2 && y <= location.getY() + Block.getSize() &&
                            z >= location.getZ() - Block.getSize() / 2 && z <= location.getZ() + Block.getSize()

                    ) {
                return (Block) location;
            }
        }

        return new AirBlock((float) x, (float) y, (float) z);
    }

}
