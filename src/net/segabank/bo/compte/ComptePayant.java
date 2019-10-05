package net.segabank.bo.compte;

import net.segabank.bo.agence.Agence;

public class ComptePayant extends Compte {

    private static int fraisCommission = 5;

    public ComptePayant(int id, int solde){
        super(id, solde, CompteType.PAYANT);
    }

    public int getFraisCommission(){
        return fraisCommission;
    }

    @Override
    public void retrait(int montant) {
        this.solde -= montant * 1+(this.fraisCommission /100);
    }

    @Override
    public void ajout(int montant){
        this.solde += montant*(1-(this.fraisCommission /100));
    }

    @Override
    public String toString() {
        return " - COMPTE_PAYANT [solde : " + solde + "]";
    }
}
