package nu.sebka.craftsource.core;

import nu.sebka.craftsource.World;

public class Location implements Cloneable {

    protected World world;
    protected float x, y, z, pitch, yaw;

    private Location() {
        this(null, 0, 0, 0);
    }

    public Location(final World world, final float x, final float y, final float z) {
        this(world, x, y, z, 0, 0);
    }

    public Location(final World world, final float x, final float y, final float z, final float yaw, final float pitch) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public String toString() {
        return "Location{" +
                "world=" + world +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", pitch=" + pitch +
                ", yaw=" + yaw +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (Float.compare(location.pitch, pitch) != 0) return false;
        if (Float.compare(location.x, x) != 0) return false;
        if (Float.compare(location.y, y) != 0) return false;
        if (Float.compare(location.yaw, yaw) != 0) return false;
        if (Float.compare(location.z, z) != 0) return false;
        if (!world.equals(location.world)) return false;

        return true;
    }

    @Override
    protected Location clone() throws CloneNotSupportedException {
        return new Location(world, x, y, z, pitch, yaw);
    }

    public Location getDirection() {
        Location vector = new Location();

        float rotX = this.getYaw();
        float rotY = this.getPitch();

        vector.setY((float) -Math.sin(Math.toRadians(rotY)));

        float h = (float) Math.cos(Math.toRadians(rotY));

        vector.setX((float) (-h * Math.sin(Math.toRadians(rotX))));
        vector.setZ((float) (h * Math.cos(Math.toRadians(rotX))));

        return vector;
    }

    public Location add(Location vec) {
        if (vec == null || vec.getWorld() != getWorld()) {
            throw new IllegalArgumentException("Cannot add Locations of differing worlds");
        }

        x += vec.x;
        y += vec.y;
        z += vec.z;
        return this;
    }

    public Location add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Location subtract(Location vec) {
        if (vec == null || vec.getWorld() != getWorld()) {
            throw new IllegalArgumentException("Cannot add Locations of differing worlds");
        }

        x -= vec.x;
        y -= vec.y;
        z -= vec.z;
        return this;
    }

    public Location subtract(float x, float y, float z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Location multiply(float m) {
        x *= m;
        y *= m;
        z *= m;
        return this;
    }

    public Location divide(float m) {
        x /= m;
        y /= m;
        z /= m;
        return this;
    }


    public Location zero() {
        x = 0;
        y = 0;
        z = 0;
        return this;
    }

}
