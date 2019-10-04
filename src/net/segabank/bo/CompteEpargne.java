package net.segabank.bo;

public class CompteEpargne extends Compte {
    private int tauxInteret;

    public CompteEpargne(int id, int solde, int tauxInteret) {
        super(id, solde);
        this.tauxInteret = tauxInteret;
    }

    public void calculInteret(){
        this.solde += solde*1+(tauxInteret/100);
    }

    @Override
    public String toString() {
        return "CompteEpargne{" +
                "tauxInteret=" + tauxInteret +
                ", id=" + id +
                ", solde=" + solde +
                '}';
    }
}
