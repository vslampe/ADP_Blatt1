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
     * generiereZufaelligePos()
     * @param anzahlElemente
     * @return
     */
    private int generiereZufaelligePos(int anzahlElemente) {
        Random ran = new Random();
        if(anzahlElemente==0){
            return 0;
        }
        return ran.nextInt(anzahlElemente);
    }

    /**
     * Fuegt 10 000 Elemente in die Liste ein.
     * @param iList Darf nicht null sein
     * @param options Darf nicht null sein
     *                @return Gibt die vergangene Zeit in Nanosekunden zurück
     */
    private long fuegeZehntausendElemEin(IList<String> iList, Options options){

        final long timeStart = System.nanoTime();

        if(options == Options.ENDE){
            for(int i = 0; i < 10000; i++){
                iList.insertAt(generiereZufaelligenStr(),iList.getAnzahlElemente()-1);
            }
        }
        else if(options==Options.ZUFALL){
            //Performance Gruende
            for(int i = 0; i< 10000; i++){
               iList.insertAt(generiereZufaelligenStr(),generiereZufaelligePos(iList.getAnzahlElemente()));
            }
        }
        else {
            for(int i = 0; i < 10000; i++){
                iList.insertAt(generiereZufaelligenStr(),0);
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
            for(int i = 0; i < 1000; i++){
                iList.deleteAt(iList.getAnzahlElemente()-1);
            }
        }
        else if(options==Options.ZUFALL){
            //Performance Gruende
            for(int i = 0; i< 1000; i++){
                iList.deleteAt(generiereZufaelligePos(iList.getAnzahlElemente()));
            }
        }
        else {
            for(int i = 0; i < 1000; i++){
                iList.deleteAt(pos);
            }
        }
        return System.nanoTime()-timeStart;
    }
    private void printTest(String msg, long nanoTime) {
        System.out.println(msg+ "Der Test dauerte "+ nanoTime +"ns");

    }
    public void performanceTestEins(IList<String> list){
        long time = 0;
        //Testreihe Nummer 1
        for(int i= 0; i< 10;i++){
            list.clear();
            time += fuegeZehntausendElemEin(list,Options.ANFANG);
            time += fuegeZehntausendElemEin(list, Options.ZUFALL);
            time += fuegeZehntausendElemEin(list, Options.ENDE);
        }
        printTest("Testreihe 1 (10 000 ELemente jeweils am Anfang, Zufaellig und am Ende einfuegen) : ",time/10);
    }
    public void performanceTestZwei(IList<String> list){
        long time = 0;
        //Testreihe Nummer 1
        for(int i= 0; i< 10;i++){
            list.clear();
            fuegeZehntausendElemEin(list,Options.ZUFALL);
            time += loescheTausendElem(list,Options.ANFANG);
            time += loescheTausendElem(list, Options.ZUFALL);
            time += loescheTausendElem(list, Options.ENDE);
        }
        printTest("Testreihe 1 (1 000 ELemente jeweils am Anfang, Zufaellig und am Ende löschen) : ",time/10);
    }
    public void starteAlleTests(){

        //ArrayList Tests
        System.out.println("-Array List-\n\n");
        IList<String> list = new ArrayList<>();
        performanceTestEins(list);
        performanceTestZwei(list);

        //ArrayList Tests
        System.out.println("-Linked List-\n\n");
        list = new LinkedList<>();
        performanceTestEins(list);
        performanceTestZwei(list);


    }

}
