package net.segabank.bo;

public class CompteSimple extends Compte{

    private int decouvert;

    public CompteSimple(int id, int solde, int decouvert) {
        super(id, solde);
        this.decouvert = decouvert;
    }

    private boolean estDebitable(int montant){
        return montant <this.solde+this.decouvert;
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
