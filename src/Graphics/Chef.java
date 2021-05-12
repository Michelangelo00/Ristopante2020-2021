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

/**
 * classe che implementa graficamente la figura dello Chef
 */

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
    private DefaultListModel<Piatto> model= new DefaultListModel<>();


    public Chef() {
        /**
         * Costruttore della base grafica
         */
        frame = new JFrame("Chef");
        LoadMenuList();
        frame.setBounds(600, 300, 820, 600);
        frame.setContentPane(ChefPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        /**
         * Listener per aggiungere un nuovo piatto al menù
         */
        aggiungiPiattoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome= PiattoNome.getText();
                Double prezzo= Double.parseDouble(PiattoPrezzo.getText());
                chefLogic.addFood(nome,prezzo);
                Add(new Piatto(nome,prezzo));
                PiattoNome.setText("");
                PiattoPrezzo.setText("");
            }
        });

        /**
         * Listener per confermare le modifiche effettuate al menù
         */
        confermaAggiunteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chefLogic.finalizzaMenu();
            }
        });

        /**
         * Listener per rimuovere un piatto dal menù
         */
        rimuoviPiattoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChefLogic.removeFood((Piatto) MenuList.getSelectedValue());
                Delete((Piatto) MenuList.getSelectedValue());
            }
        });

        /**
         * Listener per modificare un piatto dal menù
         */
        modificaPiattoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Piatto modifica = (Piatto) MenuList.getSelectedValue();
                    String nuovo_nome= JOptionPane.showInputDialog("Modifica il nome del piatto",modifica.getNome());
                    double nuovo_prezzo = Double.parseDouble(JOptionPane.showInputDialog("Modifica il prezzo del piatto",modifica.getPrezzo()));
                    Piatto piatto_modificato =new Piatto(nuovo_nome,nuovo_prezzo);
                    chefLogic.editFood((Piatto) MenuList.getSelectedValue(),piatto_modificato);
                    Edit(MenuList.getSelectedIndex(),piatto_modificato);

            }
        });
    }

    /**
     * metodo per caricare il modello della lista con i piatti del menù
     */
    public void LoadMenuList(){
        for (Piatto p : chefLogic.getMenu()) {
            model.addElement(p);
        }
        MenuList = new JList();
        MenuList.setModel(model);
        ListPanel.add(MenuList);
    }


    public void Add(Piatto piatto){
        model.addElement(piatto);
    }
    public void Edit(int index, Piatto piatto_nuovo){
        model.set(index,piatto_nuovo);
    }
    public void Delete(Piatto piatto){
        model.removeElement(piatto);
    }

}
