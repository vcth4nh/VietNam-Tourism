package gui.building;
import javax.swing.JButton;
import java.awt.Font;

public class Museum extends JButton {
    public Museum(){
        this.setText("MUSEUM");
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setBounds(25, 170, 300, 40);
    }
}
