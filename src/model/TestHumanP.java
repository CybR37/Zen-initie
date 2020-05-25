package model;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestHumanP {
    private HumanP human;
    private ArrayList<Pawn> myPawn;
    private ArrayList<Pawn> allPawn;

    @Before
    public void setup(){
        this.myPawn = new ArrayList<Pawn>();
        this.allPawn = new ArrayList<Pawn>();

        this.myPawn.add(new Pawn(0, 0, PawnType.WHITE));
        this.allPawn.add(new Pawn(0, 0, PawnType.WHITE));
        this.human = new HumanP(UIMode.TEXT, this.myPawn, this.allPawn, 11, 11);
    }

    @Test
    public void testConstructor(){
        assertEquals(UIMode.TEXT, this.human.ui);
        assertEquals(11, this.human.width);
        assertEquals(11, this.human.height);
        assertEquals(this.myPawn, this.human.myPawns);
        assertEquals(this.allPawn, this.human.pawnList);
    }

    @Test
    public void testSetUIAvecModesExistants(){
        this.human.setUI(UIMode.GRAPH);
        assertEquals(UIMode.GRAPH, this.human.ui);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetUISansModesExistants(){
        this.human.setUI(UIMode.valueOf("GRAT"));
        assertEquals(UIMode.GRAPH, this.human.ui);
    }

    @Test
    public void testIsAllConnectedOnePawn(){
        assertTrue(this.human.isAllConnected());
    }

    @Test
    public void testIsAllConnectedNoPawn(){
        this.myPawn.remove(0);
        assertTrue(this.human.isAllConnected());
    }

    @Test
    public void testIsAllConnectedTwoPawnValid(){
        this.myPawn.add(new Pawn(1, 0, PawnType.WHITE));
        assertTrue(this.human.isAllConnected());
    }

    @Test
    public void testIsAllConnectedTwoPawnNoValid(){
        this.myPawn.add(new Pawn(2, 0, PawnType.WHITE));
        assertFalse(this.human.isAllConnected());
    }

    @Test
    public void testRemoveOutPawnsOnePawn(){
        this.myPawn.get(0).setState(true);
        assertEquals(1, this.human.myPawns.size());
        this.human.removeOutPawns();
        assertEquals(0, this.human.myPawns.size());
    }

    @Test
    public void testRemoveOutPawnsTwoPawnsRemoveOne(){
        this.myPawn.get(0).setState(true);
        this.myPawn.add(new Pawn(0, 0, PawnType.WHITE));
        assertEquals(2, this.human.myPawns.size());
        this.human.removeOutPawns();
        assertEquals(1, this.human.myPawns.size());
    }

    @Test
    public void testRemoveOutPawnsTwoPawnsRemoveTwo(){
        this.myPawn.add(new Pawn(0, 0, PawnType.WHITE));
        this.myPawn.get(0).setState(true);
        this.myPawn.get(1).setState(true);
        assertEquals(2, this.human.myPawns.size());
        this.human.removeOutPawns();
        assertEquals(0, this.human.myPawns.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOutPawnsNoPawn(){
        this.myPawn.get(0).setState(true);
        assertEquals(1, this.human.myPawns.size());
        this.human.removeOutPawns();
        assertEquals(0, this.human.myPawns.size());
    }
}