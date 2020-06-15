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
        this.mvt = new Movement(p, this.array.get(0).getType(), 7, 1, this.array, 11, 11);
    }

    @Test
    public void testConstructor(){
        Pawn p = new Pawn(3, 1, PawnType.BLACK);
        Movement move = new Movement(p, this.array.get(0).getType(), 7, 1, this.array, 11, 11);
        assertEquals(this.mvt.getClass(), move.getClass());
        assertEquals(3, move.getOX());
        assertEquals(1, move.getOY());
        assertEquals(7, move.getNX());
        assertEquals(1, move.getNY());
    }

    @Test
    public void testGetDir(){
        assertEquals(Direction.E, this.mvt.getDir());

        Pawn p = new Pawn(7, 9, PawnType.BLACK);
        Movement move = new Movement(p, this.array.get(0).getType(), 7, 5, this.array, 11, 11);
        assertEquals(Direction.S, move.getDir());
    }

    @Test
    public void testComputeDelta(){
        assertEquals(4, this.mvt.computeDelta());

        Pawn p = new Pawn(7, 6, PawnType.BLACK);
        Movement move = new Movement(p, this.array.get(0).getType(), 7, 5, this.array, 11, 11);
        assertEquals(1, move.computeDelta());

        move = new Movement(p, this.array.get(0).getType(), 9, 5, this.array, 11, 11);
        assertEquals(0, move.computeDelta());

        move = new Movement(p, this.array.get(0).getType(), 9, 4, this.array, 11, 11);
        assertEquals(2, move.computeDelta());
    }

    @Test
    public void testIsValidWithTwoPawnsNotValids(){
        assertFalse(this.mvt.isValid());
    }

    @Test
    public void testIsValidWithOnePawn(){
        ArrayList<Pawn> array2 = new ArrayList<Pawn>();
        Pawn p = new Pawn(7, 6, PawnType.BLACK);
        array2.add(p);
        Movement move = new Movement(p, array2.get(0).getType(), 7, 5, array2, 11, 11);
        assertTrue(move.isValid());

        ArrayList<Pawn> array = new ArrayList<Pawn>();
        p = new Pawn(5, 4, PawnType.WHITE);
        array.add(p);
        move = new Movement(array.get(0), array.get(0).getType(), 4, 5, array, 11, 11);
        assertTrue(move.isValid());
    }

    @Test
    public void testIsValidWithFourPawn(){
        Pawn p = new Pawn(2, 1, PawnType.BLACK);
        this.array.add(p);
        Pawn p2 = new Pawn(1, 1, PawnType.BLACK);
        this.array.add(p2);
        Movement move = new Movement(this.array.get(0), this.array.get(0).getType(), 7, 1, this.array, 11, 11);
        assertTrue(move.isValid());
    }

    @Test
    public void testIsValidWithOnePawnOnAnotherAllyPawn(){
        Pawn p = new Pawn(7, 1, PawnType.BLACK);
        this.array.add(p);
        Pawn p2 = new Pawn(1, 1, PawnType.BLACK);
        this.array.add(p2);
        Movement move = new Movement(this.array.get(0), this.array.get(0).getType(), 7, 1, this.array, 11, 11);
        assertFalse(move.isValid());
    }

    @Test
    public void testIsValidWithOnePawnOnEnnemyPawn(){
        Pawn p = new Pawn(7, 1, PawnType.WHITE);
        this.array.add(p);
        Pawn p2 = new Pawn(1, 1, PawnType.BLACK);
        this.array.add(p2);
        Movement move = new Movement(this.array.get(0), this.array.get(0).getType(), 7, 1, this.array, 11, 11);
        assertTrue(move.isValid());
    }

    @Test
    public void testIsValidWithOnePawnOnZenPawn(){
        Pawn p = new Pawn(7, 1, PawnType.ZEN);
        this.array.add(p);
        Pawn p2 = new Pawn(1, 1, PawnType.BLACK);
        this.array.add(p2);
        Movement move = new Movement(this.array.get(0), this.array.get(0).getType(), 7, 1, this.array, 11, 11);
        assertTrue(move.isValid());
    }

    @Test
    public void testIsValidWithPawnBehindEnnemyPawn(){
        Pawn p = new Pawn(5, 1, PawnType.WHITE);
        this.array.add(p);
        Pawn p2 = new Pawn(1, 1, PawnType.BLACK);
        this.array.add(p2);
        Movement move = new Movement(this.array.get(0), this.array.get(0).getType(), 7, 1, this.array, 11, 11);
        assertFalse(move.isValid());

        ArrayList<Pawn> array = new ArrayList<Pawn>();
        p = new Pawn(5, 4, PawnType.WHITE);
        array.add(p);
        p2 = new Pawn(4, 3, PawnType.BLACK);
        array.add(p2);
        move = new Movement(array.get(0), array.get(0).getType(), 3, 2, array, 11, 11);
        assertFalse(move.isValid());
    }

    @Test
    public void testIsValidWithOnePawnBehindAnotherAllyPawn(){
        Pawn p = new Pawn(5, 1, PawnType.BLACK);
        this.array.add(p);
        Pawn p2 = new Pawn(1, 1, PawnType.BLACK);
        this.array.add(p2);
        Movement move = new Movement(this.array.get(0), this.array.get(0).getType(), 7, 1, this.array, 11, 11);
        assertTrue(move.isValid());
    }

    @Test
    public void testIsValidWithOnePawnBehindZenPawn(){
        Pawn p = new Pawn(5, 1, PawnType.ZEN);
        this.array.add(p);
        Pawn p2 = new Pawn(1, 1, PawnType.BLACK);
        this.array.add(p2);
        Movement move = new Movement(this.array.get(0), this.array.get(0).getType(), 7, 1, this.array, 11, 11);
        assertTrue(move.isValid());
    }

    @Test
    public void testIsValidWithZenPawnOnAllyPawn(){
        ArrayList<Pawn> array = new ArrayList<Pawn>();
        Pawn p = new Pawn(5, 1, PawnType.ZEN);
        array.add(p);
        Pawn p2 = new Pawn(2, 1, PawnType.BLACK);
        array.add(p2);
        Pawn p3 = new Pawn(4, 1, PawnType.BLACK);
        array.add(p3);
        Movement move = new Movement(array.get(0), array.get(1).getType(), 2, 1, array, 11, 11);
        assertFalse(move.isValid());
    }

    @Test
    public void testIsValidWithZenPawnOnEnnemyPawn(){
        ArrayList<Pawn> array = new ArrayList<Pawn>();
        Pawn p = new Pawn(5, 1, PawnType.ZEN);
        array.add(p);
        Pawn p2 = new Pawn(2, 1, PawnType.WHITE);
        array.add(p2);
        Pawn p3 = new Pawn(4, 1, PawnType.BLACK);
        array.add(p3);
        Movement move = new Movement(array.get(0), PawnType.BLACK, 2, 1, array, 11, 11);
        assertTrue(move.isValid());
    }
}