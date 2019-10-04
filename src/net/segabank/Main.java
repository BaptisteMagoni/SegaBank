package net.segabank;

import java.io.IOException;
import java.util.Scanner;

// Afficher la liste des COMPTES
// Sélectionner un compte
// |╚> Détails du compte
// |╚> Virement
// |╚> Débit
// |╚> Exporter les opérations en CSV
// |╚> Supprimmer
// Afficher la liste des AGENCES
// Sélectionner une agence
// |╚> Liste des comptes par agences
// Quitter
public class Main {

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        byte action = -1;
        do{
            switch (action){
                case 1:
                    System.out.println("Action 1 saisie");
                    SC.nextLine();
                    break;
                case 2 :
                    dspMenuCompte();
                    SC.nextLine();
                    break;
                case 3:
                    System.out.println("Action 3 saisie");
                    SC.nextLine();
                    break;
                case 4:
                    System.out.println("Action 4 saisie");
                    SC.nextLine();
                    break;
                case 5:
                    break;
            }
            dspMenu();
            try{
                action = SC.nextByte();
                SC.nextLine();
                if(action > 5) throw new Exception();
            }catch( Exception e){
                System.out.println("Veuillez saisir une option du menu correct");
                action = -1;
                SC.nextLine();
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
        System.out.println("||   ╚> liste des comptes             ||");
        System.out.println("|| 5 ═> Quitter                       ||");
        System.out.println("=======================================");
        System.out.println("");
        System.out.print("Saisir une action : ");
    }

    public static void dspMenuCompte(){
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
        byte action = SC.nextByte();
        SC.nextLine();

        switch (action){
            case 1:
                System.out.println("Détails du compte :");
                break;
            case 2 :
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
    }

    public static void dspMenuAgence(){
        System.out.println("=======================================");
        System.out.println("||   ╚> 1 Afficher tous les comptes  ||");
        System.out.println("||   ╚> 2 Retour                     ||");
        System.out.println("=======================================");
        System.out.println("");
        System.out.print("Saisir une action ");
        byte action = SC.nextByte();
        SC.nextLine();
        switch(action){
            case 1:
                System.out.println("Afficher tous les comptes");
                break;
        }
    }
}
