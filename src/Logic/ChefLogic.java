package Logic;

import java.util.*;

public class ChefLogic {
    private static Data data = new Data();
    static int numero_pietanze=0;


    public void addFood(String food, Double price){
        Piatto nuovo= new Piatto(food,price);
        if(!Data.getMenu().contains(nuovo)){
            Data.getMenu().add(nuovo);
            numero_pietanze++;
        }
    }

    public void finalizzaMenu(){
            data.WriteToFile();
            numero_pietanze=0;
    }


    public static void removeFood(Piatto piatto){
        Data.getMenu().remove(piatto);
        System.out.println(Data.getMenu());
    }

    public void editFood(){}

    public ArrayList<Piatto> getMenu(){
        return Data.getMenu();
    }
}
