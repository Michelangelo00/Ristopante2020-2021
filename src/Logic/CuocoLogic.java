package Logic;

import java.util.ArrayList;

public class CuocoLogic {


    private Data data = new Data();
    Ordine tavolo;


    public CuocoLogic(){



    }


    public ArrayList<Ordine> GetOrdiniCuoco(){
        return data.getOrdini();
    }


}
