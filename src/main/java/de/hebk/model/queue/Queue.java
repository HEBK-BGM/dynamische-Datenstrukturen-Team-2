package de.hebk.model.queue;

import de.hebk.model.node.Node;

/**
 * Objekte der Klasse Queue (Warteschlange) verwalten beliebige Objekte nach dem
 * First-In-First-Out-Prinzip, d.h., das zuerst abgelegte Objekt wird als erstes wieder
 * entnommen.
 * @param <T>
 */
public class Queue<T> {
    private Node<T> first;

    /**
     * Die Anfrage liefert den Wert true, wenn die Schlange keine
     * Objekte enthält, sonst liefert sie den Wert false.
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Das Objekt pObject wird an die Schlange angehängt. Falls
     * pObject gleich null ist, bleibt die Schlange unverändert
     */
    public void enqueue(T pObject){
        if (first == null) {
            first = new Node<T>(pObject);
            return;
        }

        Node<T> tmp = first;
        while (tmp.getNext() != null) {
            tmp = tmp.getNext();
        }
        tmp.setNext(new Node<T>(pObject));
    }

    /**
     * Das erste Objekt wird aus der Schlange entfernt. Falls die
     * Schlange leer ist, wird sie nicht verändert.
     */
    public T dequeue(){
        if (!isEmpty()){
            Node<T> tmp = first;
            first = first.getNext();
            return tmp.getContext();
        }
        return  null;
    }

    /**
     * Die Anfrage liefert das erste Objekt der Schlange. Die
     * Schlange bleibt unverändert. Falls die Schlange leer ist, wird
     * null zurückgegeben.
     */
    public T front(){
        if(!isEmpty()){
            return first.getContext();
        }
        return  null;
    }
}