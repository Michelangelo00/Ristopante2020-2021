package Graphics;

import Logic.CuocoLogic;
import Logic.Ordine;
import Logic.Piatto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe che implementa graficamente la figura del cuoco
 */
public class Cuoco extends JPanel{
    private JPanel CuocoPanel;
    private JList ListaOrdiniTavolo;
    private JPanel PanelListaTavoli;
    private JButton Evadi;
    private JButton mostraPiatti;
    private JPanel CheckPanel;
    private JButton EvadiPiatto;
    private JButton indietroButton;
    private ArrayList<ArrayList<JCheckBox>> cb= new ArrayList<ArrayList<JCheckBox>>();
    private JFrame frame;
    private CuocoLogic cuoco = new CuocoLogic();
    private int count_box=0;
    DefaultListModel<Integer> model = new DefaultListModel<>();
    DefaultListModel<Piatto> modelPiatto = new DefaultListModel<>();
    private ActionListener actionListener;



    public Cuoco() {
        frame = new JFrame("Cuoco");
        frame.setBounds(400, 300, 1000, 600);
        frame.setContentPane(CuocoPanel);
        CheckPanel.setLayout(new BoxLayout(CheckPanel,BoxLayout.Y_AXIS));

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
                    for(int i=0;i<cb.get(j).size()-1;i++){
                        cb.get(j).get(i).setVisible(true);
                    }
                }
                modelPiatto.removeAllElements();
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
                    for(int j=0;j<cb.get(i).size();j++){
                        if(cb.get(i).get(j).isSelected()){
                            System.out.println(cb.get(i).get(j).getText()+cb.get(i).size());
                            cb.get(i).remove(j);

                        }
                    }

                    if(cb.get(i).size()==1){
                        int risposta= JOptionPane.showConfirmDialog(frame,"Ordine del tavolo "+cb.get(i).get(0).getText()+" è stato completato, vuoi eliminarlo?","Elimina ordine",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                        if(risposta==JOptionPane.YES_OPTION){
                            cuoco.EvadiTavolo( cb.get(i).get(0).getText());
                            cb.get(i).clear();
                            Pulisci(cb.get(i));
                            model.removeElement(ListaOrdiniTavolo.getSelectedValue());
                            modelPiatto.removeAllElements();

                        }
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
        ArrayList<JCheckBox> check = new ArrayList<>();
        String tav = null;
        for(Ordine o: cuoco.GetOrdiniCuoco()){
            if(o.getTavoloID() == NTavolo){
                for(Piatto p: o.getPiatti()){
                    modelPiatto.addElement(p);
                }
                tav=String.valueOf(o.getTavoloID());

            }
        }

        for(int i=0; i<modelPiatto.getSize();i++){
            JCheckBox c = new JCheckBox();
            c.setText(modelPiatto.get(i).getNome()+" x"+modelPiatto.get(i).getQuantita());
            check.add(c);
            check.get(i).addActionListener(actionListener);
            check.get(i).setVisible(false);
        }
        CheckPanel.add(new JLabel("Tavolo "+ListaOrdiniTavolo.getSelectedValue()));
        for(int i=0; i<check.size();i++){
            CheckPanel.add(check.get(i));
        }
        CheckPanel.add(new JLabel("-------------------------------"));
        JCheckBox tavolo = new JCheckBox(tav);
        tavolo.setVisible(false);
        check.add(tavolo);
        cb.add(check);
    }

    public void Pulisci(ArrayList<JCheckBox> arr){
        for(int i=0;i<arr.size();i++){
            CheckPanel.remove(arr.get(i));
        }
    }

}
