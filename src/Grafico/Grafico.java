/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Francy
 */
public class Grafico extends JPanel {
    
    public Grafico() {
        setPreferredSize(new Dimension(1500, 1500));
    }
     public void paintComponent( Graphics g ){
          g.drawRect(100, 100, 650, 600);
          g.drawOval(150, 150, 530, 530);
          g.drawLine(227, 227, 602, 602);
          g.drawLine(602, 227, 227, 602);
         g.setColor(Color.black);
         g.fillArc(145, 145, 540, 540, 45, 98);
         g.setColor(Color.yellow);
         g.fillArc(145, 145, 540, 540, 225, 90);
         g.setColor(Color.red);
         g.fillArc(145, 145, 540, 540, 135, 90);
         g.setColor(Color.green);
         g.fillArc(145, 145, 540, 540, 315, 90);
     }
    
     public static void main(String[] args) {
        JFrame fr = new JFrame( "Exame Normal");
        fr.setContentPane(new Grafico());
        fr.pack();
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }
}
