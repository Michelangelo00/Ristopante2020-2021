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
    private JButton Evadi;
    private JButton mostraPiatti;
    private JPanel CheckPanel;
    private JButton EvadiPiatto;
    private JButton indietroButton;
    private ArrayList<ArrayList<JCheckBox>> cb= new ArrayList<ArrayList<JCheckBox>>();
    private JFrame frame;
    private CuocoLogic cuoco = new CuocoLogic();
    private int count_box=0;
    private ActionListener actionListener;



    public Cuoco() {
        frame = new JFrame("Cuoco");
        frame.setBounds(400, 300, 1000, 600);
        frame.setContentPane(CuocoPanel);
        CheckPanel.setLayout(new BoxLayout(CheckPanel,BoxLayout.Y_AXIS));


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);



        /*Evadi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Ordine or: cuoco.GetOrdiniCuoco()){
                    if ((Integer)ListaOrdiniTavolo.getSelectedValue() == or.getTavoloID()) {
                        //cuoco.EvadiTavolo(or);
                    }
                }
                model.removeElement(ListaOrdiniTavolo.getSelectedValue());
            }
        });*/

        mostraPiatti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //loadPiatti( (Integer) ListaOrdiniTavolo.getSelectedValue());
                for(int j=0;j<cb.size();j++){
                    for(int i=0;i<cb.get(j).size()-1;i++){
                        cb.get(j).get(i).setVisible(true);
                    }
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
                    for(int j=0;j<cb.get(i).size();j++){
                        System.out.println(cb.get(i).get(j).getText());
                        if(cb.get(i).get(j).isSelected()){
                            //System.out.println(cb.get(i).get(j).getText());
                            //CheckPanel.remove(cb.get(i).get(j));
                            //cb.get(i).remove(j);
                            cb.get(i).get(j).setEnabled(false);
                            //System.out.println("caccioddio  "+cb.get(i).get(j).isEnabled());
                        }
                    }
                    boolean contains=Check(cb.get(i));
                    System.out.println(contains);
                    if(contains){
                        int risposta= JOptionPane.showConfirmDialog(frame,"Ordine del tavolo "+cb.get(i).get(0).getText()+" è stato completato, vuoi eliminarlo?","Elimina ordine",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                        if(risposta==JOptionPane.YES_OPTION){
                            cuoco.EvadiTavolo( cb.get(i).get(0).getText());

                            frame.revalidate();
                            frame.repaint();
                        }
                    }

                }
            }

        };

        loadPiatti();
    }




    public void loadPiatti(){
        String tav;
        for(Ordine o: cuoco.GetOrdiniCuoco()) {
            if (o.getStato() == 1) {
                ArrayList<JCheckBox> check = new ArrayList<>();
                tav = String.valueOf(o.getTavoloID());
                CheckPanel.add(new JLabel("Tavolo " + tav));
                for (int i = 0; i < o.getPiatti().size(); i++) {
                    JCheckBox c = new JCheckBox();
                    c.setText(o.getPiatti().get(i).getNome() + " x" + o.getPiatti().get(i).getQuantita());
                    check.add(c);
                    check.get(i).addActionListener(actionListener);
                    CheckPanel.add(c);
                    check.get(i).setVisible(true);
                }
                CheckPanel.add(new JLabel("-------------------------------"));
                JCheckBox tavolo = new JCheckBox(tav);
                tavolo.setVisible(false);
                check.add(tavolo);
                cb.add(check);
            }
        }

    }



    public void Pulisci(ArrayList<JCheckBox> arr){
        for(int i=0;i<arr.size();i++){
            CheckPanel.removeAll();
        }
    }

    public boolean Check(ArrayList<JCheckBox> arr){
        boolean ris=false;
        for (int i = 0; i < arr.size()-1; i++) {
            if(!(arr.get(i).isEnabled())){
                ris=true;
            }else{
                ris=false;
            }
        }
        return ris;
    }

}
