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
    private JPanel ListPanel;
    private JButton indietroButton;
    private GridBagConstraints c = new GridBagConstraints();
    private JList<Piatto> MenuList;
    private final DefaultListModel<Piatto> model= new DefaultListModel<>();


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
                if (!(PiattoNome.getText().equals("")&&PiattoPrezzo.getText().equals(""))) {
                    if (PiattoNome.getText().equals("")) {
                        JOptionPane.showMessageDialog(frame, "Inserisci il nome del piatto!");
                    } else if (PiattoPrezzo.getText().equals("")) {
                        JOptionPane.showMessageDialog(frame, "Inserisci il prezzo del piatto!");
                    }else{
                        String nome= PiattoNome.getText();
                        Double prezzo= Double.parseDouble(PiattoPrezzo.getText());
                        chefLogic.addFood(new Piatto(nome,prezzo));
                        Add(new Piatto(nome,prezzo));
                        PiattoNome.setText("");
                        PiattoPrezzo.setText("");
                        JOptionPane.showMessageDialog(frame,"Piatto aggiunto!");

                    }
                }else{
                    JOptionPane.showMessageDialog(frame, "Inserisci le informazioni per il piatto!");

                }
            }
        });

        /**
         * Listener per confermare le modifiche effettuate al menù
         */
        confermaAggiunteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chefLogic.finalizzaMenu();
                JOptionPane.showMessageDialog(frame,"Modifiche salvate correttamente!");
            }
        });

        /**
         * Listener per rimuovere un piatto dal menù
         */
        rimuoviPiattoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(MenuList.getSelectedValue()==null)) {
                    chefLogic.removeFood(MenuList.getSelectedValue());
                    Delete(MenuList.getSelectedValue());
                    JOptionPane.showMessageDialog(frame, "Piatto eliminato correttamente");
                }else{
                    JOptionPane.showMessageDialog(frame, "Seleziona il piatto da eliminare!");
                }
            }
        });

        /**
         * Listener per modificare un piatto dal menù
         */
        modificaPiattoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(MenuList.getSelectedValue()==null)) {
                    Piatto modifica = (Piatto) MenuList.getSelectedValue();
                    String nuovo_nome = JOptionPane.showInputDialog("Modifica il nome del piatto", modifica.getNome());
                    double nuovo_prezzo = Double.parseDouble(JOptionPane.showInputDialog("Modifica il prezzo del piatto", modifica.getPrezzo()));
                    Piatto piatto_modificato = new Piatto(nuovo_nome, nuovo_prezzo);
                    chefLogic.editFood(MenuList.getSelectedValue(), piatto_modificato);
                    Edit(MenuList.getSelectedIndex(), piatto_modificato);
                    JOptionPane.showMessageDialog(frame, "Piatto modificato correttamente!");
                }else{
                    JOptionPane.showMessageDialog(frame, "Seleziona il piatto da modificare!");
                }

            }
        });
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                chefLogic.getMenu().clear();
                HomePage homePage = new HomePage();
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
