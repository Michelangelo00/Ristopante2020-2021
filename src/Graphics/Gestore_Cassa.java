package Graphics;

import Logic.Gestore_CassaLogic;
import Logic.Ordine;
import Logic.Piatto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;

import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;

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
                if(PiattiList.getModel() != null ){
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
        pagaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrinterJob pj = PrinterJob.getPrinterJob();
                pj.setPrintable(new BillPrintable(), gestore_cassaLogic.CreaPageFormat(pj));
                if(pj.printDialog()) {
                    try {
                        pj.print();
                        OrdiniModel.removeElement(TavoliList.getSelectedValue());
                        PiattiOrdineModel.removeAllElements();
                        Tot.setText("");
                    } catch (PrinterException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public void LoadOrdiniList(){
        for(Ordine o: gestore_cassaLogic.GetOrdini()){
            System.out.println(o.getPiatti());
            if(o.getStato()==2){
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

    public class BillPrintable implements Printable {
        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            int ris = NO_SUCH_PAGE;
            if (pageIndex == 0) {
                Graphics g2d = (Graphics2D) graphics;
                double larghezza = pageFormat.getImageableWidth();
                g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

                FontMetrics metrics = g2d.getFontMetrics(new Font("Arial", Font.BOLD, 7));
                int PiattoLunghezza = metrics.stringWidth("000000");
                int QuantitàLunghezza = metrics.stringWidth("00000");
                int PrezzoLunghezza = metrics.stringWidth("000000");
                int prodLength = (int) larghezza - PiattoLunghezza - QuantitàLunghezza - PrezzoLunghezza - 17;

                int productPosition = 0;
                int discountPosition = prodLength + 5;
                int pricePosition = 10;
                int qtyPosition = pricePosition + PrezzoLunghezza + 4;
                int amtPosition = qtyPosition + QuantitàLunghezza;

                try {
                    int y = 20;
                    int yShift = 10;
                    int headerRectHeight = 10;
                    int headerRectHeighta = 40;


                    double sum = 0;

                    g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
                    g2d.drawString("-------------------------------------", 12, y);
                    y += yShift;
                    g2d.drawString("        Ricevuta Ristopante        ", 12, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 12, y);
                    y += headerRectHeight;

                    g2d.drawString("-------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString("   Piatto                 Prezzo   ", 10, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 10, y);
                    y += headerRectHeight;
                    for(int i=0; i<PiattiOrdineModel.getSize();i++){
                        g2d.drawString(" " + PiattiOrdineModel.get(i).getNome() + "                     ",10, y);
                        g2d.drawString("     "+PiattiOrdineModel.get(i).getPrezzo()+"€",130,y);
                        sum+=PiattiOrdineModel.get(i).getPrezzo();
                        y += yShift;
                    }
                    g2d.drawString("-------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString(" Totale: " + sum + "               ", 10, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString("           Codice Lotteria         ", 10, y);
                    y += yShift;
                    g2d.drawString("             03111111111             ", 10, y);
                    y += yShift;
                    g2d.drawString("*************************************", 10, y);
                    y += yShift;
                    g2d.drawString("    Contatti: ristorante@gmail.com   ", 10, y);
                    y += yShift;
                    g2d.drawString("*************************************", 10, y);
                    y += yShift;
                    g2d.drawString("          GRAZIE E ARRIVEDERCI   ", 10, y);
                    y += yShift;
                    g2d.drawString("*************************************", 10, y);
                    y += yShift;
                } catch (Exception r) {
                    r.printStackTrace();
                }
                ris = PAGE_EXISTS;
            }
            return ris;
        }
    }
}
