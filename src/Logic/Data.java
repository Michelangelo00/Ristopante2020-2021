package Logic;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 */

public class Data {

    private static File inputfile= new File("/home/lollof00/IdeaProjects/Ristopante2020-2021/menu.txt");
    private static ArrayList<Piatto> menu= new ArrayList<>(); // ArrayList di piatti che rappresentano il menù
    private ArrayList<Ordine> ordini = new ArrayList<>(); //

    /**
     * Costruttore di Data per caricare inizialmente il menù
     */
    public Data(){
        loadMenu();
    }

    /**
     * metodo per leggere un file contenente il menù e salvarlo in un array di piatti
     */
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
    }

    public static ArrayList<Piatto> getMenu() {
        return menu;
    }

    /**
     * metodo per scrivere sul file le modifiche effettuate dallo Chef sul menù
     */
    public void WriteToFile(){

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

    public void loadOrdini(Ordine ordine){
        ordini.add(ordine);
    }

    public ArrayList<Ordine> getOrdini() {
        return ordini;
    }

}


