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
        return first == null;
    }

    /**
     * Das Objekt pObject wird oben auf den Stapel gelegt. Falls
     * pObject gleich null ist, bleibt der Stapel unverändert
     */
    public void push(T pObjekt){
        if (pObjekt == null) {
            return;
        }

        if (first == null) {
            first = new Node<T>(pObjekt);
            return;
        }

        Node<T> tmp = first;
        first = new Node<T>(pObjekt);
        first.setNext(tmp);
    }

    /**
     * Das zuletzt eingefügte Objekt wird von dem Stapel entfernt.
     * Falls der Stapel leer ist, bleibt er unverändert.
     */
    public T pop(){
        Node<T> tmp = first;
        if (first.getNext() != null) {
            first = tmp.getNext();
        }

        return tmp.getContext();
    }

    /**
     * Die Anfrage liefert das oberste Stapelobjekt. Der Stapel bleibt
     * unverändert. Falls der Stapel leer ist, wird null zurückgegeben.
     */
    public T top(){
        return this.first.getContext();
    }
}