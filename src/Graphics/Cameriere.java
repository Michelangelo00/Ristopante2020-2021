package Graphics;

import Logic.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
         * PORCODIO
         */
        ArrayList<Piatto> menu = cameriereL.getMenu();
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
                if(ordine != null && ordine.getTavoloID() != 0){
                    Piatto piattoQ = menuList.getSelectedValue();

                    if(ordine.getPiatti().contains(piattoQ)){
                        piattoQ.setQuantita(piattoQ.getQuantita() + 1);
                        System.out.println("questo piatto esisteva gia, ne ho aggiunto un altro");
                    }
                    else{
                        cameriereL.AggiungiPiatto(ordine,piattoQ);
                        System.out.println("Aggiunto piatto: " + piattoQ + " all'ordine: " + ordine);

                        //GPX
                        dlmOrdine.addElement(piattoQ);
                    }


                }
            }
        });

        /**
         * crea un nuovo ordine con un dato numero del tavolo
         */
        creaOrdineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numeroTavolo = Integer.parseInt(JOptionPane.showInputDialog(CamerierePanel,"Inserire numero del tavolo"));
                ordine = cameriereL.CreaOrdine(numeroTavolo);
                tavoloLabel.setText("Tavolo N." + numeroTavolo); //setto grafica del numero del tavolo
                System.out.println("Creato ordine: " + ordine);
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
                    ordine.SvuotaOrdine(); // svuoto l'ordine
                    ordine.setTavoloID(0); //resetto il nuemro del tavolo
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
                    Piatto piatto = ordineList.getSelectedValue();
                    cameriereL.RimuoviPiatto(ordine,piatto);
                    System.out.println("Rimosso piatto: " + piatto + " all'ordine: " + ordine);

                    //GPX
                    dlmOrdine.removeElement(piatto);
                }
            }
        });
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                cameriereL.getMenu().clear();
                HomePage homePage= new HomePage();
            }
        });
        modificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Piatto piattoQ = ordineList.getSelectedValue();
                int nuovaQuantita = Integer.parseInt(JOptionPane.showInputDialog("Modifica quantità", piattoQ.getQuantita()));
                piattoQ.setQuantita(nuovaQuantita);
            }
        });
    }


}
