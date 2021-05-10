package Graphics;

import Logic.ChefLogic;
import Logic.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class Chef extends JPanel{
    Data data = new Data();
    ChefLogic chefLogic = new ChefLogic();
    private JFrame frame;
    private JPanel ChefPanel;
    private JTextArea PiattoNome;
    private JTextArea PiattoPrezzo;
    private JButton aggiungiPiattoButton;
    private JButton confermaAggiunteButton;


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
                Double prezzo= Double.parseDouble(PiattoPrezzo.getText());
                chefLogic.addFood(nome,prezzo);
                PiattoNome.selectAll();
                PiattoNome.replaceSelection("");
                PiattoPrezzo.selectAll();
                PiattoPrezzo.replaceSelection("");
            }
        });


        confermaAggiunteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chefLogic.finalizzaMenu();
            }
        });

    }

}
