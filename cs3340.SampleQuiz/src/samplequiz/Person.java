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
public class Person {
    
    String name;
    private String email;
    
    public Person(String n, String e) {
        name = n;
        email = e;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
}
