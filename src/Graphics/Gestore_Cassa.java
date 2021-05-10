package Graphics;

import javax.swing.*;
import java.awt.*;

public class Gestore_Cassa extends JPanel{
    private JPanel GestoreCassaPanel;
    private JFrame frame;

    public Gestore_Cassa(){
        frame= new JFrame("GestoreCassa");
        frame.setSize(new Dimension(500,500));
        frame.setContentPane(GestoreCassaPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
