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
import java.util.Collections;

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
    private JScrollBar scrollBar1;
    private JList<Piatto> MenuList;
    private final DefaultListModel<Piatto> model= new DefaultListModel<>();
    private ArrayList<Piatto> changed = new ArrayList<>(chefLogic.getMenu());

    public Chef() {
        /**
         * Costruttore della base grafica
         */
        frame = new JFrame("Chef");
        LoadMenuList();
        frame.setBounds(600, 300, 820, 600);
        frame.setContentPane(ChefPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
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
                        if(!chefLogic.addFood(new Piatto(nome,prezzo))){
                            JOptionPane.showMessageDialog(frame,"Piatto gia esistente");
                        }else {
                            Add(new Piatto(nome, prezzo));
                            PiattoNome.setText("");
                            PiattoPrezzo.setText("");
                            frame.pack();
                            JOptionPane.showMessageDialog(frame,"Piatto aggiunto correttamente","Aggiunta piatto",JOptionPane.INFORMATION_MESSAGE);
                        }

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
                changed= new ArrayList<>(chefLogic.getMenu());
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
                    frame.pack();
                    JOptionPane.showMessageDialog(frame,"Piatto rimosso correttamente","Rimuovi piatto",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(frame, "Seleziona il piatto da eliminare","Selezione Piatto",JOptionPane.WARNING_MESSAGE);
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
                    Piatto modifica = MenuList.getSelectedValue();
                    String nuovo_nome = JOptionPane.showInputDialog("Modifica il nome del piatto", modifica.getNome());
                    double nuovo_prezzo = Double.parseDouble(JOptionPane.showInputDialog("Modifica il prezzo del piatto", modifica.getPrezzo()));
                    Piatto piatto_modificato = new Piatto(nuovo_nome, nuovo_prezzo);
                    chefLogic.editFood(MenuList.getSelectedValue(), piatto_modificato);
                    Edit(MenuList.getSelectedIndex(), piatto_modificato);
                    frame.pack();
                    JOptionPane.showMessageDialog(frame,"Piatto modificato correttamente","Modifica piatto",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(frame, "Seleziona il piatto da modificare","Modifica Piatto",JOptionPane.WARNING_MESSAGE);
                }

            }
        });

        /**
         * Listener per tornare alla homepage
         */
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(chefLogic.getMenu().equals(changed))){
                    //TODO Aggiornare l'array changed
                    int risposta= JOptionPane.showConfirmDialog(frame,"Non hai confermato le modifiche, intendi farlo?","Conferma modfiche",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                    if(risposta==JOptionPane.YES_OPTION){
                        chefLogic.finalizzaMenu();
                        frame.dispose();
                        chefLogic.getMenu().clear();
                        HomePage homePage = new HomePage();
                    }else{
                        frame.dispose();
                        chefLogic.getMenu().clear();
                        HomePage homePage = new HomePage();
                    }
                }else {
                    frame.dispose();
                    chefLogic.getMenu().clear();
                    HomePage homePage = new HomePage();
                }
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
        scrollBar1.add(MenuList);
        ListPanel.add(MenuList);
    }

    /**
     * Metodo per aggiungere un piatto al modello
     * @param piatto piatto da aggiungere
     */
    public void Add(Piatto piatto){
        model.addElement(piatto);
    }

    /**
     * Metodo per modificare un piatto nel modello
     * @param index indice del piatto da modificare
     * @param piatto_nuovo piatto modificato
     */
    public void Edit(int index, Piatto piatto_nuovo){
        model.set(index,piatto_nuovo);
    }

    /**
     * Metodo per eliminare un piatto dal modello
     * @param piatto piatto da eliminare
     */
    public void Delete(Piatto piatto){
        model.removeElement(piatto);
    }

}
