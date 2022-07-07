package gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Test {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setTitle("Tourism Ontology");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 900);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setLayout(null);

        JButton button = new JButton();
        button.setText("LOL");
        button.setBounds(100, 400, 300, 50);

        

        JPanel panel = new JPanel();

        JScrollPane sBar = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panel.setBackground(Color.white);
        panel.setBounds(50, 0, 400, 900);
        panel.setLayout(null);
        panel.add(button);

        frame.add(panel);
        frame.add(sBar);
    }
}
