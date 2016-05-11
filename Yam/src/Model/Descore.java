/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import static java.util.Arrays.sort;
import java.util.List;

/**
 *
 * @author oliver
 */
public class Descore {
    
    private int[]lancer;   
    private boolean[]t_dispos;    
    private List<Combinaison> cpossibles;
    private int[]occurences;
    private int sommedes = 0;
    private boolean[]doublons;
    
    
    public Descore()
    {
        lancer = new int[5];
        occurences = new int[6];
        cpossibles = new ArrayList();
        
        for(int i=0; i<=5; i++)
        {
            occurences[i] = 0;
        }
        
    }
    
    
    
    public void init(int[]tab)
    {
        int index;
        
        sort(tab);   
        lancer = tab;
        
        cpossibles.clear();        
        sommedes = 0;
        
        
        for(int i=0; i<=5; i++)
        {
            occurences[i] = 0;
            doublons[i] = false;
        }
        
        for(int i=0; i<=4; i++)
        {
            index = lancer[i];        
            occurences[index-1]++;
            
            sommedes += lancer[i];
        }
        
    }
    
    public void init_comb()
    {
        int nbdoublons=0;
        int nbconsecutifs=0;
        boolean suitepossible = true;
        boolean brelan = false;
        
        cpossibles.add(new Combinaison(14, sommedes)); 
        
        for(int i=0; i<=5; i++)
        {
            if (occurences[i]>0)
            {
                cpossibles.add(new Combinaison(i, (i+1)*occurences[i]));
            
                if(occurences[i]==2)
                {
                   doublons[i] = true;
                   nbdoublons++;
                   if (nbdoublons>1)
                       suitepossible = false;
                }                   
                
                
                if(occurences[i]>=3)
                {
                    suitepossible = false;
                    brelan = true;
                    
                    cpossibles.add(new Combinaison(9, sommedes));
        
                    if(occurences[i]>=4)
                    {
                        cpossibles.add(new Combinaison(11, sommedes));
                        
                        if(occurences[i]==5)
                            cpossibles.add(new Combinaison(15, 50));
                    }
                    
                }
                
                
            }              
                
        }
        
        if((nbdoublons == 1)&&brelan)
                        cpossibles.add(new Combinaison(10, 25));
            
        if (!(suitepossible)) return;
        
        for(int i=0; i<=2; i++)
        {
            if(doublons[lancer[i]-1])
                i++;
            
            if(lancer[i+1] == lancer[i]+1)
                nbconsecutifs++;
            else if(i>0 && i<3)
                suitepossible = false;
            
        }
        
        if(suitepossible && nbconsecutifs>=4)
        {
            cpossibles.add(new Combinaison(12, 35));
            
            if(nbconsecutifs == 5)
                cpossibles.add(new Combinaison(13, 40));
        }
        
        
        
    }

    public int[] getLancer() {
        return lancer;
    }

    public boolean[] getT_dispos() {
        return t_dispos;
    }

    public List<Combinaison> getCpossibles() {
        return cpossibles;
    }

    public int[] getOccurences() {
        return occurences;
    }

    public int getSommedes() {
        return sommedes;
    }

    public boolean[] getDoublons() {
        return doublons;
    }
    
    
    
    
}
