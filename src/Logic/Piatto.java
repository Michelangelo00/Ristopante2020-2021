package Logic;

/**
 * classe che rappresenta un piatto del menu
 */
public class Piatto {

    private String nome; //nome del piatto
    private double prezzo; //prezzo del piatto
    private int quantita;

    /**
     * costruttore della classe piatto
     * @param nome
     * @param prezzo
     */
    public Piatto(String nome, double prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.quantita = 1;
    }

    /**
     * costruttore classe piatto con quantita
     * @param nome
     * @param prezzo
     * @param quantita
     */
    public Piatto(String nome, double prezzo, int quantita) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }

    @Override
    public String toString(){
        return this.quantita + " " + this.getNome() + " " + this.getPrezzo() + "€ ";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}
