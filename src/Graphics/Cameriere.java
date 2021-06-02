package Graphics;

import Logic.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Cameriere extends JPanel{
    private JPanel CamerierePanel;
    private JButton aggiungiPiattoButton;
    private JButton creaOrdineButton;
    private JButton inviaOrdineButton;
    private JList<Piatto> menuList;
    private JList<Piatto> ordineList;
    private JLabel menuText;
    private JButton rimuoviPiattoButton;
    private JButton indietroButton;
    private JLabel tavoloLabel;
    private JButton modificaButton;
    private JFrame frame;

    private Ordine ordine; //inizializzo ordine

    public Cameriere(){

        CameriereLogic cameriereL = new CameriereLogic(); //creo istanza di cameriere logic


        /**
         * costruisco la base grafica
         */
        frame= new JFrame("Cameriere");
        frame.setBounds(400, 300, 1000, 600);
        frame.setContentPane(CamerierePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        /**
         * popola l'array list menu
         */

        /* ORIGINALE
        ArrayList<Piatto> menu = cameriereL.getMenu();
        System.out.println(menu);
        DefaultListModel dlmMenu = new DefaultListModel();
        for(Piatto piatto : menu){
            dlmMenu.addElement(piatto);
        }
        this.menuList.setModel(dlmMenu);

         */
        ArrayList<Piatto> menu = cameriereL.getMenu();
        Collections.sort(menu,(a1,a2) -> a1.getTipologia().compareTo(a2.getTipologia())); //GUARDA IN ALTO, CI SONO IO
        System.out.println(menu);
        DefaultListModel dlmMenu = new DefaultListModel();
        for(Piatto piatto : menu){
            dlmMenu.addElement(piatto);
        }
        this.menuList.setModel(dlmMenu);


        /**
         * dlm lista dell'ordine
         */
        DefaultListModel dlmOrdine = new DefaultListModel();
        this.ordineList.setModel(dlmOrdine);

        /**
         * aggiunge un piatto all'ordine
         */
        aggiungiPiattoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ordine != null && ordine.getStato() == 0){
                    Piatto piattoQ = new Piatto(menuList.getSelectedValue());

                    if(ordine.contienePiatto(piattoQ)){
                        JOptionPane.showMessageDialog(frame, "Questo piatto é già stato inserito","Piatto Inserito",JOptionPane.WARNING_MESSAGE);
                    }
                    else{
                        cameriereL.AggiungiPiatto(ordine,piattoQ);
                        System.out.println("Aggiunto piatto: " + piattoQ + " all'ordine: " + ordine);

                        //GPX
                        dlmOrdine.addElement(piattoQ);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame, "Impossibile aggiungere un piatto ad un ordine inesistente","Ordine inesistente",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        /**
         * crea un nuovo ordine con un dato numero del tavolo
         */
        creaOrdineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String stringaTavolo = JOptionPane.showInputDialog(CamerierePanel,"Inserire numero del tavolo");
                if(!stringaTavolo.isEmpty()){
                    int numeroTavolo = Integer.parseInt(stringaTavolo);
                    ordine = cameriereL.CreaOrdine(numeroTavolo);
                    tavoloLabel.setText("Tavolo N." + numeroTavolo); //setto grafica del numero del tavolo
                    System.out.println("Creato ordine: " + ordine);
                }
                else{
                    System.out.println("Famm bocc");
                }
            }
        });

        /**
         * finalizza l'ordine compilato e svuota la GUI
         */
        inviaOrdineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ordine != null && ordine.getTavoloID() != 0){
                    cameriereL.FinalizzaOrdine(ordine); //invio l'ordine al cuoco
                    dlmOrdine.clear(); //svuoto la GUI
                    //ordine.SvuotaOrdine(); // svuoto l'ordine
                    //ordine.setTavoloID(0); //resetto il numero del tavolo
                    tavoloLabel.setText("Tavolo N."); //resetto la grafica del numero tavolo
                    System.out.println("Ordine: " + ordine + " finalizzato");
                }
            }
        });

        /**
         * rimuove un piatto selezionato dall'ordine
         */
        rimuoviPiattoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ordine != null && ordine.getTavoloID() != 0){
                    Piatto piattoQ = ordineList.getSelectedValue();

                    cameriereL.RimuoviPiatto(ordine,piattoQ);
                    System.out.println("Rimosso piatto: " + piattoQ + " all'ordine: " + ordine);

                    //GPX
                    dlmOrdine.removeElement(piattoQ);
                }
            }
        });
        /**
         * torna al menu principale
         */
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                cameriereL.getMenu().clear();
                HomePage homePage= new HomePage();
            }
        });
        /**
         * modifica la quantita di un piatto dell'ordinazione
         */
        modificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Piatto piattoQ = ordineList.getSelectedValue();
                int nuovaQuantita = Integer.parseInt(JOptionPane.showInputDialog("Modifica quantità", piattoQ.getQuantita()));

                if(nuovaQuantita > 0 && nuovaQuantita < 100){
                    piattoQ.setQuantita(nuovaQuantita);
                    CamerierePanel.repaint();
                }
                else{
                    System.out.println("mi dispiace ci hai sgarato");
                }
            }
        });


    }

    public boolean isParsable(String stringToConvert){

        for(int i = 0; i < stringToConvert.length(); i++){
            //if(stringToConvert.charAt(i).)
        }


        return true;
    }
}
