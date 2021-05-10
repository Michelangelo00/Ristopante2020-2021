package Logic;

public class PiattoTester {
    public static void main(String[] args) {

        Piatto piatto1 = new Piatto("carbonara", 12.50, 2);
        Piatto piatto2 = new Piatto("gricia", 10.00);
        Piatto piatto3 = new Piatto("amatriciana", 11.90);

        System.out.println("piatto 1 = nome: " + piatto1.getNome() + " prezzo: " + piatto1.getPrezzo() + " quantita: " + piatto1.getQuantita());
        System.out.println("Expected: piatto 1 = nome: carbonara prezzo: 12.50 quantita: 2");

        System.out.println("piatto 2 = nome: " + piatto2.getNome() + " prezzo: " + piatto2.getPrezzo() + " quantita: " + piatto2.getQuantita());
        System.out.println("Expected: piatto 2 = nome: gricia prezzo: 10.00 quantita: 1");

        System.out.println("piatto 3 = nome: " + piatto3.getNome() + " prezzo: " + piatto3.getPrezzo() + " quantita: " + piatto3.getQuantita());
        System.out.println("Expected: piatto 3 = nome: amatriciana prezzo: 11.90 quantita: 1");


        piatto3.setNome("amatriciana speciale");
        piatto3.setPrezzo(15.90);
        piatto3.setQuantita(3);

        System.out.println("piatto 3 = nome: " + piatto3.getNome() + " prezzo: " + piatto3.getPrezzo() + " quantita: " + piatto3.getQuantita());
        System.out.println("Expected: piatto 3 = nome: amatriciana speciale prezzo: 15.90 quantita: 3");
    }
}
