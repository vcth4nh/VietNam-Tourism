package gui;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;



public class MainFrame extends JFrame {
    MainFrame(){
        this.setTitle("Tourism Ontology");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(1600, 900);
        this.setVisible(true);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.DARK_GRAY);

        this.setLayout(null);
        
    }
}
