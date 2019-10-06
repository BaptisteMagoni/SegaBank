package net.segabank.bo.compte;

import net.segabank.bo.agence.Agence;

public abstract class Compte {

    protected int id;
    protected int solde;
    private CompteType compteType;

    public Compte(int id, int solde, CompteType compteType) {
        this.id = id;
        this.solde = solde;
        this.compteType = compteType;
    }

    public Compte(CompteType compteType){
        this.solde = 0;
        this.compteType = compteType;
    }

    public int getId() { return id; }
    public int getSolde() { return solde; }

    public void setId(int id) { this.id = id; }
    public void setSolde(int solde) { this.solde = solde; }

    public void ajout(int montant){
        this.solde += montant;
    }

    public void retrait(int montant){
        this.solde -= montant;
    }

}
