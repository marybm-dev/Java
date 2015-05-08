
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David
 */
public class Order implements Comparable<Object> {
    private static int orderCounter = 0;
    protected List<LineItem> items;
    protected int orderNo;
    
    public Order() {
        items = new ArrayList<>();
        orderNo = ++orderCounter;
    }
    
    public void addItem(LineItem item) {
        items.add(item);
    }
    
    public int total() {
        int sum = 0;    // in cents
        for (LineItem item: items) {
            sum += item.itemTotal();
        }
        return sum;
    }

    @Override
    public int compareTo(Object o) {
        Order orderToCompare = (Order) o;
        return (this.total() - orderToCompare.total());
    }
    
    public int getOrderNumber() {
        return orderNo;
    }

}