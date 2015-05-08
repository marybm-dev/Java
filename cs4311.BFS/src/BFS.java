
import java.sql.ResultSet;
import java.sql.SQLException;

public class BFS {
    
    public static void main(String[] args) {
        //Node vertex = new Node(args[0]);
        DataBase db = new DataBase("genDB");
        Queue q = new Queue();
        q.enqueue(args[0]);
	
        System.out.println(args[0]);
        
        while (!q.isEmpty()) {
            
            String currentParent = (String) q.dequeue();
            Node parent = new Node(currentParent);
            
            ResultSet rs = db.execute("select child from Letters where parent = '" + currentParent + "'");
            int rscount = 0;
            
            try {
                
                while (rs.next()) {
                    String currentChild = rs.getString("CHILD");
                    System.out.print(currentChild + " ");
                    q.enqueue(currentChild);
                    
                    if (!currentChild.isEmpty()) {
                        Node child = new Node(currentChild);
                        parent.addNeighboor(child);
                    }
                    rscount++;
                }                               
                parent.setDepth(rscount);
                rs.close();                                     
            } 
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
}
