
import java.util.List;
import java.util.ArrayList;

/* Maria L Martinez - mlmartinez85@gmail.com
 * CS 4311 - Week 2a Assignment
 * Winter 2015
 * 01/14/2015
 */

public class Node {
    public Object element = new Object();
    public Node next;
    
    public boolean visited = false;
    public List<Node> neighboors = new ArrayList<>();
    public int depth;
    

    public Node(Object obj) {
        element = obj;
    }
    
    public void addNeighboor(Node n) {
        neighboors.add(n);
    }
    
    public void setVisited() {
        visited = true;
    }
    
    public void setDepth(int d) {
        depth = d;
    }
    
    public int getDepth() {
        return depth;
    }
    
    public List<Node> getNeighboors() {
        return neighboors;
    }
}