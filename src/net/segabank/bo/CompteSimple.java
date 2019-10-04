package net.segabank.bo;

public class CompteSimple extends Compte{

    private int decouvert;

    public CompteSimple(int id, int solde) {
        super(id, solde);
    }

    @Override
    public void retrait(int montant) {

    }

    @Override
    public String toString() {
        return null;
    }
}
