
import junit.framework.*;
import model.Product;
import model.persistence.ProductTextFile;

public class ProductTextFileTest extends TestCase {
    
    private ProductTextFile productTextFile = null;
    
    public ProductTextFileTest(String testName) {
        super(testName);
        productTextFile = new ProductTextFile();
    }
    
    @Override
    protected void setUp() throws Exception{
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public boolean testAddProduct(Product p) {
        // Product parameter should not be null
        assertTrue("!null", p != null);
        
        // add product into productTextFile
        productTextFile.addProduct(p);
        
        // test to see if product made it into productTextFile
        String pCode = p.getCode();
        Product productInTextFile = productTextFile.getProduct(pCode);
        assertEquals(pCode, productInTextFile.getCode());
        
        return true;
    }
    
}