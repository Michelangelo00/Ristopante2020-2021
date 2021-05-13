package Graphics;

import Logic.*;

import javax.swing.*;
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
    private JFrame frame;

    private Ordine ordine; //inizializzo ordine

    public Cameriere(){

        CameriereLogic cameriereL = new CameriereLogic(); //creo istanza di cameriere logic



        /**
         * costruisco la base grafica
         */
        frame= new JFrame("Cameriere");
        frame.setSize(new Dimension(500,500));
        frame.setContentPane(CamerierePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        //PROVA INPUT MENU/////////////////////////////////////////////////////////
        /*ArrayList<Piatto> menu = da.getMenu();
        DefaultListModel dlmMenu = new DefaultListModel();
        for(Piatto piatto : menu){
            dlmMenu.addElement(piatto);
        }
        this.menuList.setModel(dlmMenu);*/

        ////////////////////////////////////////////////////////////////////////////

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
                    Piatto piatto = menuList.getSelectedValue();
                    cameriereL.AggiungiPiatto(ordine,piatto);
                    System.out.println("Aggiunto piatto: " + piatto + " all'ordine: " + ordine);

                    //GPX
                    dlmOrdine.addElement(piatto);
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
                    ordine.setTavoloID(0);
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
                HomePage homePage= new HomePage();
            }
        });
    }


}
