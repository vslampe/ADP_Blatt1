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
     *                @return Gibt die vergangene Zeit in Nanosekunden zurück
     */
    private long fuegeZehntausendElemEin(IList<String> iList, Options options){
        final long timeStart = System.nanoTime();
        int pos = 0;
        if(options == Options.ENDE){
            pos= 1;
        }
        else if(options==Options.ZUFALL){
            //Performance Gruende
            for(int i = 0; i< 10000; i++){
               iList.insertAt(generiereZufaelligenStr(),generiereZufaelligePos(iList.getAnzahlElemente()));
            }
        }
        else {
        for(int i = 0; i < 10000; i++){
            iList.insertAt(generiereZufaelligenStr(),pos);

        }
        }
        return System.nanoTime()-timeStart;

    }

    /**
     * Löscht 1000 Elemente in der Liste
     * @param iList Darf nicht null sein
     * @param options Darf nicht null sein
     * @return Gibt die vergangene Zeit in Nanosekunden zurück
     */
    private long loescheTausendElem(IList<String> iList, Options options){
        final long timeStart = System.nanoTime();
        int pos = 0;
        if(options == Options.ENDE){
            pos= 1;
        }
        else if(options==Options.ZUFALL){
            //Performance Gruende
            for(int i = 0; i< 10000; i++){
                iList.deleteAt(generiereZufaelligePos(iList.getAnzahlElemente()));
            }
        }
        else {
            for(int i = 0; i < 10000; i++){
                iList.deleteAt(pos);
            }
        }
        return System.nanoTime()-timeStart;
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
    private void printTest(String msg, long nanoTime) {
        System.out.println(msg+ "Der Test dauerte "+ nanoTime +"ns");

    }
    public void performanceTest(IList<String> list){
        long time = 0;
        for(int i= 0; i< 10;i++){
            time += fuegeZehntausendElemEin(list,Options.ANFANG);
            time += fuegeZehntausendElemEin(list, Options.ZUFALL);
            time += fuegeZehntausendElemEin(list, Options.ENDE);
        }


    }
}
