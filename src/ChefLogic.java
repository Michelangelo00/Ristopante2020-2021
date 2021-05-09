import java.io.*;
import java.util.Scanner;

public class ChefLogic {
    //private Scanner in = new Scanner(System.in);
    //private String file = in.nextLine();
    private File inputfile= new File("/home/lollof00/IdeaProjects/Prova/menu.txt");
    static int numero_pietanze=0;

    public void addFood(String food, String price){
        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(inputfile,true));
            out.write("/*"+(numero_pietanze++)+"*/"+"Pietanza: "+food+" Prezzo: "+price);
            out.write("\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeFood(String food, double price){}
}
