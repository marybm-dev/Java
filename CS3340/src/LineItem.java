
public class LineItem {
    protected String productName;
    protected int numPurchased;
    protected int unitCost; // in cents
    
    public LineItem(String _productName, int _numPurchased, int _unitCost) {
        productName = _productName;
        numPurchased = _numPurchased;
        unitCost = _unitCost;
    }
    
    public int getNumPurchased() {
        return numPurchased;
    }
    
    public int getUnitCost() {
        return unitCost;
    }
    
    public int itemTotal() {
        return numPurchased * unitCost;
    }
}