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
                pj.setPrintable(new BillPrintable(), CreaPageFormat(pj));
                try{
                    pj.print();
                }
                catch (PrinterException ex){
                    ex.printStackTrace();
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

    public PageFormat CreaPageFormat(PrinterJob pj){
        PageFormat pf = new PageFormat();
        Paper paper = pf.getPaper();
        double centroAltezza = 8.0;
        double sopraAltezza = 2.0;
        double bassoAltezza= 2.0;
        double larghezza = (8*0.393600787)*72d;
        double altezza= ((centroAltezza+sopraAltezza+bassoAltezza)*0.393600787)*72d;
        paper.setSize(larghezza,altezza);
        paper.setImageableArea(
                0,
                10,
                larghezza,
                altezza-((1*0.393600787)*72d)
        );
        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);
        return pf;
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
                int pricePosition = discountPosition + 10;
                int qtyPosition = pricePosition + PrezzoLunghezza + 4;
                int amtPosition = qtyPosition + QuantitàLunghezza;

                try {
                    int y = 20;
                    int yShift = 10;
                    int headerRectHeight = 15;
                    int headerRectHeighta = 40;

                    String pn1a = PiattiOrdineModel.get(0).getNome();
                    String pn2a = PiattiOrdineModel.get(1).getNome();

                    double pp1a = PiattiOrdineModel.get(0).getPrezzo();
                    double pp2a = PiattiOrdineModel.get(1).getPrezzo();
                    double sum = pp1a + pp2a;

                    g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
                    g2d.drawString("-------------------------------------", 12, y);
                    y += yShift;
                    g2d.drawString("      Restaurant Bill Receipt        ", 12, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 12, y);
                    y += headerRectHeight;

                    g2d.drawString("-------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString(" Food Name                 T.Price   ", 10, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 10, y);
                    y += headerRectHeight;
                    g2d.drawString(" " + pn1a + "                  " + pp1a + "  ", 10, y);
                    y += yShift;
                    g2d.drawString(" " + pn2a + "                  " + pp2a + "  ", 10, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString(" Total amount: " + sum + "               ", 10, y);
                    y += yShift;
                    g2d.drawString("-------------------------------------", 10, y);
                    y += yShift;
                    g2d.drawString("          Free Home Delivery         ", 10, y);
                    y += yShift;
                    g2d.drawString("             03111111111             ", 10, y);
                    y += yShift;
                    g2d.drawString("*************************************", 10, y);
                    y += yShift;
                    g2d.drawString("    THANKS TO VISIT OUR RESTUARANT   ", 10, y);
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
