
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        DataCluster myCluster = new DataCluster();
        for (int i=0; i<10 ; i++) {
            myCluster.addPoint(new Point(i, i));
        }
        Point myCentroid = myCluster.centroid();
        System.out.println("Centroid: (" + myCentroid.x + ", " + myCentroid.y + ")");
        
        Floor kitchen = new Floor(10,20);
        Floor living = new Floor(20,20);
        Floor bedroom = new Floor(15,15);
        Floor bathroom = new Floor(10,10);
        
        FloorPlan myPlan = new FloorPlan();
        myPlan.addFloor(kitchen);
        myPlan.addFloor(living);
        myPlan.addFloor(bedroom);
        myPlan.addFloor(bathroom);
        
        Home myHome = new Home("123 Main Street", "Oakland", 800000, myPlan);
        
        City oakland = new City();
        oakland.addHome(myHome);
        
        kitchen = new Floor(10,10);
        living = new Floor(15,15);
        bedroom = new Floor(12,12);
        bathroom = new Floor(18,18);
        
        FloorPlan myPlan1 = new FloorPlan();
        myPlan1.addFloor(kitchen);
        myPlan1.addFloor(living);
        myPlan1.addFloor(bedroom);
        myPlan1.addFloor(bathroom);
        
        Home home1 = new Home("4432 Tulip Ave", "Oakland", 500000, myPlan1);
        
        oakland.addHome(home1);
        
        System.out.println("Avg: " + oakland.pricePerSqFt());
    }
    
}

class City {
    private List<Home> homes;
    public City() {
        homes = new ArrayList<>();
    }
    public void addHome(Home h) {
        homes.add(h);
    }
    public Integer pricePerSqFt() {
        int sumArea = 0;
        int sumPrice = 0;
        for (Home h : homes) {
            sumArea += h.sqFeet();
            sumPrice += h.getPriceSold();
        }
        System.out.println("price: " + sumPrice);
        System.out.println("sqft: " + sumArea);
        return sumPrice/sumArea;
    }
}

class Home {
    private String address;
    private String city;
    private Integer priceSold;
    private FloorPlan floorPlan;
    public Home(String a, String c, Integer p, FloorPlan fp) {
        address = a;
        city = c;
        priceSold = p;
        floorPlan = fp;
    }
    public Integer sqFeet() {
        return floorPlan.totalArea();
    }
    
    public Integer getPriceSold() {
        return priceSold;
    }
}

class Floor {
    private int length;
    private int width;
    public Floor(int l, int w) {
        length = l;
        width = w;
    }
    public Integer squareFeet() {
        return length * width;
    }
}

class FloorPlan {
    private List<Floor> floors;
    public FloorPlan() { 
        floors = new ArrayList<>(); 
    }
    public void addFloor(Floor f) {
        floors.add(f);
    }
    public Integer totalArea() {
        int sum = 0;
        for (Floor f : floors) {
            sum += f.squareFeet();
        }
        return sum;
    }
}

class Point {
    public int x, y;
    public Point(int _x, int _y) {
        x = _x;
        y = _y;
    }
}

class DataCluster {
    private List<Point> points = new ArrayList<>();
    public DataCluster() {}
    public void addPoint(Point p) {
        points.add(p);
    }
    public Point centroid() {
        int xsum = 0;
        int ysum = 0;
        for (Point p : points) {
            xsum += p.x;
            ysum += p.y;
        }
        return (new Point(xsum/points.size(), ysum/points.size()));
    }
}