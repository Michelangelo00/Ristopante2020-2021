package Graphics;

import Logic.CuocoLogic;
import Logic.Ordine;
import Logic.Piatto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cuoco extends JPanel{
    private JPanel CuocoPanel;
    private JList ListaOrdiniTavolo;
    private JPanel PanelListaTavoli;
    private JButton Evadi;
    private JPanel PanelCheckBox;
    private JCheckBox CheckPiatti;
    private JFrame frame;
    private CuocoLogic cuoco = new CuocoLogic();
    JList<Ordine> ordini = new JList<>();
    DefaultListModel<Integer> model = new DefaultListModel<>();
    DefaultListModel<Piatto> modelPiatto = new DefaultListModel<>();

    public Cuoco(){

        frame= new JFrame("Cuoco");
        frame.setSize(new Dimension(500,500));
        frame.setContentPane(CuocoPanel);




        CheckPiatti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        loadTavoli();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Evadi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.removeElement(ListaOrdiniTavolo.getSelectedValue());
            }
        });

    }

    public void loadTavoli(){
        for(Ordine o: cuoco.GetOrdiniCuoco()) {
            if (o.getStato() == 1) {
                model.addElement(o.getTavoloID());
                for (Piatto p : cuoco.GetPiattiOrdine()) {
                    modelPiatto.addElement(p);
                }
            }
        }
        ListaOrdiniTavolo = new JList();
        ListaOrdiniTavolo.setModel(model);
        PanelListaTavoli.add(ListaOrdiniTavolo);
        CheckPiatti = new JCheckBox();
        CheckPiatti.setModel((ButtonModel) modelPiatto);
        PanelCheckBox.add(CheckPiatti);
    }



}
