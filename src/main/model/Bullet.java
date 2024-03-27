package model;

/* A bullet that moves up the screen and has x and y coordinates. */

public class Bullet {
    private int x1;
    private int y1;
    private double rotation;

    // EFFECTS: creates a new Bullet object with coordinates x, y
    public Bullet(int x, int y, double rotation) {
        x1 = x;
        y1 = y;
        this.rotation = rotation;
    }

    // MODIFIES: this
    // EFFECTS: moves the bullet every clock tick
    public void move() {
        if (this.rotation != 0) {
            y1 += 8 * Math.sin(rotation);
            x1 += 8 * Math.cos(rotation);
        } else {
            y1 -= 8; // minus 1 b/c of the screen position (++ is down, -- is up)
        }
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

    public double getRotation() {
        return rotation;
    }
}
