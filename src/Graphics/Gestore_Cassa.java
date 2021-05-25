package Graphics;

import Logic.Gestore_CassaLogic;
import Logic.Ordine;
import Logic.Piatto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gestore_Cassa extends JPanel{
    private Gestore_CassaLogic gestore_cassaLogic= new Gestore_CassaLogic();
    private JPanel GestoreCassaPanel;
    private JList TavoliList;
    private JPanel TavoliPanel;
    private JButton checkOutTavoloSelezionatoButton;
    private JButton pagaButton;
    private JButton indietroButton;
    private JPanel PiattiListPanel;
    private JList PiattiList;
    private JLabel Tot;
    private JFrame frame;
    private final DefaultListModel<Integer> OrdiniModel= new DefaultListModel<>();
    private final DefaultListModel<Piatto> PiattiOrdineModel= new DefaultListModel<>();


    public Gestore_Cassa(){
        frame= new JFrame("GestoreCassa");
        frame.setBounds(400, 300, 1000, 600);
        frame.setContentPane(GestoreCassaPanel);
        LoadOrdiniList();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                HomePage homePage= new HomePage();
            }
        });
        checkOutTavoloSelezionatoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(PiattiList.getModel().getSize()==0 ){
                    LoadPiattiOrdine((Integer)TavoliList.getSelectedValue());
                    PiattiList = new JList();
                    PiattiList.setModel(PiattiOrdineModel);
                    PiattiListPanel.add(PiattiList);
                    PiattiList.setVisible(true);
                    Tot.setText(String.valueOf(CalcolaTot()));
                }else{
                    JOptionPane.showMessageDialog(frame, "Piatti già caricati!");
                }

            }
        });
    }

    public void LoadOrdiniList(){
        for(Ordine o: gestore_cassaLogic.GetOrdini()){
            System.out.println(o.getPiatti());
            if(o.getStato()==1){
                OrdiniModel.addElement(o.getTavoloID());
            }
        }
        TavoliList= new JList();
        TavoliList.setModel(OrdiniModel);
        TavoliPanel.add(TavoliList);
    }

    public void LoadPiattiOrdine(int NTavolo){
        for(Ordine o: gestore_cassaLogic.GetOrdini()){
            if(o.getTavoloID()==NTavolo){
                PiattiOrdineModel.addAll(o.getPiatti());
            }
        }
        PiattiList.setVisible(false);
    }

    public double CalcolaTot(){
        double tot=0;
        for(int i=0; i<PiattiOrdineModel.getSize();i++){
            tot+=PiattiOrdineModel.get(i).getPrezzo();
        }
        return tot;
    }
}
