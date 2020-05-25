package model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class TestMovement {
    private Movement mvt;
    private ArrayList<Pawn> array;
    @Before
    public void setup(){
        Pawn p = new Pawn(3, 1, PawnType.BLACK);
        Pawn p2 = new Pawn(0, 1, PawnType.WHITE);
        this.array = new ArrayList<Pawn>();
        this.array.add(p);
        this.array.add(p2);
        this.mvt = new Movement(p, 7, 1, this.array, 11, 11);
    }

    @Test
    public void testConstructor(){
        Pawn p = new Pawn(3, 1, PawnType.BLACK);
        Movement move = new Movement(p, 7, 1, this.array, 11, 11);
        assertEquals(this.mvt.getClass(), move.getClass());
        assertEquals(3, move.getOX());
        assertEquals(1, move.getOY());
        assertEquals(7, move.getNX());
        assertEquals(1, move.getNY());
    }

    @Test
    public void testGetDir(){
        assertEquals(Direction.RIGHT, this.mvt.getDir());

        Pawn p = new Pawn(7, 9, PawnType.BLACK);
        Movement move = new Movement(p, 7, 5, this.array, 11, 11);
        assertEquals(Direction.UP, move.getDir());
    }

    @Test
    public void testComputeDelta(){
        assertEquals(4, this.mvt.computeDelta());

        Pawn p = new Pawn(7, 6, PawnType.BLACK);
        Movement move = new Movement(p, 7, 5, this.array, 11, 11);
        assertEquals(1, move.computeDelta());
    }

    @Test
    public void testIsValidWithTwoPawns(){
        assertFalse(this.mvt.isValid());
    }

    @Test
    public void testIsValidWithOnePawn(){
        ArrayList<Pawn> array2 = new ArrayList<Pawn>();
        Pawn p = new Pawn(7, 6, PawnType.BLACK);
        array2.add(p);
        Movement move = new Movement(p, 7, 5, array2, 11, 11);
        assertTrue(move.isValid());
    }
}