package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class WordBlockTest {
    private WordBlock w;

    @BeforeEach
    public void setup() {
        w = new WordBlock("test", 1, 1);
    }

    @Test
    public void getWordTest() {
        assertEquals("test", w.getWord());
    }

    @Test
    public void getXYTest() {
        assertEquals(1, w.getY());
        assertEquals(1, w.getX());
    }

    @Test
    public void moveTest() {
        w.move();
        assertEquals(6, w.getY());
        assertEquals(1, w.getX());
    }

    @Test
    public void setWordTest() {
        w.setX(5);
        assertEquals(5, w.getX());

        w.setY(2);
        assertEquals(2, w.getY());
    }

    @Test
    public void getHitTest() {
        assertFalse(w.getHit());
    }

    @Test
    public void setHitTest() {
        assertFalse(w.getHit());
        w.setHit(true);
        assertTrue(w.getHit());
    }
}
