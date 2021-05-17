package Logic;

import Graphics.Gestore_Cassa;

import java.util.ArrayList;

public class Gestore_CassaLogic {
    private Data data = new Data();

    public Gestore_CassaLogic(){

    }

    public ArrayList<Ordine> GetOrdini(){
        return data.getOrdini();
    }

}
