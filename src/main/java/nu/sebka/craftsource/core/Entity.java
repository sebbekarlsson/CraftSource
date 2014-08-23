package nu.sebka.craftsource.core;

import nu.sebka.craftsource.World;

/**
 * Created by brandon on 8/23/14.
 */
public abstract class Entity extends Location implements Drawable {
    public Entity(World world, float x, float y, float z) {
        super(world, x, y, z);
    }

    public Entity(World world, float x, float y, float z, float yaw, float pitch) {
        super(world, x, y, z, yaw, pitch);
    }
}
