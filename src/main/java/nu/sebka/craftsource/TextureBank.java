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
