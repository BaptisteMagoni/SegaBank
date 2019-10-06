package net.segabank.bo.compte;

import net.segabank.bo.agence.Agence;

public class CompteSimple extends Compte{

    private int decouvert;

    public CompteSimple(int id, double solde, int decouvert) {
        super(id, solde, CompteType.SIMPLE);
        this.decouvert = decouvert;
    }

    public CompteSimple(){
        super(CompteType.SIMPLE);
    }

    public void setDecouvert(int decouvert){
        this.decouvert = decouvert;
    }

    private boolean estDebitable(double montant){
        return montant <this.solde+this.decouvert;
    }

    public int getDecouvert(){
        return decouvert;
    }

    @Override
    public void retrait(double montant) {
        if(estDebitable(montant)){
            this.solde -= montant;
        }
    }

    @Override
    public String toString() {
        return " - COMPTE_SIMPLE [solde : " + solde + ", decouvert : " + decouvert + "]";
    }
}
