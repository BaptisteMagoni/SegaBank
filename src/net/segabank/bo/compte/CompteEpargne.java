package net.segabank.bo.compte;

import net.segabank.bo.agence.Agence;

public class CompteEpargne extends Compte {

    private int tauxInteret;

    public CompteEpargne(int id, int solde, int tauxInteret) {
        super(id, solde, CompteType.EPARGNE);
        this.tauxInteret = tauxInteret;
    }

    public int getTauxInteret(){
        return tauxInteret;
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
