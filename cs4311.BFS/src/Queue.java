

public class Queue {

    Node head = null;									
    Node tail = null;
	
    public Queue() {

    }
	
    public boolean isEmpty() {
        return (head == null);
    }
	
    public void enqueue(Object obj) {
        Node tmp = new Node(obj);                                       
        if (isEmpty())
            head = tmp;
        else
            tail.next = tmp;
        tail = tmp;
    }
	
    public Object dequeue() {
        if (isEmpty())
            return null;
        else {
            Object el = head.element;
            head = head.next;
            if (head == null)
                tail = null;
            return el;
        }
    }
}
