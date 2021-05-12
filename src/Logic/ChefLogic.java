package Logic;

import java.util.*;

/**
 *classe che implementa la figura dello chef con (aggiungere, rimuovere, modificare piatti del menù)
 */

public class ChefLogic {
    private static Data data = new Data();


    /**
     * metodo per aggiungere un nuovo piatto al menù
     * @param food nome del piatto da aggiungere
     * @param price prezzo del piatto da aggiungere
     */
    public static void addFood(String food, Double price){
        Piatto nuovo= new Piatto(food,price);
        if(!Data.getMenu().contains(nuovo)){
            Data.getMenu().add(nuovo);
        }
    }

    /**
     * metodo per confermare tutte le modifiche effettuate dallo chef
     */
    public void finalizzaMenu(){
            data.WriteToFile();
    }

    /**
     * metodo per rimuovere un piatto (se esiste) dal menù
     * @param piatto piatto da rimuovere
     */
    public static void removeFood(Piatto piatto){
        if (Data.getMenu().contains(piatto)){
            Data.getMenu().remove(piatto);
        }
    }

    /**
     * metodo per modificare un piatto (se esiste) nel menù
     * @param piatto_vecchio vecchio piatto da modificare
     * @param piatto_nuovo nuovo piatto modificato
     */
    public void editFood(Piatto piatto_vecchio, Piatto piatto_nuovo){
        if(Data.getMenu().contains(piatto_vecchio)){
            int index= Data.getMenu().indexOf(piatto_vecchio);
            Data.getMenu().set(index,piatto_nuovo);
        }
    }

    public ArrayList<Piatto> getMenu(){
        return Data.getMenu();
    }
}
