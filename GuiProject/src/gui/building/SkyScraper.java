package gui.building;
import javax.swing.JButton;
import java.awt.Font;

public class SkyScraper extends JButton {
    public SkyScraper(){
        this.setText("SKYSCRAPER");
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setBounds(25, 320, 300, 40);
    }
    
}
