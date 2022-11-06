package de.hebk.model.stack;


import de.hebk.model.node.Node;

/**
 *
 * @param <T>
 */
public class Stack<T> {
    private Node<T> first;

    /**
     * Die Anfrage liefert den Wert true, wenn der Stapel keine
     * Objekte enthält, sonst liefert sie den Wert false.
     */
    public boolean isEmpty(){
        //TODO fill
        return true;
    }

    /**
     * Das Objekt pObject wird oben auf den Stapel gelegt. Falls
     * pObject gleich null ist, bleibt der Stapel unverändert
     */
    public void push(T pObjekt){
        //TODO fill
    }

    /**
     * Das zuletzt eingefügte Objekt wird von dem Stapel entfernt.
     * Falls der Stapel leer ist, bleibt er unverändert.
     */
    public void pop(){
        //TODO fill
    }

    /**
     * Die Anfrage liefert das oberste Stapelobjekt. Der Stapel bleibt
     * unverändert. Falls der Stapel leer ist, wird null zurückgegeben.
     */
    public T top(){
        //TODO fill
        return this.first.getContext();
    }
}
