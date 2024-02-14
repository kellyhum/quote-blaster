package model;

/* A bullet that collides with WordBlocks. When it touches the WordBlocks,
*  both bullet and block disappear and score is increased by 1. */

public class Bullet {
    private int x1;
    private int y1;

    // EFFECTS: creates a new Bullet object with coordinates x, y
    public Bullet(int x, int y) {
        x1 = x;
        y1 = y;
    }

    // MODIFIES: this
    // EFFECTS: moves the bullet every clock tick
    public void move() {
        y1--; // minus 1 b/c of the screen position (++ is down, -- is up)
    }

    // getters and setters
    public int getX() {
        return x1;
    }

    public int getY() {
        return y1;
    }

    public void setY(int y) {
        this.y1 = y;
    }

    public void setX(int x) {
        this.x1 = x;
    }
}
