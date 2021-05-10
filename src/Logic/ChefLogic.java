package Logic;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChefLogic {
    //private Scanner in = new Scanner(System.in);
    //private String file = in.nextLine();
    private HashMap<String,Double> menu = new HashMap<>();
    private File inputfile= new File("/home/lollof00/IdeaProjects/Ristopante2020-2021/menu.txt");
    static int numero_pietanze=0;

    public void addFood(String food, Double price){
        if(!menu.containsKey(food)){
            menu.put(food,price);
        }
    }

    public void WriteToFile(){
        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(inputfile,true));
            for(Map.Entry<String, Double> h: menu.entrySet()){
                out.write("/*"+(numero_pietanze++)+"*/"+"Pietanza: "+h.getKey()+" Prezzo: "+h.getValue());
                out.write("\n");
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void GetMenu()  {
        try {
            BufferedReader in = new BufferedReader(new FileReader(inputfile));
            System.out.println(in.readLine().toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void removeFood(String food, double price){}

}
