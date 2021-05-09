import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage {
    private JFrame frame;
    private JButton responsabileDiCassaButton;
    private JButton cameriereButton;
    private JButton cuocoButton;
    private JButton chefButton;
    private JPanel HomePagePanel;
    private JButton esciXButton;

    public HomePage() {
        frame = new JFrame("HomePage");
        frame.setSize(new Dimension(500, 500));
        frame.setContentPane(HomePagePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        esciXButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        chefButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Chef chef= new Chef();
                chef.setVisible(true);

            }
        });
        cuocoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Cuoco cuoco = new Cuoco();
                cuoco.setVisible(true);
            }
        });
        cameriereButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Cameriere cameriere= new Cameriere();
                cameriere.setVisible(true);
            }
        });
        responsabileDiCassaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Gestore_Cassa gestore_cassa= new Gestore_Cassa();
                gestore_cassa.setVisible(true);
            }
        });
    }


    public static void main(String[] args) {
        HomePage homePage= new HomePage();
    }
}
