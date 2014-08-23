package nu.sebka.craftsource.blocks;

import org.newdawn.slick.opengl.Texture;

/**
 * Created by brandon on 8/23/14.
 */
public enum BlockType {

    AIR(null, null, null, null, null, null);

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
