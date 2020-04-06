package com.company;

class Knoten<T> {
    T element;
    Knoten<T> nachfolger;
    Knoten<T> vorfahrer;

    /**
     * Konstruktor.
     *
     * @param element Knote-Datei
     */
    Knoten(T element) {
        this(null, element, null);
    }

    /**
     * Constructor.
     *
     * @param element Knote-Datei
     * @param nachfolger nachfolger-Knote
     * @param vorfahrer previous-Knote
     */
    Knoten(Knoten<T> vorfahrer, T element, Knoten<T> nachfolger) {
        this.element = element;
        this.nachfolger = nachfolger;
        this.vorfahrer = vorfahrer;
    }
}

public class LinkedList<T> implements IList<T>{
    private Knoten<T> kopf;
    private int anzahl;

    /**
     * Erzeugt eine leere Liste
     */
    public LinkedList() {
        kopf = null;
        anzahl = 0;
    }

    private boolean isEmpty() {
        return anzahl == 0;
    }

    /**
     *
     * @return int : anzahl Elemente
     */
    @Override
    public int getAnzahlElemente() {
        return anzahl;
    }


    /**
     * Knote auf die gewünschte zugreifende Position hinzufügen
     * @param var1 Knote-Datei
     * @param var2 die gewünschte zugreifende Position
     */
    @Override
    public void insertAt(T var1, int var2) {
        // Prüft die eingegebene Index
        if(var2 < 0 || var2 > getAnzahlElemente()) throw new IllegalArgumentException("Falsche Index");

        //Wenn es die erste Knote ist
        if(var2 == 0) {
            if (isEmpty())
                kopf = new Knoten<>(var1);
            else {
                Knoten<T> zeiger = kopf;
                kopf = new Knoten<>(null, var1, zeiger);
                kopf.nachfolger.vorfahrer = kopf;
            }
            anzahl++;
        }

        //Wenn es die letzte Knote ist
        else if(var2==getAnzahlElemente()){
            if (isEmpty())
                kopf = new Knoten<>(var1);
            else {
                Knoten<T> zeiger = kopf;
                // Traverse till end of list
                while (zeiger.nachfolger != null) {
                    zeiger = zeiger.nachfolger;
                }
                zeiger.nachfolger = new Knoten<T>(zeiger, var1, null);
            }
            anzahl++;
        }

        //Auf gewünschte Position
        else {
            Knoten<T> zeiger = kopf;
            for(int i = 0; i < var2; i++){
                zeiger = zeiger.nachfolger;
            }
            Knoten<T> neueKnote = new Knoten<T>(zeiger.vorfahrer, var1, zeiger);
            if (zeiger.vorfahrer != null)
                zeiger.vorfahrer.nachfolger = neueKnote;
            else
                kopf = neueKnote;
            zeiger.vorfahrer = neueKnote;
            anzahl++;

        }

    }


    /**
     * Knote aus der gewünschten zugreifenden Position entfernt.
     * @param var2 die gewünschte zugreifende Position
     */
    @Override
    public void deleteAt(int var2) {
        // Prüft die eingegebene Index
        if(var2 < 0 || var2 > getAnzahlElemente()) throw new IllegalArgumentException("Falsche Index");

        //Wenn es die erste Knote ist
        if(var2 == 0) {
            kopf = kopf.nachfolger;
            anzahl--;
        }

        //Auf gewünschte Position
        else {
            Knoten<T> zeiger = kopf;
            for(int i = 0; i < var2; i++){
                zeiger = zeiger.nachfolger;
            }
            if (zeiger == null)
                throw new IllegalArgumentException("Falsche Index");
            if (zeiger.nachfolger != null)
                zeiger.nachfolger.vorfahrer = zeiger.vorfahrer;
            zeiger.vorfahrer.nachfolger = zeiger.nachfolger;
            anzahl--;
        }

    }

    /**
     * Gibt die ELement von einer Position zurück.
     * @param var2 die gewünschte zugreifende Position
     * @return T-Element
     */
    @Override
    public T retrieve(int var2) {
        // Prüft die eingegebene Index
        if(var2 < 0 || var2 > getAnzahlElemente()) throw new IllegalArgumentException("Falsche Index");

        //Wenn es die erste Knote ist
        if(var2 == 0) {
            return kopf.element;
        }
        //Wenn es die letzte Knote ist
        else if(var2==getAnzahlElemente()-1){
            Knoten<T> zeiger = kopf;
            while(zeiger.nachfolger!=null){
                zeiger = zeiger.nachfolger;
            }
            return zeiger.element;
        }
        //Auf gewünschte Position
        else {
            Knoten<T> zeiger = kopf;
            for(int i = 0; i < var2; i++){
                zeiger = zeiger.nachfolger;
            }
            if (zeiger == null)
                throw new IllegalArgumentException("Falsche Index");
            if (zeiger.nachfolger != null)
                return zeiger.element;
        }
        return null;
    }

    /**
     * die Liste entleert.
     */
    @Override
    public void clear() {
        kopf = null;
        anzahl = 0;
    }

    /**
     * Die Liste als String zurückgeben.
     * @return String-Referenz von der Liste
     */
    @Override
    public String toString() {
        Knoten<T> zeiger = kopf;
        StringBuilder builder = new StringBuilder("[");
        while (zeiger != null) {
            builder.append(zeiger.element).append(",");
            zeiger = zeiger.nachfolger;
        }
        builder.append("]");
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
