public class DoubleLinkedList
{
    private Node first;

    private Node last;

    private Node current;

    /**
     * Einfuegen einer neuen Ausrede
     * @param a Ausrede
     */
    public void add(Ausrede a) {
        Node n = new Node(a);
        if (last == null) {     // Liste ist noch leer
            first = n;
            last = n;
        }
        else {                  // Liste enth�lt bereits Elemente
            last.setNext(n);
            n.setPrevious(last);
            last = n;
        }
    }

    /**
     * Internen Zeiger fuer next() zuruecksetzen
     */
    public void reset() {
        current = first;
    }

    /**
     * analog zur Funktion reset()
     */
    public void resetToLast() {
        current = last;
    }

    /**
     * Liefert erste Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node getFirst() {
        return first;
    }

    /**
     * Liefert letzte Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node getLast() {
        return last;
    }

    /**
     * Gibt aktuelle Ausrede zurueck und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     * @return Ausrede|null
     */
    public Ausrede next() {
        if (current == null) {
            return null;
        }
        Ausrede a = current.getAusrede();
        current = current.getNext();
        return a;
    }

    /**
     * analog zur Funktion next()
     * @return Ausrede|null
     */
    public Ausrede previous() {
        if (current == null) {
            return null;
        }
        Ausrede a = current.getAusrede();
        current = current.getPrevious();
        return a;
    }

    /**
     * Current-Pointer auf naechste Ausrede setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    public void moveNext() {
        if (current != null)
            current = current.getNext();
    }

    /**
     * Analog zur Funktion moveNext()
     */
    public void movePrevious() {
        if (current != null)
            current = current.getPrevious();
    }

    /**
     * Retourniert aktuelle (current) Ausrede, ohne Zeiger zu aendern
     * @return Ausrede
     * @throws CurrentNotSetException
     */
    public Ausrede getCurrent() throws CurrentNotSetException {
        if (current == null) {
            throw new CurrentNotSetException();
        }
        return current.getAusrede();
    }

    /**
     * Gibt Ausrede an bestimmter Position zurueck
     * @param pos Position, Nummerierung startet mit 1
     * @return Ausrede|null
     */
    public Ausrede get(int pos) {
        Node n = first;
        int cnt = 1;
        while (cnt != pos && n != null) {
            n = n.getNext();
            cnt++;
        }
        return (n != null) ? n.getAusrede() : null;
    }

    /**
     * Entfernen des Elements an der angegebenen Position
     * @param pos
     */
    public void remove(int pos) {
        Node n = first;
        int cnt = 1;
        while (cnt != pos && n != null) {   // Element suchen
            n = n.getNext();
            cnt++;
        }
        if (n == null) {    // keine Node an dieser Position
            return;
        }
        removeNode(n);
        if (current == n) {
            current = null;
        }
    }

    /**
     * Entfernt das aktuelle Element.
     * Als neues aktuelles Element wird der Nachfolger gesetzt oder
     * (falls kein Nachfolger) das vorhergehende Element
     * @throws CurrentNotSetException
     */
    public void removeCurrent() throws CurrentNotSetException {
        if (current == null) {
            throw new CurrentNotSetException();
        }
        removeNode(current);
        if (current.getNext() != null) {
            current = current.getNext();
        }
        else {
            current = current.getPrevious();
        }
    }

    /**
     * Hilfsfunktion, die von remove() und removeCurrent() verwendet wird
     * @param n Zu loeschende Node
     */
    private void removeNode(Node n) {
        if (n == first) {
            first = n.getNext();
            if (first != null) {
                first.setPrevious(null);
            }
        }
        else if (n == last) {
            last = n.getPrevious();
            if (last != null) {
                last.setNext(null);
            }
        }
        else {
            n.getPrevious().setNext(n.getNext());
            n.getNext().setPrevious(n.getPrevious());
        }
    }


    /**
     * Die Methode fuegt die Uebergebene Ausrede nach der aktuellen (current) ein
     * und setzt dann die neu eingefuegte Ausrede als aktuelle (current) Ausrede.
     * @throws CurrentNotSetException
     */
    public void insertAfterCurrentAndMove(Ausrede a) throws CurrentNotSetException {
        Node n = new Node(a);
        if (current == null) {
            throw new CurrentNotSetException();
        }
        else if (current == last) {
            last.setNext(n);
            n.setPrevious(last);
            last = n;
        }
        else {
            n.setNext(current.getNext());
            n.setPrevious(current);
            current.getNext().setPrevious(n);
            current.setNext(n);
        }
        current = n;
    }

}
