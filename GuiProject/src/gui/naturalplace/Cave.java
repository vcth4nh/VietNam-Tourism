package gui.naturalplace;
import java.awt.Font;

import javax.swing.JButton;

public class Cave extends JButton {
    public Cave(){
        this.setText("CAVE");
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setBounds(25, 540, 300, 40);
    }
}
