package gui;

public class Main {
    public static void main(String[] args){
        
        MainFrame frame = new MainFrame();
        WestPanel west = new WestPanel();
        CenterPanel center = new CenterPanel();

        frame.add(center);
        frame.add(west);

    }
}
