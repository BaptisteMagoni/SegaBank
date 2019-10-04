package net.segabank.bo;

public class CompteEpargne extends Compte {
    private int tauxInteret;

    public CompteEpargne(int id, int solde, int tauxInteret) {
        super(id, solde);
        this.tauxInteret = tauxInteret;
    }

    @Override
    public void retrait(int montant) {

    }

    @Override
    public String toString() {
        return null;
    }
}
