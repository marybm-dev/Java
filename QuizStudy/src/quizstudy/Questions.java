/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizstudy;

public class Questions {
    
    private String question;
    private String answer;
    
    public Questions(String q, String a) {
        question = q;
        answer = a;
    }
    
    public String getQuestion() {
        return question;
    }
    
    public String getAnswer() {
        return answer;
    }
    
}
