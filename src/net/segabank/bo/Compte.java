package net.segabank.bo;

public abstract class Compte {

    protected int id;
    protected int solde;

    public Compte(int id, int solde) {
        this.id = id;
        this.solde = solde;
    }

    public int getId() { return id; }
    public int getSolde() { return solde; }

    public void setId(int id) { this.id = id; }
    public void setSolde(int solde) { this.solde = solde; }

    public void ajout(int montant){
        this.solde += montant;
    }

    public abstract void retrait(int montant);

}
