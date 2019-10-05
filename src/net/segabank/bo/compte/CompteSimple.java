package net.segabank.bo.compte;

import net.segabank.bo.agence.Agence;

public class CompteSimple extends Compte{

    private int decouvert;

    public CompteSimple(int id, int solde, Agence agence, int decouvert) {
        super(id, solde, agence, CompteType.SIMPLE);
        this.decouvert = decouvert;
    }

    public void setDecouvert(int decouvert){
        this.decouvert = decouvert;
    }

    private boolean estDebitable(int montant){
        return montant <this.solde+this.decouvert;
    }

    public int getDecouvert(){
        return decouvert;
    }

    @Override
    public void retrait(int montant) {
        if(estDebitable(montant)){
            this.solde -= montant;
        }
    }

    @Override
    public String toString() {
        return "CompteSimple{" +
                "decouvert=" + decouvert +
                ", id=" + id +
                ", solde=" + solde +
                '}';
    }
}
