package Graphics;

import Logic.ChefLogic;
import Logic.Data;
import Logic.Piatto;

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
    private JButton modificaPiattoButton;
    private JButton rimuoviPiattoButton;
    private JList list1;
    private JPanel ListPanel;
    private GridBagConstraints c = new GridBagConstraints();
    private JList MenuList;


    public Chef(){
        frame= new JFrame("Chef");
        MenuList= new JList(data.getMenu().toArray());
        MenuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListPanel.add(MenuList);
        frame.setBounds(600,300,820,600);
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
