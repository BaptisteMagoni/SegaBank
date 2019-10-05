package net.segabank;

import net.segabank.bo.agence.Agence;
import net.segabank.bo.compte.Compte;
import net.segabank.bo.compte.CompteType;
import net.segabank.dao.compte.CompteDAO;
import net.segabank.dao.compte.IDAOCompte;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Main {

    private static final Scanner SC = new Scanner(System.in);
    private static final IDAOCompte<CompteType, Compte, Integer, Agence> COMPTE_DAO = new CompteDAO();
    private static Map<Integer, Agence> agences = new HashMap<>();
    private static List<Compte> lesComptes;

    public static void main(String[] args) {
        byte action = -1;
        do{
            switch (action){
                case 1:
                    System.out.println("Afficher la liste des comptes");
                    try {
                        lesComptes = COMPTE_DAO.findAll();
                        for(Compte unCompte : lesComptes){
                            System.out.println(unCompte);
                        }
                    } catch (SQLException | IOException | ClassNotFoundException e) {
                        System.err.println(e.getMessage());
                    }
                    SC.nextLine();
                    break;
                case 2 :
                    dspMenuCompte();
                    SC.nextLine();
                    break;
                case 3:
                    System.out.println("Afficher la liste des agences");
                    SC.nextLine();
                    break;
                case 4:
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
        System.out.println("=======================================");
        System.out.println("======BIENVENUE CHEZ SEGABANK ! =======");
        System.out.println("=======================================");
        System.out.println("|| 1 ═> Afficher la liste des comptes ||");
        System.out.println("|| 2 ═> Sélectionner un compte        ||");
        System.out.println("||   ╚> Détails du compte             ||");
        System.out.println("||   ╚> Virement                      ||");
        System.out.println("||   ╚> Débit                         ||");
        System.out.println("||   ╚> Supprimmer le compte          ||");
        System.out.println("||   ╚> Exporter les opérations       ||");
        System.out.println("|| 3 ═> Afficher la liste des agences ||");
        System.out.println("|| 4 ═> Sélectionner une agence       ||");
        System.out.println("||   ╚> Afficher tous les comptes     ||");
        System.out.println("|| 5 ═> Quitter                       ||");
        System.out.println("=======================================");
        System.out.println("");
        System.out.print("Saisir une action : ");
    }

    public static void dspMenuCompte(){
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
            System.out.println("=======================================");
            System.out.println("||   ╚> 1 Détails du compte          ||");
            System.out.println("||   ╚> 2 Virement                   ||");
            System.out.println("||   ╚> 3 Débit                      ||");
            System.out.println("||   ╚> 4 Supprimmer le compte       ||");
            System.out.println("||   ╚> 5 Exporter les opérations    ||");
            System.out.println("||   ╚> 6 Retour                     ||");
            System.out.println("=======================================");
            System.out.println("");
            System.out.print("Saisir une action :");
            try{
                action = SC.nextByte();
                SC.nextLine();
                if(action > 6 || action < 1) throw new Exception();
            }catch(Exception e ){
                System.out.println("Veuillez saisir une option correct");
                action = -1;
            }
        }while(action != 6);

    }

    public static void dspMenuAgence(){
        byte action = -1;
        do{
            switch(action){
                case 1:
                    System.out.println("Afficher tous les comptes");
                    break;
            }
            System.out.println("=======================================");
            System.out.println("||   ╚> 1 Afficher tous les comptes  ||");
            System.out.println("||   ╚> 2 Retour                     ||");
            System.out.println("=======================================");
            System.out.println("");
            System.out.print("Saisir une action ");
            try{
                action = SC.nextByte();
                SC.nextLine();
                if(action > 2 || action < 1) throw new Exception();
            }catch (Exception e ){
                System.out.println("Veuillez saisir une option correct");
                action = -1;
            }
        }while(action !=2);


    }
}
