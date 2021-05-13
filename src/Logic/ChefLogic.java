package Logic;

import java.util.*;

/**
 *classe che implementa la figura dello chef con (aggiungere, rimuovere, modificare piatti del menù)
 */

public class ChefLogic {
    private static Data data = new Data();

    public ChefLogic(){
        Load();
    }

    /**
     * metodo per aggiungere un nuovo piatto al menù
     //* @param food nome del piatto da aggiungere
     //* @param price prezzo del piatto da aggiungere
     */
    public void addFood(Piatto nuovo){
        //System.out.println(food);
        if(!(data.getMenu().contains(nuovo))){
            data.getMenu().add(nuovo);
        }

        /*Piatto nuovo = null;
        Iterator<Piatto> itr= data.getMenu().iterator();
        while(itr.hasNext()){
            Piatto p = itr.next();
            if(!(p.getNome().equals(food) && p.getPrezzo()==price )){
                nuovo= new Piatto(food,price);
            }
        }
        if(nuovo!=null){
            data.getMenu().add(nuovo);
        }*/

        System.out.println(data.getMenu());
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
    public  void removeFood(Piatto piatto){
        if(data.getMenu().contains(piatto)){
            System.out.println("ce sta");
        }
        data.getMenu().remove(piatto);
        System.out.println(data.getMenu());

    }

    /**
     * metodo per modificare un piatto (se esiste) nel menù
     * @param piatto_vecchio vecchio piatto da modificare
     * @param piatto_nuovo nuovo piatto modificato
     */
    public void editFood(Piatto piatto_vecchio, Piatto piatto_nuovo){
        if(data.getMenu().contains(piatto_vecchio)){
            int index= data.getMenu().indexOf(piatto_vecchio);
            data.getMenu().set(index,piatto_nuovo);
        }
        System.out.println(data.getMenu());
    }

    public List<Piatto> getMenu(){
        return data.getMenu();
    }

    public void Load(){
        data.loadMenu();
    }
}
