package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BulletTest {
    private Bullet b;

    @BeforeEach
    public void setup() {
        b = new Bullet(3, 3);
    }

    @Test
    public void moveTest() {
        b.move();
        assertEquals(2, b.getY());
        assertEquals(3, b.getX());
    }
}
