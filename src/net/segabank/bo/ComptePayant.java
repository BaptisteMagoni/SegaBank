package net.segabank.bo;

public class ComptePayant extends Compte {

    private static int fraisCommission = 5;

    public ComptePayant(int id, int solde, int fraisCommission) {
        super(id, solde);
        this.fraisCommission = fraisCommission;
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
        return "ComptePayant{" +
                "id=" + id +
                ", solde=" + solde +
                '}';
    }
}
