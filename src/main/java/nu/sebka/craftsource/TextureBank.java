package  nu.sebka.craftsource;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class TextureBank {

    public static Texture GRASS_TEXTURE = loadTexture("png", "grass.png");
    public static Texture DIRT_TEXTURE = loadTexture("png", "dirt.png");
    public static Texture COBBLE_TEXTURE = loadTexture("png", "cobble.png");
    public static Texture DIAMOND_ORE_TEXTURE = loadTexture("png", "diamond_ore.png");
    public static Texture LOG_TOP = loadTexture("png", "logtop.png");
    public static Texture LOG_SIDE = loadTexture("png", "logside.png");
    public static Texture PLANKS_TEXTURE = loadTexture("png","planks.png");
    public static Texture SHADOW = loadTexture("png", "shadow.png");
    public static Texture GLASS = loadTexture("png","glass.png");
    public static Texture SAND = loadTexture("png","sand.png");
    public static Texture GRAVEL = loadTexture("png","gravel.png");
    public static Texture LEAF = loadTexture("png","leaf.png");
    public static Texture PUMPKIN_SIDE = loadTexture("png","pumpkin_side.png");
    public static Texture PUMPKIN_TOP = loadTexture("png","pumpkin_top.png");
    public static Texture CHEST_SIDE = loadTexture("png","chest_side.png");
    public static Texture FURNACE_FRONT = loadTexture("png","furnace_front.png");
    public static Texture FURNACE_TOP = loadTexture("png","furnace_top.png");
    public static Texture WHEAT = loadTexture("png","wheat.png");
    public static Texture EMPTY = loadTexture("png","empty.png");
    public static Texture WORKBENCH_SIDE = loadTexture("png","workbench_side.png");
    public static Texture WORKBENCH_TOP = loadTexture("png","workbench_top.png");
    public static Texture STONE = loadTexture("png","stone.png");


    public static Texture loadTexture(String format, String path) {
        try {

            Texture texture = TextureLoader.getTexture(format, ResourceLoader.getResourceAsStream("src/main/resources/"+path));

            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);

            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

            return texture;


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


}
