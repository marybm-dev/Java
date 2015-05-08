
/* Maria L Martinez - mlmartinez85@gmail.com
 * CS 4311 - Week 4 Assignment - Ex 5
 * Winter 2015
 * 01/29/2015
 *
 * Client.java
 * Application acquires/releases a database connection object from
 * the pool instead of constructing the object each time it is needed.
 *
 * Output:
 * S1
 * S2
 * S3
 * S4
 * S5
 */

import java.sql.*;
import java.util.Vector;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static void main(String[] args) {
        ConnectionPool pool = ConnectionPool.getInstance();                         // 1
        Connection conn = pool.acquire("SuppDB");                                   // 2
        
        try {
            Statement stmt = conn.createStatement();                                // 3
            stmt.execute("select * from S");                                        // 4
            ResultSet rs = stmt.getResultSet();                                     // 4'
            
            while (rs.next()) {
                System.out.println(rs.getString("S_NO"));                           // 5a & 5b
            }
            
            rs.close();                                                             // 6
            
        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            
        }        
    }
}


/* ConnectionPool.class
 * Singleton Implementation of ConnectionPool
 */

class ConnectionPool {
    // singleton contructs itself when class loaded detects first reference
    private static ConnectionPool instance = new ConnectionPool();
    
    // normal instance data
    private static final String driverName = "org.sqlite.JDBC";
    private String url = "jdbc:sqlite:";
    int n = 3;
    Hashtable<String, Vector<Connection>> hash = new Hashtable<String, Vector<Connection>>();
    
    /* ConnectionPool Constructor
     * private constructor since singleton constructs itself
     * do not want a default public constructor from the compiler
     */
    private ConnectionPool() {
        try {
            Class.forName(driverName);                                              // 0
            makeConnections("genDB");                                               // 1
            makeConnections("SuppDB");                                              // 2
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /* getInstance() method
     * @return Singleton instance of ConnectionPool
     */
    public static ConnectionPool getInstance() {
        return instance;
    }
    
    /* acquire() method
     * get connection for this DB, remove it from available pool
     * @param name of database
     * @return Singleton instance of ConnectionPool
     */
    public Connection acquire(String dbName) {
        Connection conn = null;
        Vector<Connection> v = hash.get(dbName);                       // 2.1
        if (v.size() > 0) {
            conn = v.elementAt(0);                                     // 2.2a
            v.removeElementAt(0);                                      // 2.2b
        }
        return conn;
    }
    
    /* release() method
     * releases this DB's connection, add it to available pool
     * @param name of database
     * @param connection that was in use
     */
    public void release(String dbName, Connection conn) {
        Vector<Connection> v = hash.get(dbName);                                    // 7.1
        v.addElement(conn);                                                         // 7.2
    }
    
    /* makeConnections() method
     * makes connection to database
     * @param name of database to connect to
     */
    private void makeConnections(String dbName) {
        Vector<Connection> v = new Vector<Connection>();                            // 1.1
        for (int i = 1 ; i < n ; i++) {
            try {
                v.addElement(DriverManager.getConnection(url + dbName));            // 1.2a
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        hash.put(dbName, v);                                                        // 1.2b
    }
}