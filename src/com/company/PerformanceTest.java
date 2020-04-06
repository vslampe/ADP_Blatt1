package com.company;


import java.nio.charset.Charset;
import java.util.Random;

public class PerformanceTest {
    enum Options {
        ANFANG, ENDE, ZUFALL
    }
    private String generiereZufaelligenStr() {

        byte[] array = new byte[7];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
    /**
     * Fuegt 10 000 Elemente in die Liste ein.
     * @param iList Darf nicht null sein
     * @param options Darf nicht null sein
     */
    private void fuegeZehntausendElemEin(IList<String> iList, Options options){
        int pos = 0;
        if(options == Options.ENDE){
            pos= 1;
        }
        else if(options==Options.ZUFALL){
            for(int i = 0; i< 10000; i++){
               iList.insertAt(generiereZufaelligenStr(),generiereZufaelligePos(iList.getAnzahlElemente()));
            }
        }
        else {
        for(int i = 0; i < 10000; i++){
            iList.insertAt(generiereZufaelligenStr(),pos);

        }
        }
    }

    /**
     * generiereZufaelligePos()
      * @param anzahlElemente
     * @return
     */
    private int generiereZufaelligePos(int anzahlElemente) {

        Random ran = new Random();
        int res  = 0 + ran.nextInt((anzahlElemente-1)-0+1);
        return res;
    }
}
