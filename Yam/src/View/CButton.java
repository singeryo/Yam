/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.YamControl;
import Model.Combinaison;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

/**
 *
 * @author oliver
 */
public class CButton extends JButton implements ActionListener{
    
    private Combinaison c;
    private String s;
    private static String labels[] = {"1", "2", "3", "4", "5", "6", "", "", "", "Brelan", "Full", "Carre", "Pte suite", "Grande Suite", "Chance", "Yam", "", ""};
    
    
    public CButton(Combinaison pc)
    {
        super();
        c = pc;
        
        s = String.format("Case %s (%s)", CButton.labels[c.getIndex()], c.getVal());
        
        this.setText(s);
        
        this.addActionListener(this);
    }

    public static String[] getLabels() {
        return labels;
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {        
        YamControl.getInstance().processCombClick(c);
        YamControl.getInstance().resetFrameLancer();
        YamControl.getInstance().nextTurn();
    }
    
    
    
}
