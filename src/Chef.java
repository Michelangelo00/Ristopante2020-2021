import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chef extends JPanel{
    ChefLogic chefLogic = new ChefLogic();
    private JFrame frame;
    private JPanel ChefPanel;
    private JTextArea PiattoNome;
    private JTextArea PiattoPrezzo;
    private JButton aggiungiPiattoButton;

    public Chef(){
        frame= new JFrame("Chef");
        frame.setSize(new Dimension(500,500));
        frame.setContentPane(ChefPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        aggiungiPiattoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome= PiattoNome.getText();
                String prezzo= PiattoPrezzo.getText();
                chefLogic.addFood(nome,prezzo);
            }
        });
    }
}
