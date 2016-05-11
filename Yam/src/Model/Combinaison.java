/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author oliver
 */
public class Combinaison {
    
    private int index;
    private int val;
    
    public Combinaison(int ind, int val)
    {
        index = ind;
        this.val = val;
    }

    public int getIndex() {
        return index;
    }


    public int getVal() {
        return val;
    }

    public void setIndex(int index) {
        this.index = index;
    }

 

    public void setVal(int val) {
        this.val = val;
    }
    
    
}
