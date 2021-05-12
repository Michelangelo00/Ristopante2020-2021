package Logic;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class Data {

    private static File inputfile= new File("menu.txt");
    private static ArrayList<Piatto> menu= new ArrayList<>();
    private static ArrayList<Ordine> ordini = new ArrayList<>();

    public Data(){
        loadMenu();
    }


    public  void loadMenu(){
            try {
                BufferedReader reader= new BufferedReader(new FileReader(inputfile));
                String linea = reader.readLine();

                while(linea!= null){
                    String[] record = linea.split(",");
                    String Nome = record[0].trim();
                    Double Prezzo = Double.parseDouble(record[1].trim());
                    Piatto nuovo= new Piatto(Nome,Prezzo);
                    if(!menu.contains(nuovo.getNome())) {
                        menu.add(nuovo);
                    }
                    linea= reader.readLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
           System.out.println(getMenu());
    }

    public static ArrayList<Piatto> getMenu() {
        return menu;
    }

    public static void WriteToFile(){

        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(inputfile,false));
            for(Piatto h: menu){
                out.write(h.getNome()+","+h.getPrezzo());
                out.write("\n");
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadOrdini(Ordine ordine){
        ordini.add(ordine);
    }

    public ArrayList<Ordine> getOrdini() {
        return ordini;
    }

    public static void RemovePiatto(Piatto piatto){
        menu.remove(piatto);
    }
}


