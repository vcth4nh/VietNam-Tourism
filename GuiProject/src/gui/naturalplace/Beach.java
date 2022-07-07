package gui.naturalplace;
import java.awt.Font;

import javax.swing.JButton;

public class Beach extends JButton {
    public Beach(){
        this.setText("BEACH");
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setBounds(25, 590, 300, 40);
    }
}
