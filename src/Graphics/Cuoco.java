package Graphics;

import Logic.CuocoLogic;
import Logic.Ordine;
import Logic.Piatto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Arrays;


public class Cuoco extends JPanel{
    private JPanel CuocoPanel;
    private JList ListaOrdiniTavolo;
    private JPanel PanelListaTavoli;
    private JButton Evadi;
    private JButton mostraPiatti;
    private JPanel CheckPanel;
    private JButton EvadiPiatto;
    private JButton indietroButton;
    private ArrayList<JCheckBox> cb;
    private JFrame frame;
    private CuocoLogic cuoco = new CuocoLogic();
    private int count_box=0;
    JList<Ordine> ordini = new JList<>();
    DefaultListModel<Integer> model = new DefaultListModel<>();
    DefaultListModel<Piatto> modelPiatto = new DefaultListModel<>();
    private ActionListener actionListener;



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
                        //cuoco.EvadiTavolo(or);
                    }
                }
                model.removeElement(ListaOrdiniTavolo.getSelectedValue());
            }
        });

        mostraPiatti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPiatti( (Integer) ListaOrdiniTavolo.getSelectedValue());
                for(int j=0;j<cb.size();j++){
                    cb.get(j).setVisible(true);
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


        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homePage= new HomePage();
            }
        });

        actionListener= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<cb.size();i++) {
                    if (cb.get(i).isSelected()) {
                        count_box--;
                        cb.remove(i);
                    }
                }
                System.out.println(count_box);
                if(count_box==0){
                    int risposta= JOptionPane.showConfirmDialog(frame,"Ordine del tavolo "+ListaOrdiniTavolo.getSelectedValue()+" è stato completato, vuoi eliminarlo?","Elimina ordine",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                    if(risposta==JOptionPane.YES_OPTION){
                        cuoco.EvadiTavolo((Integer) ListaOrdiniTavolo.getSelectedValue());
                        cb.clear();
                        CheckPanel.removeAll();
                        System.out.println(cb);
                        model.removeElement(ListaOrdiniTavolo.getSelectedValue());
                        modelPiatto.removeAllElements();
                    }

                }
            }

        };
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

        cb= new ArrayList<JCheckBox>(modelPiatto.getSize());
        for(int i=0; i<modelPiatto.getSize();i++){
            cb.add( new JCheckBox(modelPiatto.get(i).getNome()));
            cb.get(i).addActionListener(actionListener);
            count_box+=1;
            cb.get(i).setVisible(false);
        }
        for(int i=0; i<modelPiatto.getSize();i++){
            CheckPanel.add(cb.get(i));
        }
    }


}
