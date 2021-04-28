public class Node
{
    private final Ausrede ausrede; // Datenbehälter

    private Node next;

    private Node previous;

    
    public Node(Ausrede ausrede) {
        this.ausrede = ausrede;
    }

    public Ausrede getAusrede() {
        return ausrede;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

	public Node getPrevious() {
		return previous;
	}

	public void setPrevious(Node previous) {
		this.previous = previous;
	}
}
