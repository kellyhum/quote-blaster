package model;

/* A bullet that moves up the screen and has x and y coordinates. */

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
        y1 -= 8; // minus 1 b/c of the screen position (++ is down, -- is up)
    }

    // MODIFIES: this
    // EFFECTS: changes the y value to the parameter
    public void setY(int y) {
        this.y1 = y;
    }

    // MODIFIES: this
    // EFFECTS: changes the x value to the parameter
    public void setX(int x) {
        this.x1 = x;
    }

    // getters
    public int getX() {
        return x1;
    }

    public int getY() {
        return y1;
    }
}
