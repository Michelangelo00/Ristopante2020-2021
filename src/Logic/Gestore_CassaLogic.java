package Logic;

import Graphics.Gestore_Cassa;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

public class Gestore_CassaLogic {
    private Data data = new Data();

    public Gestore_CassaLogic(){

    }

    public ArrayList<Ordine> GetOrdini(){
        return data.getOrdini();
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
                20,
                10,
                larghezza,
                altezza-((1*0.393600787)*72d)
        );
        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);
        return pf;
    }



}
