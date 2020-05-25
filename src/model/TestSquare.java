package model;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestSquare {
    @Test
    public void testConstructor(){
        Square sq = new Square(SymbolSquare.NONE);
        assertTrue(sq.isFree());
        assertEquals(SymbolSquare.NONE, sq.getType());

        Square sq2 = new Square(SymbolSquare.GEOMETRIC);
        assertTrue(sq2.isFree());
        assertEquals(SymbolSquare.GEOMETRIC, sq2.getType());

        Square sq3 = new Square(SymbolSquare.CHINESE);
        assertTrue(sq3.isFree());
        assertEquals(SymbolSquare.CHINESE, sq3.getType());
    }

    @Test
    public void testSetFree(){
        Square sq = new Square(SymbolSquare.NONE);
        assertTrue(sq.isFree());
        sq.setFree(false);
        assertFalse(sq.isFree());
    }
}