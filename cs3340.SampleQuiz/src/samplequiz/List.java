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
public class List {
    
    public static final int CAPACITY = 5;
    
    private int[] vals = new int[CAPACITY];
    private int length = 0;
    
    public List() {
        
    }
    public void insert(int val) {
        vals[length++] = val;
    }
    
    public int get(int index) {
        return vals[index];
    }
    
    public int getLength() {
        return length;
    }
}
