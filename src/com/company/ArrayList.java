package com.company;

import java.security.InvalidParameterException;

public class ArrayList<T> implements IList<T> {

    private Object elemente[];
    private int anzahlElemente;
    private int maxAnzahlElemente;
    private static final  int KAPAZITAET = 5;
    public ArrayList(){
            elemente = new Object[KAPAZITAET];
            anzahlElemente=0;
            maxAnzahlElemente= KAPAZITAET;
    }
    private boolean istGueltigeEinfPos(int pos){
        return (pos >= 0 )&& (pos<maxAnzahlElemente);
    }
    @Override
    public int getAnzahlElemente() {
        return anzahlElemente;
    }

    /**
     * Fügt ein neues Element an Einfuegeposition ein
     * @param t Generischer Parameter != null
     * @param pos Muss zwischen 0,.., Kapazität liegen ansonsten wird eine InvalidParameterException geworfen
     */
    @Override
    public void insertAt(T t, int pos) {
        if(!istGueltigeEinfPos(pos)){
            throw new InvalidParameterException("Ungültige Einfügeposition oder Kapazität überschritten!");
        }
        //Wenn es nicht das letzte Element ist, dann verschiebe alle Elemente um eins
        if(!(anzahlElemente == pos)){
            Object last = null;
            for(int i = pos; i< elemente.length; i++){
                Object aktElm = elemente[i];
                elemente[i] = last;
                last= aktElm;
            }
        }
        elemente[pos]= t;
        anzahlElemente++;
    }

    /**
     * Entfernt ein Element aus der Liste. Wirft InvalidParameterException, falls es sich um eine leere Liste handelt.
     * @param pos Muss zwischen 0 und
     */
    @Override
    public void deleteAt(int pos) {

        if(anzahlElemente == 0){
            throw new InvalidParameterException("Es kann kein Element aus einer leeren Liste entfernt werden");
        }
        if(!istGueltigeEinfPos(pos)){
            throw new InvalidParameterException("Ungültige Löscheposition oder Kapazität überschritten!");
        }
        if(!(anzahlElemente == pos)){
            Object last = null;
            for(int i = anzahlElemente-1; i>=pos; i--){
                Object aktElm = elemente[i];
                elemente[i]= last;
                last = aktElm;
            }
        }
        anzahlElemente--;

    }

    /**
     * Gibt Element an bestimmter Position zurück.
     * @param pos Muss zwischen 0,.., Kapazität liegen ansonsten wird eine InvalidParameterException geworfen
     * @return
     */
    @SuppressWarnings("unckecked")
    @Override
    public T retrieve(int pos) {

        if(!istGueltigeEinfPos(pos)){
            throw new InvalidParameterException("Ungültige Einfügeposition oder Kapazität überschritten!");
        }

        return (T) elemente[pos];
    }

    /**
     * Löscht alle Elemente einer Liste
     */
    @Override
    public void clear() {
        for(int i =0 ; i < elemente.length; i++){
            elemente[i] = null;
        }
        anzahlElemente=0;
    }
}
