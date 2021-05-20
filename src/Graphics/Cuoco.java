package Graphics;

import Logic.CuocoLogic;
import Logic.Ordine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cuoco extends JPanel{
    private JPanel CuocoPanel;
    private JList ListaOrdiniTavolo;
    private JList ListaPiattiOrdine;
    private JPanel PanelListaTavoli;
    private JButton Evadi;
    private JFrame frame;
    private CuocoLogic cuoco = new CuocoLogic();
    JList<Ordine> ordini = new JList<>();
    DefaultListModel<Integer> model = new DefaultListModel<>();

    public Cuoco(){

        frame= new JFrame("Cuoco");
        frame.setSize(new Dimension(500,500));
        frame.setContentPane(CuocoPanel);

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
        for(Ordine o: cuoco.GetOrdiniCuoco()){
            if(o.getStato() == 1){
                model.addElement(o.getTavoloID());
            }
        }
        ListaOrdiniTavolo = new JList();
        ListaOrdiniTavolo.setModel(model);
        PanelListaTavoli.add(ListaOrdiniTavolo);
    }



}
