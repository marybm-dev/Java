
package quizstudy;
import java.util.Scanner;

public class QuizStudy {

    public static void main(String[] args) {
        
        
        String name;
        Scanner input = new Scanner(System.in);
        
        System.out.println("Welcome to the Study Session");
        System.out.println("Please enter your name");
        name = input.next();
        
        System.out.println("Hi " + name);
        
        Test t = new Test();
        t.takeTest();
        System.out.println("Great job, " + name);
    }
    
}
