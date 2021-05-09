import javax.swing.*;
import java.awt.*;

public class Cuoco extends JPanel{
    private JPanel CuocoPanel;
    private JFrame frame;

    public Cuoco(){
        frame= new JFrame("Cuoco");
        frame.setSize(new Dimension(500,500));
        frame.setContentPane(CuocoPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
