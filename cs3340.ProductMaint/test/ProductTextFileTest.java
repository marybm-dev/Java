
/* Maria L Martinez - mlmartinez85@gmail.com
 * CS 4311 - Week 2b Assignment
 * Winter 2015
 * 01/21/2015
 *
 * ProductTextFileTest.java
 * this is a JUnit Test for assignment 2a, class Queue.java
 */

import org.junit.*;
import model.Product;
import model.persistence.ProductTextFile;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductTextFileTest {
	
    private final ProductTextFile productTextFile = new ProductTextFile();

    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public boolean testAddProductNotNull(Product p) {
        // Product parameter should not be null
        assertTrue("!null", p != null);
        return true;
    }	
    
    @Test
    public boolean testAddProductAdded(Product p) {
        // add product into productTextFile
        productTextFile.addProduct(p);
        
        // test to see if product made it into productTextFile
        String pCode = p.getCode();
        Product productInTextFile = productTextFile.getProduct(pCode);
        assertEquals(pCode, productInTextFile.getCode());
        return true;
    }
    
}
