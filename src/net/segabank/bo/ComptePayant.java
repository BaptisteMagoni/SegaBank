package net.segabank.bo;

public class ComptePayant extends Compte {

    private static int interet = 5;

    public ComptePayant(int id, int solde) {
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
