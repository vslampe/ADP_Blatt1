package com.company;

public interface IList <T>{
    //TO-DO Kommentare
    int getAnzahlElemente();
    void insertAt(T t, int pos);
    void deleteAt(int pos);
    T retrieve(int pos);
    void clear();
}
