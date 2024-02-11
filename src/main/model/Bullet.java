package model;

/* A bullet that collides with WordBlocks. When it touches the WordBlocks,
*  both bullet and block disappear and score is increased by 1. */

public class Bullet {
    private int x;
    private int y;

    // EFFECTS: creates a new Bullet object with coordinates x, y
    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // MODIFIES: this
    // EFFECTS: moves the bullet every clock tick
    public void move() {

    }

    // getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
