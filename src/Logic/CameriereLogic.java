package Logic;

public class CameriereLogic {

    private Data data = new Data();

    public Ordine CreaOrdine(int numTavolo){
        Ordine nuovoOrdine = new Ordine(numTavolo);
        return nuovoOrdine;
    }

    public void AggiungiPiatto(Ordine ordine, Piatto piatto){
        ordine.AggiungiPiatto(piatto);
    }

    public void RimuoviPiatto(Ordine ordine, Piatto piatto){
        ordine.RimuoviPiatto(piatto);
    }

    public void ModificaPiatto(Ordine ordine, Piatto piattoVecchio, Piatto piattoNuovo){
        ordine.RimuoviPiatto(piattoVecchio);
        ordine.AggiungiPiatto(piattoNuovo);
    }

    public void ModificaQuantita(Piatto piatto, int nuovaQuantita){
        piatto.setQuantita(nuovaQuantita);
    }

    public void FinalizzaOrdine(Ordine ordine){
        ordine.setStato(1);
        data.loadOrdini(ordine);
    }
}
