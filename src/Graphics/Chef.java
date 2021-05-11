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
import java.util.ArrayList;

public class Chef extends JPanel{
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

        DefaultListModel<Piatto> model= new DefaultListModel<>();
        for(Piatto p: chefLogic.getMenu()){
            model.addElement(p);
        }
        MenuList= new JList();
        MenuList.setModel(model);
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
                model.addElement(new Piatto(nome,prezzo));
            }
        });


        confermaAggiunteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chefLogic.finalizzaMenu();
            }
        });

        rimuoviPiattoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chefLogic.removeFood((Piatto) MenuList.getSelectedValue());
                model.removeElement(MenuList.getSelectedValue());
            }
        });
    }

}
