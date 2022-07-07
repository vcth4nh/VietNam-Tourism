package gui.naturalplace;
import java.awt.Font;

import javax.swing.JButton;

public class BodyOfWater extends JButton {
    public BodyOfWater(){
        this.setText("BODY OF WATER");
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setBounds(25, 490, 300, 40);
    }
}
