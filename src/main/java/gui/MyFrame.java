package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MyFrame extends JFrame implements ActionListener {
    JButton button = new JButton();

    MyFrame() {
        ImageIcon image = new ImageIcon("logo.jpg");
        Border border = BorderFactory.createLineBorder(Color.green, 3);

        // Button
        button.setBounds(200, 100, 100, 50);
        button.addActionListener(this);
        button.setText("Button ok ok");
        button.setFocusable(false);
        // Panel

        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.red);
        redPanel.setBounds(0, 0, 250, 250);

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.blue);
        bluePanel.setBounds(250, 0, 250, 250);

        JPanel greenPanel = new JPanel();
        greenPanel.setBackground(Color.green);
        greenPanel.setBounds(0, 250, 500, 250);
        // Label
        JLabel label = new JLabel("Bro, you code sooo bad");
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setForeground(new Color(0x00FF00));
        label.setFont(new Font("MV Boli", Font.PLAIN, 20));
        label.setBackground(Color.black);
        label.setOpaque(true);
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        // label.setBounds(100, 0, 300, 300);

        this.setTitle("VNTR");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setLayout(null);
        this.setIconImage(image.getImage());
        // this.getContentPane().setBackground(new Color(123, 50, 250));
        // this.add(label);
        // this.add(bluePanel);
        // this.add(greenPanel);
        // this.add(redPanel);

        this.add(button);

        // frame.pack();
    }

    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            System.out.println("Clicked");
        }
    }

}
