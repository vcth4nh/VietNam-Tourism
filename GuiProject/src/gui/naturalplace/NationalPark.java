package gui.naturalplace;
import java.awt.Font;

import javax.swing.JButton;

public class NationalPark extends JButton {
    public NationalPark(){
        this.setText("NATIONAL PARK");
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setBounds(25, 440, 300, 40);
    }
}
