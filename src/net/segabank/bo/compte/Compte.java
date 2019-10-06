package net.segabank.bo.compte;

import net.segabank.bo.agence.Agence;

public abstract class Compte {

    protected int id;
    protected double solde;
    private CompteType compteType;

    public Compte(int id, double solde, CompteType compteType) {
        this.id = id;
        this.solde = solde;
        this.compteType = compteType;
    }

    public Compte(CompteType compteType){
        this.solde = 0;
        this.compteType = compteType;
    }

    public int getId() { return id; }
    public double getSolde() { return solde; }

    public void setId(int id) { this.id = id; }
    public void setSolde(double solde) { this.solde = solde; }

    public void ajout(double montant){
        this.solde += montant;
    }

    public void retrait(double montant){
        this.solde -= montant;
    }

}
