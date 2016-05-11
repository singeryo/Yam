/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Dimensions;
import java.awt.Color;
import java.awt.Graphics;
import static java.lang.Math.random;
import javax.swing.*;

/**
 *
 * @author oliver
 */
public class Dice extends JButton {
    
    private int valeur=1;
    private ImageIcon i;
    private boolean toThrow= true;
    
    public Dice()
    {
        valeur = 1;
    }
    
    public Dice(JButton b)
    {
        valeur = 1;
    }
    
    
    public void lancer()
    {
        if (!toThrow) return;
        
        valeur = ((int)(random()*6)+1);
        System.out.println(valeur);
        display();
    }
    
    public void display()
    {
        switch (valeur){
                case 1:i = new ImageIcon("src/images/one.png"); break;
                case 2:i = new ImageIcon("src/images/two.png"); break;
                case 3:i = new ImageIcon("src/images/three.png"); break;
                case 4:i = new ImageIcon("src/images/four.png"); break;
                case 5:i = new ImageIcon("src/images/five.png"); break;
                case 6:i = new ImageIcon("src/images/six.png"); break; 
        }
        
        this.setIcon(i);
        
    }    

    public int getValeur() {
        return valeur;
    }

    public ImageIcon getI() {
        return i;
    }

    public boolean isToThrow() {
        return toThrow;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public void setToThrow(boolean toThrow) {
        this.toThrow = toThrow;
    }
    
    public void toggleTothrow()
    {
        toThrow = !(toThrow);
    }
    
    
    @Override
    public void paintComponent(Graphics g)
    {
        Dimensions d;
        d = Fenetre.getPos(this);
        
        if(toThrow)
        {
            g.setColor(Color.green);
            this.getBorder().paintBorder(this, g, d.getX(), d.getY(), d.getW(), d.getH());
        }
        else
            this.setBorderPainted(toThrow);
    }
    
    
}
