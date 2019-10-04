package net.segabank.bo;

public abstract class Compte {

    protected int id;
    protected int solde;

    public abstract void ajout(int montant);

    public abstract void retrait(int montant);

    public abstract String toString();

}
