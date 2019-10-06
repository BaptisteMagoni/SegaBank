package net.segabank.bo.compte;

import net.segabank.bo.agence.Agence;

public class ComptePayant extends Compte {

    private static int fraisCommission = 5;

    public ComptePayant(int id, double solde){
        super(id, solde, CompteType.PAYANT);
    }

    public ComptePayant(){
        super(CompteType.PAYANT);
    }

    public int getFraisCommission(){
        return fraisCommission;
    }

    @Override
    public void retrait(double montant) {
        this.solde -= montant * 1+(this.fraisCommission /100);
    }

    @Override
    public void ajout(double montant){
        this.solde += montant*(1-(this.fraisCommission /100));
    }

    @Override
    public String toString() {
        return " - COMPTE_PAYANT [solde : " + solde + "]";
    }
}
