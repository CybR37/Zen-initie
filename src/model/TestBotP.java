package model;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestBotP {
    private BotP bot;
    private ArrayList<Pawn> myPawn;
    private ArrayList<Pawn> allPawn;

    @Before
    public void setup(){
        this.myPawn = new ArrayList<Pawn>();
        this.allPawn = new ArrayList<Pawn>();

        this.myPawn.add(new Pawn(0, 0, PawnType.WHITE));
        this.allPawn.add(new Pawn(0, 0, PawnType.WHITE));
        this.bot = new BotP(UIMode.TEXT, "test", this.myPawn, this.allPawn, 11, 11);
    }

    @Test
    public void testConstructor(){
        assertEquals(UIMode.TEXT, this.bot.ui);
        assertEquals(11, this.bot.width);
        assertEquals(11, this.bot.height);
        assertEquals(this.myPawn, this.bot.myPawns);
        assertEquals(this.allPawn, this.bot.pawnList);
    }

    @Test
    public void testSetUIAvecModesExistants(){
        this.bot.setUI(UIMode.GRAPH);
        assertEquals(UIMode.GRAPH, this.bot.ui);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetUISansModesExistants(){
        this.bot.setUI(UIMode.valueOf("GRAT"));
        assertEquals(UIMode.TEXT, this.bot.ui);
    }

    @Test
    public void testIsAllConnectedOnePawn(){
        assertTrue(this.bot.isAllConnected());
    }

    @Test
    public void testIsAllConnectedNoPawn(){
        this.myPawn.remove(0);
        assertTrue(this.bot.isAllConnected());
    }

    @Test
    public void testIsAllConnectedTwoPawnValid(){
        this.myPawn.add(new Pawn(1, 0, PawnType.WHITE));
        assertTrue(this.bot.isAllConnected());
    }

    @Test
    public void testIsAllConnectedTwoPawnNoValid(){
        this.myPawn.add(new Pawn(2, 0, PawnType.WHITE));
        assertFalse(this.bot.isAllConnected());
    }
}