 
/* Maria L Martinez - mlmartinez85@gmail.com
 * CS 4311 - Week 2b Assignment
 * Winter 2015
 * 01/21/2015
 *
 * Queue.java
 * this is a queue implementation such that First element in is First element out
 */

package cs4311.queue;

public class Queue {

    Node head = null;									
    Node tail = null;

    public static void main(String[] args) {
        Queue q = new Queue();                                          // 1
        q.enqueue(new Integer(5));					// 2 & 3
        q.enqueue(new Integer(1));					// 4 & 5
        q.enqueue(new Integer(7));					// 6 & 7

        while (!q.isEmpty())
                System.out.println(q.dequeue());			// 8
    }
	
    public Queue() {

    }
	
    public boolean isEmpty() {
        return (head == null);
    }
	
    public void enqueue(Object obj) {
        Node tmp = new Node(obj);                                       // 3.1
        if (isEmpty()) {
            head = tmp;
        }
        else {
            tail.next = tmp;
        }
        tail = tmp;
    }
	
    public Object dequeue() {
        if (isEmpty()) {
            return null;
        }
        else {
            Object el = head.element;
            head = head.next;
            if (head == null)
                tail = null;
            return el;
        }
    }
}
