package gui.naturalplace;
import java.awt.Font;

import javax.swing.JButton;

public class NaturalPlace extends JButton {
    public NaturalPlace(){
        this.setText("NATURAL PLACE");
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setBounds(25, 390, 350, 40);
    }
    
}
