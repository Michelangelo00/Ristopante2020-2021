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
    private JButton mostraPiatti;
    private JPanel CheckPanel;
    private JButton EvadiPiatto;
    private JCheckBox cb[];
    private JCheckBox cb2[]= new JCheckBox[10];
    private JFrame frame;
    private CuocoLogic cuoco = new CuocoLogic();
    JList<Ordine> ordini = new JList<>();
    DefaultListModel<Integer> model = new DefaultListModel<>();
    DefaultListModel<Piatto> modelPiatto = new DefaultListModel<>();



    public Cuoco() {
        frame = new JFrame("Cuoco");
        frame.setBounds(400, 300, 1000, 600);
        frame.setContentPane(CuocoPanel);

        loadTavoli();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);



        Evadi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Ordine or: cuoco.GetOrdiniCuoco()){
                    if ((Integer)ListaOrdiniTavolo.getSelectedValue() == or.getTavoloID()) {
                        or.setStato(2);
                    }
                }
                model.removeElement(ListaOrdiniTavolo.getSelectedValue());

            }
        });

        mostraPiatti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPiatti( (Integer) ListaOrdiniTavolo.getSelectedValue());
                for(int j=0;j<cb.length;j++){
                    cb[j].setVisible(true);
                }

            }
        });

        EvadiPiatto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component checkBox[] = CheckPanel.getComponents();
                for(Component c : checkBox){
                    if(((JCheckBox)c).isSelected()){
                        CheckPanel.remove(c);
                    }
                }
                CheckPanel.revalidate();
                CheckPanel.repaint();
            }
        });
    }

    public void loadTavoli(){
        for(Ordine o: cuoco.GetOrdiniCuoco()) {
            if (o.getStato() == 1) {
                model.addElement(o.getTavoloID());
            }
        }
        ListaOrdiniTavolo = new JList();
        ListaOrdiniTavolo.setModel(model);
        PanelListaTavoli.add(ListaOrdiniTavolo);
    }



    public void loadPiatti(int NTavolo){
        for(Ordine o: cuoco.GetOrdiniCuoco()){
            if(o.getTavoloID() == NTavolo){
                for(Piatto p: o.getPiatti()){
                    modelPiatto.addElement(p);
                }
            }
        }

        cb= new JCheckBox[modelPiatto.getSize()];
        for(int i=0; i<modelPiatto.getSize();i++){
            cb[i]= new JCheckBox(modelPiatto.get(i).getNome());
            cb[i].setVisible(false);
        }
        for(int i=0; i<modelPiatto.getSize();i++){
            CheckPanel.add(cb[i]);
        }
    }

}
