/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JOptionPane;

/**
 *
 * @author oliver
 */
public class Player {
    
    private String name;
    private int[]tabscore;
    private List<Combinaison>propositions;
    private boolean done;
    
    public Player()
    {
        name = JOptionPane.showInputDialog("Nom du joueur: ");
        
        tabscore = new int[18];
        
        for(int i=0; i<=17; i++)
        {
            tabscore[i] = 0;
        }
        
        propositions = new ArrayList();
    }
    
    public void maj()
    {
        int sommest=0, sommeT=0;
        
        for(int i=0; i<=5; i++)
        {
            sommest += tabscore[i];
        }
        tabscore[6] = sommest; 
        
        if (sommest >= 63)
            tabscore[7] = 35;
        
        tabscore[8] = sommest + tabscore[7];
        
        for(int i=9; i<=15; i++)
        {
            sommeT += tabscore[i];
        }
        tabscore[16] = sommeT;
        
        tabscore[17] = sommeT + tabscore[8];
            
        
    }
    
    public void add(Combinaison c)
    {
        tabscore[c.getIndex()] = c.getVal();
        
        for(int i=0; i<=17; i++)
        {
            if (tabscore[i]==0)
                return;
        }
        
        done = true;
        
    }
    
    public void filterComb(List combinaisons)
    {
        ListIterator<Combinaison> it;
        
        it = combinaisons.listIterator();
        
        while(it.hasNext())
        {
            if(tabscore[it.next().getIndex()] != 0)
                it.remove();
            
        }
        
        propositions = combinaisons;
        
        
    }

    public String getName() {
        return name;
    }

    public int[] getTabscore() {
        return tabscore;
    }

    public List<Combinaison> getPropositions() {
        return propositions;
    }

    public boolean isDone() {
        return done;
    }
    
    
    
}
