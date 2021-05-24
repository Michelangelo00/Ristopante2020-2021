package Logic;

import java.util.ArrayList;

public class CuocoLogic {


    private Data data = new Data();
    Ordine tavolo;
    private Ordine piatti = new Ordine(tavolo.getTavoloID());


    public CuocoLogic(){



    }


    public ArrayList<Ordine> GetOrdiniCuoco(){
        return data.getOrdini();
    }

    public ArrayList<Piatto> GetPiattiOrdine(){ return piatti.getPiatti();}



}
