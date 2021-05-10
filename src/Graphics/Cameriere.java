package Graphics;

import javax.swing.*;
import java.awt.*;

public class Cameriere extends JPanel{
    private JPanel CamerierePanel;
    private JFrame frame;

    public Cameriere(){
        frame= new JFrame("Cameriere");
        frame.setSize(new Dimension(500,500));
        frame.setContentPane(CamerierePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
