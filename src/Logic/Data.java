package Logic;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class Data {
    private File inputfile= new File("/home/lollof00/IdeaProjects/Ristopante2020-2021/menu.txt");
    private ArrayList<Piatto> menu= new ArrayList<>();

    private ArrayList<Ordine> ordini = new ArrayList<>();

    public Data(){
        loadMenu();
    }


    public void loadMenu(){
            try {
                BufferedReader reader= new BufferedReader(new FileReader(inputfile));
                String linea = reader.readLine();

                while(linea!= null){
                    String[] record = linea.split(",");
                    String Nome = record[0].trim();
                    Double Prezzo = Double.parseDouble(record[1].trim());
                    menu.add(new Piatto(Nome,Prezzo));
                    linea= reader.readLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public ArrayList<Piatto> getMenu() {
        return menu;
    }

    public void WriteToFile(){

        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(inputfile,true));
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


