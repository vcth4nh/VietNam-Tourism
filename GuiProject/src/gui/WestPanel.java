package gui;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.building.BuildingAndStructure;
import gui.building.HistoricBuilding;
import gui.building.Museum;
import gui.building.ReligiousBuilding;
import gui.building.SkyScraper;
import gui.naturalplace.Beach;
import gui.naturalplace.BodyOfWater;
import gui.naturalplace.Cave;
import gui.naturalplace.NationalPark;
import gui.naturalplace.NaturalPlace;

public class WestPanel extends JPanel {

    public WestPanel() {
        this.setBackground(Color.white);
        this.setBounds(50, 0, 400, 900);
        this.setLayout(null);

        JLabel vnTourismLabel = new JLabel();
        vnTourismLabel.setText("VN TOURISM");
        vnTourismLabel.setHorizontalTextPosition(JLabel.CENTER);
        vnTourismLabel.setVerticalTextPosition(JLabel.CENTER);
        vnTourismLabel.setBounds(100, 25, 200, 50);
        vnTourismLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD,  30));

        BuildingAndStructure button1 = new BuildingAndStructure();
        Museum button2 = new Museum();
        ReligiousBuilding button3 = new ReligiousBuilding();
        HistoricBuilding button4 = new HistoricBuilding();
        SkyScraper button5 = new SkyScraper();

        NaturalPlace button6 = new NaturalPlace();
        NationalPark button7 = new NationalPark();
        BodyOfWater button8 = new BodyOfWater();
        Cave button9 = new Cave();
        Beach button10 = new Beach();

 
        JPanel vnTourismPanel = new JPanel();
        vnTourismPanel.setBounds(0, 0, 400, 100);
        vnTourismPanel.setBackground(new Color(166, 195, 233));
        vnTourismPanel.setLayout(null);
        vnTourismPanel.add(vnTourismLabel);
        
        this.add(button1);     
        this.add(button2);   
        this.add(button3);
        this.add(button4);
        this.add(button5);

        this.add(button6);
        this.add(button7);
        this.add(button8);
        this.add(button9);
        this.add(button10);
        this.add(vnTourismPanel);

        

        
        
    }
    
}
