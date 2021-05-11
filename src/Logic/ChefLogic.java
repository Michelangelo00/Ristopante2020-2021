package Logic;

import java.io.*;
import java.util.*;

public class ChefLogic {
    private Data data = new Data();
    static int numero_pietanze=0;

    public void addFood(String food, Double price){
        data.getMenu().add(new Piatto(food,price));
        numero_pietanze++;
    }

    public void finalizzaMenu(){
        if(numero_pietanze!=0) {
            data.WriteToFile();
            numero_pietanze=0;
        }
    }


    public void removeFood(Piatto piatto){
        Iterator iterator = data.getMenu().iterator();
        while(iterator.hasNext()){
            if(iterator.next()==piatto){
                iterator.remove();
            }
        }
    }

    public void editFood(){}

}
