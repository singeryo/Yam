
package View;

import Model.Combinaison;
import Model.Dimensions;
import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.LINE_END;
import static java.awt.BorderLayout.LINE_START;
import static java.awt.BorderLayout.PAGE_END;
import static java.awt.BorderLayout.PAGE_START;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.*;
import java.awt.Graphics;

public class Fenetre extends JFrame implements ActionListener{

    
    public Fenetre(int nbP) {      
        nbPlayers = nbP;
        initComponents();
        scorePanel = new JPanel();
    }
    
    
    private void initScorePanel()
    {
        JLabel[]tempscore;
        
        scores = new ArrayList();
        slabels = new JLabel[18];
        scores.add(slabels);
        
        scoreLayout = new GridLayout();
        scoreLayout.setColumns(nbPlayers+1);
        scoreLayout.setRows(18);
        
        for(int i=0; i<=17; i++)
        {
            slabels[i] = new JLabel();            
            slabels[i].setText(CButton.getLabels()[i]);
        }
        
        for(int i=0; i<nbPlayers; i++)
        {
            tempscore = new JLabel[18];
            for(int j=0; j<=17; j++)
                tempscore[j] = new JLabel();
            scores.add(tempscore);
            
        }
        
        scorePanel = new JPanel();
      //  scorePanel.setPreferredSize(new Dimension(200, 200));
        scorePanel.setLayout(scoreLayout);
        
        for(int i=1; i<=nbPlayers; i++)
        {
            for(int j=0; j<=17; j++)
                scorePanel.add((scores.get(i))[j]);
                
        }
        
    }
    
    public void initDicePanel()
    {
        dtab = new Dice[5];
        
        for (int i=0; i<=4; i++)
        {
            dtab[i] = new Dice();
            dtab[i].addActionListener(this);
        }
        
        lancer = new JButton();
        lancer.setText("Lancer");
        
        diceLayout = new GridLayout();
        diceLayout.setColumns(6);
        diceLayout.setRows(2);
        
        
        dicePanel = new JPanel();
      //  dicePanel.setPreferredSize(new Dimension(200, 100));
        
        dicePanel.setLayout(diceLayout);
        for(int i=0; i<=4; i++)
        {
            dicePanel.add(dtab[i]);
        }
        
        dicePanel.add(lancer);
        
    }
    
    public void initCombinationsPanel()
    {
        ctab = new ArrayList();
        possibles = new ArrayList();
        
        combinationsPanel = new JPanel();
     //   combinationsPanel.setPreferredSize(new Dimension(300, 300));
        combinationsPanel.setLayout(new FlowLayout());
    }

    
    public void initTitlePanel()
    {
        title = new JLabel();
        title.setSize(new Dimension(290, 87));
        titlePanel = new JPanel();
   //     titlePanel.setPreferredSize(new Dimension(100, 90));
        titlePanel.add(title);
    }
    
    public void initPlayerPanel()
    {
        playerLabel = new JLabel();
        playerPanel = new JPanel();
    //    playerPanel.setPreferredSize(new Dimension(200, 50));
        playerPanel.add(playerLabel);
    }
                             
    private void initComponents()
    { 
        
        Dimension d = new Dimension(1000, 1200);
        this.setSize(d);
        
        initScorePanel();
        initDicePanel();   
        initCombinationsPanel();
        initPlayerPanel();
        initTitlePanel();
        
        
        
        controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());
        
        this.setContentPane(controlPanel);
        
        controlPanel.add(titlePanel, PAGE_START);
        controlPanel.add(playerPanel, PAGE_END);
        controlPanel.add(scorePanel, LINE_START);       
        controlPanel.add(combinationsPanel, LINE_END);
        controlPanel.add(dicePanel, CENTER);
        
        
        
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);    
    }               
    
    
    public int[] lancerdes()
    {
        int[]lancerdes;
        lancerdes = new int[5];
        
        for(int i=0; i<=4; i++)
            lancerdes[i] = dtab[i].getValeur();
        
        return lancerdes;
    }
    
     
    public void lancer()
    {
        for(int i=0; i<=4; i++)
            dtab[i].lancer();
    }
    
    
    
    
    
    private Dice[]dtab;   
    private JLabel[] ltab;   
    private List<CButton> ctab;
    private List<JLabel[]> scores;
    private JLabel[] slabels;
    private JLabel playerLabel;
    private JButton lancer;
    private JLabel title;
    private List<CButton> possibles;
    private Combinaison chosen;
    
    
    
    private JPanel scorePanel;
    private JPanel titlePanel;
    private JPanel playerPanel;
    private JPanel dicePanel;
    private JPanel combinationsPanel;
    
    private JPanel controlPanel;
    
    
    private GridLayout scoreLayout;
    private GridLayout diceLayout;
    
    private int nbPlayers;
    private int turnCounter;
    

    public Dice[] getDtab() {
        return dtab;
    }

    public JLabel[] getLtab() {
        return ltab;
    }

    public List<CButton> getCtab() {
        return ctab;
    }

    public List<JLabel[]> getScores() {
        return scores;
    }

    public JButton getLancer() {
        return lancer;
    }

    public JLabel getPlayerLabel() {
        return playerLabel;
    }

    public void setCtab(List<CButton> ctab) {
        this.ctab.clear();
        this.ctab = ctab;
        
        ListIterator<CButton> it;
        
        it = ctab.listIterator();
        
        while(it.hasNext())
            combinationsPanel.add(it.next());
        
        
        
    }
    
    
    public void paintDice()
    {
        for(int i=0; i<=4; i++)
        {
            dtab[i].repaint();
        }
    }

    
    
    public static Dimensions getPos(Dice d)
    {
        int x;
        int y;
        int w;
        int h;
        
        x = d.getX();
        y = d.getY();
        w = d.getWidth();
        h = d.getHeight();
        
        return(new Dimensions(x, y, w, h));
        
    }
    
    
    public void informTurn(int c)
    {
        turnCounter = c;
    }
    
    public void refresh(int i, Combinaison c)
    {
        scores.get(i+1)[c.getIndex()].setText(Integer.toString(c.getVal()));   
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(turnCounter == 2) return;
        
        ((Dice)e.getSource()).toggleTothrow();
        ((Dice)e.getSource()).repaint();
               
    }

    
    
    

   
}
