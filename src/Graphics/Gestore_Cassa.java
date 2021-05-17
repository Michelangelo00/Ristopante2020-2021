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
    private JTextField TotField;
    private JButton indietroButton;
    private JPanel PiattiListPanel;
    private JList PiattiList;
    private JFrame frame;
    private final DefaultListModel<Integer> OrdiniModel= new DefaultListModel<>();
    private final DefaultListModel<Piatto> PiattiOrdineModel= new DefaultListModel<>();


    public Gestore_Cassa(){
        frame= new JFrame("GestoreCassa");
        frame.setSize(new Dimension(500,500));
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
                LoadPiattiOrdine((Integer) TavoliList.getSelectedValue());
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
                for(Piatto p: o.getPiatti()){
                    PiattiOrdineModel.addElement(p);
                }
            }
        }
        PiattiList = new JList();
        PiattiList.setModel(PiattiOrdineModel);
        PiattiListPanel.add(PiattiList);
    }
}
