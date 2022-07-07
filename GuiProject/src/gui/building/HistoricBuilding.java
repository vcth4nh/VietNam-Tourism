package gui.building;
import javax.swing.JButton;
import java.awt.Font;


public class HistoricBuilding extends JButton {
    public HistoricBuilding(){
        this.setText("HISTORIC BUILDING");
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setBounds(25, 270, 300, 40);
    }
}
