package net.segabank.bo.agence;

import net.segabank.bo.compte.Compte;

import java.util.ArrayList;
import java.util.List;

public class Agence {

    private int id;
    private int code;
    private String adresse;
    private List<Compte> comptes;

    public Agence(int id, int code, String adresse) {
        this.id = id;
        this.code = code;
        this.adresse = adresse;
        this.comptes = new ArrayList<Compte>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
