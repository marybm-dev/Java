
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main {

    
    public static void main(String[] args) {
        
        List<Integer> values = new ArrayList<>();
        values.add(13);
        values.add(0);
        values.add(5);
        if (hasZeroes(values)) {
            System.out.println("values has at least one zero");
        }
        else
            System.out.println("has no zeroes");

        Rectangle square = new Rectangle(10, 10, 5, 5);
        System.out.println("square perimeter: " + square.perimeter());
        
        Lottery lotto = new Lottery("Big bucks", 30, 30, 10);
        
        List<Book> books = new ArrayList<>();
        List<String> subjects = new ArrayList<>();
        List<String> subjects2 = new ArrayList<>();
        subjects.add("Horror");
        subjects.add("Sci-Fi");
        subjects.add("Fiction");
        subjects.add("Drama");
        subjects.add("Suspence");
        subjects.add("Romance");
        Book book = new Book("Monique", "Scary Hair 2", subjects);
        Book book2 = new Book("Monique", "Flight", subjects2);
        books.add(book);
        books.add(book2);
        Finder finder = new Finder(new StringChecker("Horror"));
        
        Book result = finder.find(books);
    }
    
    public static boolean hasZeroes(List<Integer> nums) {
        boolean result = false;
        for (int i=0 ; i<nums.size() ; i++) {
            if (0 == nums.get(i))
                result = true;
        }
        return result;
    }
    
    abstract static class Shape {
        protected Integer id, x, y;
        public Shape(Integer _x, Integer _y) {
            x = _x;
            y = _y;
        }
        public void move(Integer xadj, Integer yadj) {
            x += xadj;
            y += yadj;
        }
        public abstract Integer perimeter();
    }
    
    static class Rectangle extends Shape {
        private Integer len, width;
        
        public Rectangle(Integer _x, Integer _y, Integer l, Integer w) {
            super(_x, _y);
            len = l;
            width = w;
        }

        @Override
        public Integer perimeter() {
            return 2*len + 2*width;
        }
    }
    
    static class Lottery {
        private String file = "LottoResults.txt";
        private String name;
        private Integer xMax, yMax, zMax;
        private Integer xWin, yWin, zWin;
        
        public Lottery(String _name, Integer _xM, Integer _yM, Integer _zM) {
            name = _name;
            xMax = _xM;
            yMax = _yM;
            zMax = _zM;
            winningNumbers();
        }
        
        public void winningNumbers() {
            Random random = new Random();
            xWin = random.nextInt(xMax);
            yWin = random.nextInt(yMax);
            zWin = random.nextInt(zMax);
            storeResults();
        }
        
        public void storeResults() {
            try {
                // create file if doesn't exist
                
                String filename = "myfile.txt";
                Path path = Paths.get(file);
                
                Path pathname = Paths.get(filename);
                
                if( Files.exists(pathname)) {
                    System.out.println("file exists");
                }
                else {
                    Files.createFile(path);
                }
                
                
                if (Files.exists(path)) {
                    System.out.println("file exists");
                }
                else {
                    Files.createFile(path);
                }
                
                if(!Files.exists(Paths.get(file))) {
                    Files.createFile(Paths.get(file));
                }
                // write to file
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
                out.println("Winning numbers " + xWin + ", " + yWin + ", " + zWin);
                System.out.println("Winning numbers " + xWin + ", " + yWin + ", " + zWin);
                out.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    static class Finder {
        private Checker checker;
        
        public Finder(Checker _checker) {
            checker = _checker;
        }
        
        public Book find(List<Book> books) {
            for (Book b : books) {
                if (checker.check(b)) {
                    System.out.println("Found Book: " + b.title + " by " + b.author);
                    return b;
                }
            }
            return null;
        }
        
    }
    
    static class Book {
        public String author, title;
        public List<String> subjects;
        
        public Book(String _a, String _t, List<String> _s) {
            author = _a;
            title = _t;
            subjects = _s;
        }
    }
    
    static interface Checker {
        public boolean check(Book _book);
    }
    
    static class StringChecker implements Checker {
        String str;
        
        public StringChecker(String _str) {
            str = _str;
        }
        
        @Override
        public boolean check(Book _book) {
            return _book.subjects.contains(str);
        }
    }
}

