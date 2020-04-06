package com.company;

import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class ArrayListTest {

    IList<String> tl = new ArrayList<String>();
    @Test
    public void insertAt() {

        tl.insertAt("Hallo ",0);
        tl.insertAt("!", 1);
        tl.insertAt("Welt",1);
        String res = "";
        for(int i = 0; i<tl.getAnzahlElemente();i++){
            res += tl.retrieve(i);
        }
        assertEquals("Hallo Welt!",res);


    }

    @Test(expected = InvalidParameterException.class)
    public void deleteAtRange(){

        tl.insertAt("Hallo",0);
        tl.deleteAt(2);
    }
    @Test(expected = InvalidParameterException.class)
    public void deleteAtRangeNeg(){

        tl.insertAt("Hallo",0);
        tl.deleteAt(-1);
    }
    @Test
    public void deleteAt() {

        tl.insertAt("Hallo",0);
        tl.insertAt("Welt",0);
        tl.insertAt("!", 0);
        tl.deleteAt(1);
        //Test lösche in der Mitte
        assertEquals(2,tl.getAnzahlElemente());
        tl.deleteAt(0);
        tl.deleteAt(0);
        assertEquals(0,tl.getAnzahlElemente());

    }
    @Test(expected = InvalidParameterException.class)
    public void deleteAtNegative(){

        tl.deleteAt(0);
    }

    @Test
    public void clear() {
        tl.insertAt("Hallo",0);
        assertEquals(1, tl.getAnzahlElemente());
        tl.clear();
        assertEquals(0, tl.getAnzahlElemente());
    }
}