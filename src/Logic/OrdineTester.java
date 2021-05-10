package Logic;

public class OrdineTester {

    public static void main(String[] args) {

        Ordine ordine1 = new Ordine(12);

        ordine1.AggiungiPiatto(new Piatto("carbonara", 12.50));
        ordine1.AggiungiPiatto(new Piatto("amatriciana", 10.00, 2));
        ordine1.AggiungiPiatto(new Piatto("gricia", 11.90));

        System.out.println(ordine1);
        System.out.println("Expected: tavolo n: 12 stato: 0 piatti: [1 carbonara 12.5€, 2 amatriciana 10.0€, 1 gricia 11.9€]");

        System.out.println(ordine1.cercaPiatto(new Piatto("amatriciana", 10.00)));
        System.out.println("Expected: 2 amatriciana 10 euro");

        ordine1.RimuoviPiatto(new Piatto("amatriciana", 10.00));
        System.out.println(ordine1);
        System.out.println("Expected: tavolo n: 12 stato: 0 piatti: [1 carbonara 12.5€, 1 gricia 11.9€]");

        ordine1.RimuoviPiatto(new Piatto("parmigiana", 10.00));
        System.out.println("Expected: Piatto non trovato!");

    }

}
