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
    private JList<Piatto> list1;
    private JFrame frame;

    private Ordine ordine;
    private int numeroTavolo = 1; // variabile temporaneamente hardcoded successivamente passata da un textbox

    public Cameriere(){

        CameriereLogic cameriereL = new CameriereLogic(); //creo istanza di cameriere logic
        Data data = new Data();



        /**
         * costruisco la base grafica
         */
        frame= new JFrame("Cameriere");
        frame.setSize(new Dimension(500,500));
        frame.setContentPane(CamerierePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        //PROVA INPUT MENU/////////////////////////////////////////////////////////

        ArrayList<Piatto> menu = new ArrayList<>();
        menu.add(new Piatto("carbonara", 12.30));
        menu.add(new Piatto("gricia", 10.50));
        menu.add(new Piatto("aglio e oglio", 9.00));
        menu.add(new Piatto("amatriciana", 11.90));
        DefaultListModel dlm = new DefaultListModel();
        //dlm.addElement(data.getMenu());
        for(Piatto piatto : menu){
            dlm.addElement(piatto);
        }
        list1.setModel(dlm);

        ////////////////////////////////////////////////////////////////////////////


        /**
         * listener per aggiungere un piatto all'ordine
         */
        aggiungiPiattoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ordine != null){
                    Piatto piatto = new Piatto("carbonara", 12.50);
                    cameriereL.AggiungiPiatto(ordine,piatto); // paramentro temporaneamente hardcoded, successivamente passata da ???
                    System.out.println("Aggiunto piatto: " + piatto + " all'ordine: " + ordine);
                }
            }
        });

        /**
         * listener per creare un nuovo ordine
         */
        creaOrdineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordine = cameriereL.CreaOrdine(numeroTavolo);
                System.out.println("Creato ordine: " + ordine);
            }
        });

        /**
         * listener per finalizzare l'ordine
         */
        inviaOrdineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cameriereL.FinalizzaOrdine(ordine);
                System.out.println("Ordine: " + ordine + " finalizzato");
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
