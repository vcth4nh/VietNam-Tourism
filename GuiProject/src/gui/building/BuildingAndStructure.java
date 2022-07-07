package gui.building;
import java.awt.Font;

import javax.swing.JButton;

public class BuildingAndStructure extends JButton {
    
    public BuildingAndStructure(){
        this.setText("BUILDING");
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setBounds(25, 120, 350, 40);
    }
}
