package gui.building;
import javax.swing.JButton;
import java.awt.Font;

public class ReligiousBuilding extends JButton {
    public ReligiousBuilding(){
        this.setText("RELIGIOUS BUILDING");
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setBounds(25, 220, 300, 40);
    }
}
