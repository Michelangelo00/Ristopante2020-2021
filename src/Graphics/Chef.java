package Graphics;

import Logic.ChefLogic;
import Logic.Piatto;
import static Logic.Piatto.Type.*;
import javax.swing.*;
import java.awt.event.*;
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
    private JPanel PrimiPanel;
    private JButton indietroButton;
    private JList SecondiList;
    private JList DolciList;
    private JPanel SecondiPanel;
    private JPanel DolciPanel;
    private JList BevandeList;
    private JPanel BevandePanel;
    private JComboBox<String> TypeBox;
    private JPanel TypeBoxPanel;
    private JList<Piatto> PrimiList;
    private final DefaultListModel<Piatto> BevandeModel= new DefaultListModel<>();
    private final DefaultListModel<Piatto> PrimiModel= new DefaultListModel<>();
    private final DefaultListModel<Piatto> SecondiModel= new DefaultListModel<>();
    private final DefaultListModel<Piatto> DolciModel= new DefaultListModel<>();
    private ArrayList<Piatto> changed = new ArrayList<>(chefLogic.getMenu());

    public Chef() {
        /**
         * Costruttore della base grafica
         */
        frame = new JFrame("Chef");
        LoadMenuList();
        frame.setBounds(400, 300, 1000, 600);
        frame.setContentPane(ChefPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setVisible(true);



        /**
         * Listener per aggiungere un nuovo piatto al menù
         */
        aggiungiPiattoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ComboBoxSelection= TypeBox.getSelectedItem().toString();
                if (!(PiattoNome.getText().equals("")&&PiattoPrezzo.getText().equals(""))) {
                    if (PiattoNome.getText().equals("")) {
                        JOptionPane.showMessageDialog(frame, "Inserisci il nome del piatto!");
                    } else if (PiattoPrezzo.getText().equals("")) {
                        JOptionPane.showMessageDialog(frame, "Inserisci il prezzo del piatto!");
                    }else if (ComboBoxSelection.equals("SELEZIONA")) {
                        JOptionPane.showMessageDialog(frame, "Inserisci la tipologia del piatto!");

                    }else{
                        String nome= PiattoNome.getText();
                        Double prezzo= Double.parseDouble(PiattoPrezzo.getText());
                        Enum tipo = (Enum) TypeBox.getSelectedItem();
                        if(!chefLogic.addFood(new Piatto(nome,prezzo, (Piatto.Type) tipo))){
                            JOptionPane.showMessageDialog(frame,"Piatto gia esistente");
                            PiattoNome.setText("");
                            PiattoPrezzo.setText("");
                        }else {
                            if (tipo.toString().equals("BEVANDE")) {
                                Add(BevandeModel, new Piatto(nome, prezzo, (Piatto.Type) tipo));
                            }else if(tipo.toString().equals("PRIMI")){
                                Add(PrimiModel, new Piatto(nome, prezzo, (Piatto.Type) tipo));
                            }else if(tipo.toString().equals("SECONDI")){
                                Add(SecondiModel, new Piatto(nome, prezzo, (Piatto.Type) tipo));
                            }else if(tipo.toString().equals("DOLCI")){
                                Add(DolciModel, new Piatto(nome, prezzo, (Piatto.Type) tipo));
                            }
                            PiattoNome.setText("");
                            PiattoPrezzo.setText("");
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
                if(!(PrimiList.getSelectedValue()==null)) {
                    chefLogic.removeFood(PrimiList.getSelectedValue());
                    //Delete(MenuList.getSelectedValue());
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
                if(!(PrimiList.getSelectedValue()==null)) {
                    Piatto modifica = PrimiList.getSelectedValue();
                    String nuovo_nome = JOptionPane.showInputDialog("Modifica il nome del piatto", modifica.getNome());
                    double nuovo_prezzo = Double.parseDouble(JOptionPane.showInputDialog("Modifica il prezzo del piatto", modifica.getPrezzo()));
                    Piatto piatto_modificato= chefLogic.editFood(PrimiList.getSelectedValue(), nuovo_nome, nuovo_prezzo);
                    //Edit(MenuList.getSelectedIndex(), piatto_modificato);
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
                ConfermaBack();
            }
        });

       frame.addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent e) {
               ConfermaClose();
           }
       });

    }

    //TODO aggiornare i modelli dopo un add/edit/remove

    /**
     * metodo per caricare il modello della lista con i piatti del menù
     */
    public void LoadMenuList(){
        for (Piatto p : chefLogic.getMenu()) {
            if(p.getTipologia().equals(BEVANDE)) {
                BevandeModel.addElement(p);
            }else if(p.getTipologia().equals(PRIMI)){
                PrimiModel.addElement(p);
            }else if(p.getTipologia().equals(SECONDI)){
                SecondiModel.addElement(p);
            }else if(p.getTipologia().equals(DOLCI)) {
                DolciModel.addElement(p);
            }
        }

        TypeBox = new JComboBox(Piatto.Type.values());
        PrimiList = new JList();
        SecondiList = new JList();
        DolciList = new JList();
        BevandeList = new JList();
        PrimiList.setModel(PrimiModel);
        SecondiList.setModel(SecondiModel);
        DolciList.setModel(DolciModel);
        BevandeList.setModel(BevandeModel);
        PrimiPanel.add(PrimiList);
        SecondiPanel.add(SecondiList);
        DolciPanel.add(DolciList);
        BevandePanel.add(BevandeList);
        TypeBoxPanel.add(TypeBox);

    }

    /**
     * Metodo per aggiungere un piatto al modello
     * @param piatto piatto da aggiungere
     */
    public void Add(DefaultListModel modello, Piatto piatto){
        modello.addElement(piatto);
    }

    /**
     * Metodo per modificare un piatto nel modello
     * @param index indice del piatto da modificare
     * @param piatto_nuovo piatto modificato
     */
    public void Edit(DefaultListModel modello,int index, Piatto piatto_nuovo){
        modello.set(index,piatto_nuovo);
    }

    /**
     * Metodo per eliminare un piatto dal modello
     * @param piatto piatto da eliminare
     */
    public void Delete(DefaultListModel modello ,Piatto piatto){
        modello.removeElement(piatto);
    }

    /**
     * Metodo per salvare le modifiche in caso di ritorno alla homepage
     */
    public void ConfermaBack(){
        if(!(chefLogic.getMenu().equals(changed))){
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

    /**
     * Metodo per salvare le modifiche in caso di chiusura dell'applicazione
     */
    public void ConfermaClose(){
        if(!(chefLogic.getMenu().equals(changed))){
            int risposta= JOptionPane.showConfirmDialog(frame,"Non hai confermato le modifiche, intendi farlo?","Conferma modfiche",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            if(risposta==JOptionPane.YES_OPTION){
                chefLogic.finalizzaMenu();
                frame.dispose();
                chefLogic.getMenu().clear();
            }else{
                frame.dispose();
                chefLogic.getMenu().clear();
            }
        }else {
            frame.dispose();
            chefLogic.getMenu().clear();
        }
    }

}
