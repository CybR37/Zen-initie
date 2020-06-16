package model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestPawn {
    private Pawn pawn;
    @Before
    public void setup(){
        this.pawn = new Pawn(0, 4, PawnType.BLACK);
    }

    @Test
    public void testConstructorWithValidCoordinates(){
        Pawn p = new Pawn(5, 9, PawnType.ZEN);
        assertEquals(this.pawn.getClass(), p.getClass());
        assertEquals(5, p.getX());
        assertEquals(9, p.getY());
        assertEquals(PawnType.ZEN, p.getType());
    }

    @Test
    public void testConstructorWithInvalidCoordinates(){
        Pawn p = new Pawn(-3, 4, PawnType.ZEN);
        assertEquals(this.pawn.getClass(), p.getClass());
        assertEquals(0, p.getX());
        assertEquals(0, p.getY());
        assertNull(p.getType());
    }

    @Test
    public void testIsAtGoodCoordinates(){
        assertTrue(this.pawn.isAt(0, 4));
    }

    @Test
    public void testIsAtValidCoordinates(){
        assertFalse(this.pawn.isAt(0, 0));
    }

    @Test
    public void testIsAtInvalidCoordinates(){
        assertFalse(this.pawn.isAt(-7, 1));
    }

    @Test
    public void testSetXValidCoords(){
        this.pawn.setX(3);
        assertEquals(3, this.pawn.getX());
    }

    @Test
    public void testSetXWrongCoords(){
        this.pawn.setX(-42);
        assertEquals(0, this.pawn.getX());
    }

    @Test
    public void testSetYValidCoords(){
        this.pawn.setY(9);
        assertEquals(9, this.pawn.getY());
    }

    @Test
    public void testSetYWrongCoords(){
        this.pawn.setY(-3);
        assertEquals(4, this.pawn.getY());
    }
}