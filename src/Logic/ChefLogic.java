package Logic;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChefLogic {
    private Data data = new Data();
    static int numero_pietanze=0;

    public void addFood(String food, Double price){
        data.getMenu().add(new Piatto(food,price));
    }

    public void finalizzaMenu(){
        data.WriteToFile();
    }


    public void removeFood(String food, double price){}

}
