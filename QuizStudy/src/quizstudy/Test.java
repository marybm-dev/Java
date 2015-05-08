
package quizstudy;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Test {
    
    private ArrayList<Questions> questions;
    private final Questions q1, q2, q3, q4, q5;
    
    public Test() {
        q1 = new Questions("What color is the sky?", "Blue");
        q2 = new Questions("Is Puka a dog?", "Yes");
        q3 = new Questions("Do I love Monique?", "Yes");
        q4 = new Questions("How many hours are in a day?", "24");
        q5 = new Questions("How many days are in a year?", "365");
        
        questions = new ArrayList<>();
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
        questions.add(q4);
        questions.add(q5);
        
        System.out.println(questions.size());
    }
    
    public void takeTest() {
        Scanner input = new Scanner(System.in);
        String answer;
        int correct = 0;
        int problems = questions.size();
        for (ListIterator itr = questions.listIterator(); itr.hasNext(); ) {
            
            Questions q = (Questions) itr.next();
            System.out.print(q.getQuestion()); 
            answer = input.next();
            
            if (answer.equals(q.getAnswer())) {
                System.out.println("   That's correct!");
                correct++;
            } else {
                System.out.println("   Sorry that's wrong.");
            }
            
        }
        System.out.println("Total score: " + correct/problems);
    }
    
    
    
}
