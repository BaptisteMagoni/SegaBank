package net.segabank;

import net.segabank.bo.agence.Agence;
import net.segabank.bo.compte.Compte;
import net.segabank.bo.compte.CompteType;
import net.segabank.dao.agence.AgenceDAO;
import net.segabank.dao.agence.IDAOAgence;
import net.segabank.dao.compte.CompteDAO;
import net.segabank.dao.compte.IDAOCompte;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Main {

    private static final Scanner SC = new Scanner(System.in);
    private static final IDAOCompte<CompteType, Compte, Integer, Agence> COMPTE_DAO = new CompteDAO();
    private static final IDAOAgence<Agence, Integer> AGENCE_DAO = new AgenceDAO();
    private static Map<Integer, Agence> agences = new HashMap<>();

    public static void main(String[] args) {
        byte action = -1;
        do{
            switch (action){
                // Afficher la liste des comptes
                case 1:
                    dspAllCompte();
                    SC.nextLine();
                    break;
                // Afficher le menu des actions sur les comptes
                case 2 :
                    //TODO : Sous action quand FindCompteByAgence fonctionneras
                    dspMenuCompte();
                    SC.nextLine();
                    break;
                // Afficher la liste des agences
                case 3:
                    dspAllAgences();
                    SC.nextLine();
                    break;
                case 4:
                    //TODO : Afficher les comptes pour une agence
                    dspMenuAgence();
                    SC.nextLine();
                    break;
                case 5:
                    break;
            }
            dspMenu();
            try{
                action = SC.nextByte();
                SC.nextLine();
                if(action > 5 || action < 1) throw new Exception();
            }catch( Exception e){
                System.out.println("Veuillez saisir une option correct");
                action = -1;
            }
        }while(action != 5);
        System.out.println("A bientôt sur segabank !");
    }

    public static void dspMenu(){
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("╠══════ BIENVENUE CHEZ SEGABANK ═════╣");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║ 1  → Afficher la liste des comptes ║");
        System.out.println("║ 2  → Sélectionner un compte        ║");
        System.out.println("║     ↳ Détails du compte            ║");
        System.out.println("║     ↳ Virement                     ║");
        System.out.println("║     ↳ Débit                        ║");
        System.out.println("║     ↳ Supprimmer le compte         ║");
        System.out.println("║     ↳ Exporter les opérations      ║");
        System.out.println("║ 3  → Afficher la liste des agences ║");
        System.out.println("║ 4  → Sélectionner une agence       ║");
        System.out.println("║     ↳ Afficher tous les comptes    ║");
        System.out.println("║ 5  → Quitter                       ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("");
        System.out.print("Saisir une action : ");
    }

    public static void dspMenuCompte(){

        Agence monAgence = chooseAgence();
        List<Compte> lesComptesParAgence = new ArrayList<>();
        int sizeCompte = 0;
        try {
            lesComptesParAgence = COMPTE_DAO.findCompteByIdAgence(monAgence);
            sizeCompte = lesComptesParAgence.size();
            for(Compte compte : lesComptesParAgence){
                System.out.println(compte);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        Compte monCompte = null;
        int compteId = -1;
        do{
            try{
                System.out.print("Choisir un compte : ");
                compteId = SC.nextInt();
                SC.nextLine();
                if(compteId> sizeCompte) throw new Exception();
            }catch(Exception e){
                compteId = -1;
                System.out.println("Choissisez un compte valable");
                SC.nextLine();
            }
        }while(compteId <= 0 || compteId > sizeCompte);
        monCompte = lesComptesParAgence.get(compteId);

        byte action = -1;
        do {
            switch (action) {
                case 1:
                    System.out.println("Détails du compte :");
                    break;
                case 2:
                    System.out.println("Virement");
                    break;
                case 3:
                    System.out.println("Débit");
                    break;
                case 4:
                    System.out.println("Supprimmer le compte");
                    break;
                case 5:
                    System.out.println("Exporter les opérations");
                    break;
            }
            System.out.println("╔════════════════════════════════════╗");
            System.out.println("║     ↳ 1 Détails du compte          ║");
            System.out.println("║     ↳ 2 Virement                   ║");
            System.out.println("║     ↳ 3 Débit                      ║");
            System.out.println("║     ↳ 4 Supprimmer le compte       ║");
            System.out.println("║     ↳ 5 Exporter les opérations    ║");
            System.out.println("║     ↳ 6 Retour                     ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("");
            System.out.print("Saisir une action :");
            try{
                action = SC.nextByte();
                SC.nextLine();
                if(action > 6 || action < 1) throw new Exception();
            }catch(Exception e ){
                action = -1;
                System.out.println("Veuillez saisir une option correct");
            }
        }while(action != 6);

    }

    /**
     * Affiche le menu des Agences (Option 4 du menu principal)
     * Effectue les actions proposées
     */
    public static void dspMenuAgence(){
        Agence monAgence = chooseAgence();

        byte action = -1;
        do{
            switch(action){
                case 1:
                    System.out.println("Afficher tous les comptes");
                    dspCompteByAgence(monAgence);
                    break;
            }
            System.out.println("╔════════════════════════════════════╗");
            System.out.println("║     ↳ 1 Afficher tous les comptes  ║");
            System.out.println("║     ↳ 2 Retour                     ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("");
            System.out.print("Saisir une action ");
            try{
                action = SC.nextByte();
                if(action > 2 || action < 1) throw new Exception();
            }catch (Exception e ){
                System.out.println("Veuillez saisir une option correct");
                SC.nextLine();
                action = -1;
            }
        }while(action !=2);


    }

    /**
     * Retourne une instance d'agence parmis toutes celles proposées
     * L'utilisateur choisis l'agence qu'il veut parmis celles de la BDD
     * @return Agence
     */
    private static Agence chooseAgence() {
        dspAllAgences();
        int agenceId = -1;
        do{
            System.out.print("Choisir une agence : ");
            try {
                agenceId = SC.nextInt();
                SC.nextLine();
            }catch(Exception e){
                agenceId = -1;
                System.out.println("Choissisez une agence correct");
            }
        }while(agenceId < 0 || agenceId > agences.size());
        Agence monAgence = agences.get(agenceId);
        System.out.println("Agence choisie : " + monAgence);
        return monAgence;
    }

    /**
     * Affiche toutes les agences
     * Set la variable agences
     */
    private static void dspAllAgences() {
        try {
            agences = AGENCE_DAO.findAll();
            for(Agence uneAgence : agences.values()){
                System.out.println(uneAgence);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Affiche les Comptes de l'agence choisie
     * @param agence
     */
    private static void dspCompteByAgence(Agence agence) {
        try {
            for(Compte unCompte : COMPTE_DAO.findCompteByIdAgence(agence)){
                System.out.println(unCompte);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Affiche tous les comptes présent dans la BDD
     */
    private static void dspAllCompte(){
        //TODO:Retourne pas les comptes (tableau vide !)
        try {
            for(Compte unCompte : COMPTE_DAO.findAll()){
                System.out.println(unCompte);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

}
