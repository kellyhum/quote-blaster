package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BulletTest {
    private Bullet b;
    private Bullet b1;

    @BeforeEach
    public void setup() {
        b = new Bullet(3, 3, 0);
        b1 = new Bullet(3, 3, 2.0);
    }

    @Test
    public void moveTest() {
        b.move();
        assertEquals(-5, b.getY());
        assertEquals(3, b.getX());
        assertEquals(0, b.getRotation());
    }

    @Test
    public void moveRotationTest() {
        b1.move();
        assertEquals(10.0, b1.getY());
        assertEquals(0, b1.getX());
        assertEquals(2.0, b1.getRotation());
    }

    @Test
    public void setXYTest() {
        b.setY(4);
        assertEquals(4, b.getY());

        b.setX(7);
        assertEquals(7, b.getX());
    }
}
