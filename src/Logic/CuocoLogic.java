package Logic;

import java.util.ArrayList;

public class CuocoLogic {


    private Data data = new Data();


    public ArrayList<Ordine> GetOrdiniCuoco(){
        return data.getOrdini();
    }

    public void EvadiTavolo(int Tavolo){
        for(int i=0;i<data.getOrdini().size();i++){
            if(data.getOrdini().get(i).getTavoloID()==Tavolo){
                data.getOrdini().get(i).setStato(2);
                break;
            }
        }
    }


}
