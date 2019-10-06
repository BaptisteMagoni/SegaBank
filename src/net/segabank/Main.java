package net.segabank;

import net.segabank.bo.agence.Agence;
import net.segabank.bo.compte.*;
import net.segabank.dao.ConnectionManager;
import net.segabank.dao.agence.AgenceDAO;
import net.segabank.dao.agence.IDAOAgence;
import net.segabank.dao.compte.CompteDAO;
import net.segabank.dao.compte.IDAOCompte;
import net.segabank.service.CsvService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class Main {

    private static final Scanner SC = new Scanner(System.in);
    private static final String MESSAGE_QUITTE = "Appuyer sur 'Entrez' pour revenir au menu principal";
    private static final IDAOCompte<CompteType, Compte, Integer, Agence> COMPTE_DAO = new CompteDAO();
    private static final IDAOAgence<Agence, Integer> AGENCE_DAO = new AgenceDAO();
    private static Map<Integer, Agence> agences = new HashMap<>();
    private static Map<Integer, CompteType> compteTypes = new HashMap<>();

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = ConnectionManager.getConnection();
        if (connection != null) {
            loadTypeCompte();
            agences = AGENCE_DAO.findAll();
            byte action = -1;
            do {
                switch (action) {
                    // Afficher la liste des comptes
                    case 1:
                        dspAllCompte();
                        SC.nextLine();
                        break;
                    // Afficher le menu des actions sur les comptes
                    case 2:
                        dspMenuCompte();
                        SC.nextLine();
                        break;
                    // Création compte
                    case 3:
                        dspMenuCreationCompte();
                        SC.nextLine();
                        break;
                    // Afficher la liste des agences
                    case 4:
                        dspAllAgences(true);
                        break;
                    case 5:
                        dspMenuAgence();
                        SC.nextLine();
                        break;
                }
                dspMenu();
                try {
                    action = SC.nextByte();
                    if (action > 6 || action < 1) throw new Exception();
                } catch (Exception e) {
                    action = -1;
                    SC.nextLine();
                    System.out.println("Veuillez saisir une option correct (entier)");
                }
                SC.nextLine();
            } while (action != 6);

            System.out.println("╔════════════════════════════════════╗");
            System.out.println("╠══════ A BIENTOT SUR SEGABANK ══════╣");
            System.out.println("╚════════════════════════════════════╝");
        }else{
            System.out.println("╔════════════════════════════════════╗");
            System.out.println("╠═════ Vérifier votre connexion ═════╣");
            System.out.println("╚════════════════════════════════════╝");
        }
    }
    private static void dspMenuCreationCompte() {
        try{
            Agence monAgence = chooseAgence();
            dspTypeCompte();
            int typeCompte = -1;
            do{
                try{
                    System.out.print("Choisir le type de compte (entier) : ");
                    typeCompte = SC.nextInt();
                }catch(Exception e){
                    typeCompte = -1;
                    System.out.println("\nChoissisez un compte valable (entier)");
                }
            }while(typeCompte <= 0 || typeCompte > compteTypes.size());
            CompteType compteType = compteTypes.get(typeCompte-1);
            Compte compte = null;
            if(compteType.equals(CompteType.SIMPLE)){
                compte = new CompteSimple();
            }else if(compteType.equals(CompteType.EPARGNE)){
                compte = new CompteEpargne();
            }else if(compteType.equals(CompteType.PAYANT)){
                compte = new ComptePayant();
            }
            if(compte != null) {
                compte = COMPTE_DAO.create(compte, compteType, monAgence);
                agences.get(monAgence.getId()).addCompte(compte);
                System.out.println("Vous venez de créer un compte de type " + compteType.name());
            }

        }catch(Exception e){
            System.out.println("[Erreur] Compte non créer");
        }
    }

    private static void loadTypeCompte(){
        for(int i=0;i<CompteType.values().length;i++)
            compteTypes.put(i, CompteType.values()[i]);
    }

    private static void dspTypeCompte(){
        int i = 0;
        for(Map.Entry<Integer, CompteType> entry : compteTypes.entrySet())
            System.out.println((entry.getKey()+1) + " - " + entry.getValue());
    }

    public static void dspMenu(){
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("╠══════ BIENVENUE CHEZ SEGABANK ═════╣");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║ 1  → Afficher la liste des comptes ║");
        System.out.println("║ 2  → Sélectionner un compte        ║");
        System.out.println("║     ↳ Virement                     ║");
        System.out.println("║     ↳ Débit                        ║");
        System.out.println("║     ↳ Supprimmer le compte         ║");
        System.out.println("║ 3  → Créer un compte               ║");
        System.out.println("║ 4  → Afficher la liste des agences ║");
        System.out.println("║ 5  → Sélectionner une agence       ║");
        System.out.println("║     ↳ Afficher tous les comptes    ║");
        System.out.println("║ 6  → Quitter                       ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("");
        System.out.print("Saisir une action (entier) : ");
    }

    public static void dspMenuCompte(){
        byte action = -1;

        Agence monAgence = chooseAgence();
        List<Compte> lesComptesParAgence = new ArrayList<>();
        int sizeCompte = 0;
        try {
            lesComptesParAgence = COMPTE_DAO.findCompteByIdAgence(monAgence);
            sizeCompte = lesComptesParAgence.size();
            int i = 0;
            for(Compte compte : lesComptesParAgence){
                System.out.println(i + compte.toString());
                i++;
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        Compte monCompte = null;
        int compteId = -1;
        if(sizeCompte > 0 ){
            do{
                try{
                    System.out.print("Choisir un compte (entier) : ");
                    compteId = SC.nextInt();
                    if(compteId >= sizeCompte || compteId < 0) throw new Exception();
                }catch(Exception e){
                    compteId = -1;
                    SC.nextLine();
                    System.out.println("\nChoissisez un compte valable (entier)");
                }
            }while(compteId < 0 || compteId > sizeCompte);
            monCompte = lesComptesParAgence.get(compteId);
        }else{
            System.out.println("Il n'y a plus de compte pour cette agence");
            action = 4;
        }

        if(action != 4){
            while(action != 4) {
                boolean verifSuppr = false;
                switch (action) {
                    case 1:
                        virementCompte(monCompte);
                        break;
                    case 2:
                        debitCompte(monCompte);
                        break;
                    case 3:
                        verifSuppr = deleteCompte(monCompte);
                        break;
                }
                if(!verifSuppr){
                    System.out.println("╔════════════════════════════════════╗");
                    System.out.println("║     ↳ 1 Virement                   ║");
                    System.out.println("║     ↳ 2 Débit                      ║");
                    System.out.println("║     ↳ 3 Supprimmer le compte       ║");
                    System.out.println("║     ↳ 4 Retour                     ║");
                    System.out.println("╚════════════════════════════════════╝");
                    System.out.println("");
                    System.out.print("Saisir une action (entier) :");
                    try{
                        action = SC.nextByte();
                        if(action > 4 || action < 1) throw new Exception();
                    }catch(Exception e ){
                        action = -1;
                        SC.nextLine();
                        System.out.println("\nVeuillez saisir une option correct (entier)");
                    }
                }else{
                    action = 4;
                }
            }
        }


    }

    /**
     * Débit dans un compte
     * @param compte
     */
    private static void debitCompte(Compte compte) {
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║ Ancien Solde : " + compte.getSolde());
        System.out.print("Montant à débiter : ");
        System.out.println("╚════════════════════════════════════╝");

        try{
            int montant = SC.nextInt();
            SC.nextLine();
            if(montant < 0) throw new Exception();
            else{
                compte.retrait(montant);
                COMPTE_DAO.modifySolde(compte);
                String[] args = {"Débit", String.valueOf(montant)};
                CsvService.writeCsv(args);
            }
        }catch(Exception e){
            System.out.println("Montant incorrect");
        }
    }

    /**
     * Virement dans un compte
     * @param compte
     */
    private static void virementCompte(Compte compte) {
        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║ Ancien Solde : " + compte.getSolde());
        System.out.print("Montant à virer : ");
        System.out.println("╚════════════════════════════════════╝");
        try{
            int montant = SC.nextInt();
            SC.nextLine();
            if(montant < 0) throw new Exception();
            else{
                compte.ajout(montant);
                COMPTE_DAO.modifySolde(compte);
                String[] args = {"Virement", String.valueOf(montant)};
                CsvService.writeCsv(args);
            }
        }catch(Exception e){
            System.out.println("Montant incorrect");
        }
    }

    /**
     * Méthode de suppression d'un compte
     * true si suppr sinon false
     * @param compte
     * @return boolean
     */
    private static boolean deleteCompte(Compte compte) {
        System.out.print("Voulez vous vraiment supprimmer le compte ? (Y/N) :");
        boolean ret = false;
        try{
            String confirm = SC.nextLine();
            if(confirm.equals("Y") || confirm.equals("y")){
                COMPTE_DAO.delete(compte);
                ret =  true;
            }else ret = false;
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
        return ret;
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
                    dspCompteByAgence(monAgence);
                    break;
            }
            System.out.println("╔════════════════════════════════════╗");
            System.out.println("║     ↳ 1 Afficher tous les comptes  ║");
            System.out.println("║     ↳ 2 Retour                     ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.println("");
            System.out.print("Saisir une action (entier) ");
            try{
                action = SC.nextByte();
                if(action > 2 || action < 1) throw new Exception();
            }catch (Exception e ){
                System.out.println("\nVeuillez saisir une option correct (entier)");
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
        dspAllAgences(false);
        int agenceId = -1;
        do{
            System.out.print("Choisir une agence (entier) : ");
            try {
                agenceId = SC.nextInt();
                if(agenceId <=0 || agenceId > agences.size()) throw new Exception();
            }catch(Exception e){
                agenceId = -1;
                SC.nextLine();
                System.out.println("\nChoissisez une agence correct (entier)");
            }
        }while(agenceId <= 0 || agenceId > agences.size());
        Agence monAgence = agences.get(agenceId);
        System.out.println("Agence choisie : " + monAgence);
        return monAgence;
    }

    /**
     * Affiche toutes les agences
     * Set la variable agences
     */
    private static void dspAllAgences(boolean isDirect) { //isDirect permet de savoir si la méthode est appelé directement par le menu ou par d'autre méthode
        for(Agence uneAgence : agences.values()){
            System.out.println(uneAgence);
        }

        if(isDirect) {
            System.out.println(MESSAGE_QUITTE);
            SC.nextLine();
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
        try {
            for(Compte unCompte : COMPTE_DAO.findAll()){
                    System.out.println(unCompte);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        System.out.println(MESSAGE_QUITTE);
    }

}
