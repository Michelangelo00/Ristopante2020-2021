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
    public boolean addFood(Piatto nuovo){
        boolean add=false;
        ListIterator<Piatto> itr= data.getMenu().listIterator();
        while(itr.hasNext()){
            Piatto p = itr.next();
            if(p.getNome().equals(nuovo.getNome())){
                add=false;
                break;
            }else{
                add=true;
            }
        }
        if(add){
            data.getMenu().add(nuovo);
            return true;
        }
        return false;

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
        boolean remove=false;
        int index=0;
        ListIterator<Piatto> itr= data.getMenu().listIterator();
        while(itr.hasNext()){
            Piatto p = itr.next();
            if(p.getNome().equals(piatto.getNome())){
                index=itr.nextIndex()-1;
                remove=true;
                break;
            }else{
                remove=false;
            }
        }
        if(remove){
            data.getMenu().remove(index);
        }
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
    }

    public ArrayList<Piatto> getMenu(){
        return data.getMenu();
    }

    public void Load(){
        data.loadMenu();
    }
}
