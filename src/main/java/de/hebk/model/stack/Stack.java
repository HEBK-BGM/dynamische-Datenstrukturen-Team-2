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
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Das Objekt pObject wird oben auf den Stapel gelegt. Falls
     * pObject gleich null ist, bleibt der Stapel unverändert
     */
    public void push(T pObjekt) {
        if (pObjekt == null) {
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
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        Node<T> tmp = first;
        first = first.getNext();

        return tmp.getContext();
    }

    /**
     * Die Anfrage liefert das oberste Stapelobjekt. Der Stapel bleibt
     * unverändert. Falls der Stapel leer ist, wird null zurückgegeben.
     */
    public T top() {
        if (isEmpty()) {
            return null;
        }
        return first.getContext();
    }

    /**
     * Returns the size of the stack
     * @return  Size of the stack
     */
    public int size() {
        int counter = 1;
        if (first == null) {
            return 0;
        }

        Node tmp = first;
        while (tmp.getNext() != null) {
            counter++;
            tmp = tmp.getNext();
        }

        return counter;
    }
}
