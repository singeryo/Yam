/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Combinaison;
import Model.Descore;
import Model.Player;
import View.CButton;
import View.Dice;
import View.Fenetre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.lang.Math.max;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JOptionPane;

/**
 *
 * @author oliver
 */
public class YamControl implements ActionListener{
    
    private static YamControl INSTANCE;
    
    
    private int nbPlayers;
    private List<Player> players;
    private Fenetre f;
    private Descore des;
    private Player currentP;
    private List<CButton> possibles;
    private Combinaison chosen;
    private int cpt=2;
    
    
    
    private YamControl()
    {
        
        init();
        initFirstPlayer();
        
    }


    public static YamControl getInstance()
    {
        if(INSTANCE == null)
            INSTANCE = new YamControl();
        return INSTANCE;
    }
    
    
    public void init()
    {
        nbPlayers = Integer.parseInt(JOptionPane.showInputDialog(null, "Nombre de joueurs: "));
        possibles = new ArrayList();
        
        f = new Fenetre(nbPlayers);
        
        players = new ArrayList();
        
        for(int i=0; i<=nbPlayers-1; i++)
            players.add(new Player());
        
        f.getLancer().addActionListener(this);
        
        des = new Descore();
        
        f.setVisible(true);
    }
    
    
    public void initFirstPlayer()
    {
        int index;
        String s;
        
        index = (int) (random() * (nbPlayers-1));       
        currentP = players.get(index);
        
        s = String.format("Le premier joueur est: %s", currentP.getName());
        f.getPlayerLabel().setName(currentP.getName());
        
        JOptionPane.showMessageDialog(f, s);
    }
    
    public void nextTurn()
    {
        ListIterator<Player> it;
        it = players.listIterator();
        
        while(it.next() != currentP)
        {}
        
        currentP = it.next();
        f.getPlayerLabel().setName(currentP.getName());
        
    }
    
    

    public void processThrow()
    {
        cpt--;
        f.informTurn(cpt);
        f.lancer();
        
        des.init(f.lancerdes());
        des.init_comb();
        currentP.filterComb(des.getCpossibles());
        
    }
    
    public void processThrowView()
    {
        ListIterator<Combinaison> it;
        
        possibles.clear();
        
        it = currentP.getPropositions().listIterator();
        
        
        while (it.hasNext())
        {
            possibles.add(new CButton(it.next()));
        }
        
        f.setCtab(possibles);
        
        
        if(cpt==0)
            f.getLancer().setVisible(false);
    }
    
    
    public void resetFrameLancer()
    {
        f.getLancer().setVisible(true);
    }
    
    
    public void processCombClick(Combinaison c)
    {
        ListIterator<Player> it;
        it = players.listIterator();
        
        currentP.add(c);        
        
        while(it.next() == currentP) {}
        
        f.refresh(it.nextIndex(), c);
        
        for(int i=0; i<nbPlayers; i++)
        {
            if (! players.get(i).isDone())
                return;
        }
        
        endGame();
        
    }

   
    public void endGame()
    {
        Player winner=null;
        int maxScore = 0;
        String s;
        
        for(int i=0; i<nbPlayers; i++)
        {
            maxScore = max(players.get(i).getTabscore()[17], maxScore);
            if (players.get(i).getTabscore()[17] == maxScore)
                winner = players.get(i);
        }
        
        s = String.format("Le gagnant est %s !!", winner.getName());
        
        JOptionPane.showMessageDialog(f, s);
        
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        processThrow();
        processThrowView();
        
    }
 
    
    
            
    
}
