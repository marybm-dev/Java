/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samplequiz;

/**
 *
 * @author mlmartinez85
 */
public class Mail {
    
    private Person sender;
    private String content;
    private Person receiver;
    
    public Mail(Person s, String c, Person r) {
        sender = s;
        content = c;
        receiver = r;
    }
    
    public void updateContent(String c) {
        content = c;
    }
    
    public void displayMail() {
        System.out.println("To: " + receiver.getName() + "\n");
        System.out.println(content + "\n");
        System.out.println(sender.getName());
    }
}
