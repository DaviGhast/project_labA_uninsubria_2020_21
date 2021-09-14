package cittadini;

import centrivaccinali.CentroVaccinale;
import centrivaccinali.GestioneCentriVaccinali;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe <code>Cittadini</code>
 * <p>
 *     Questa classe ha lo scopo di contenere i metodi e il main della sotto applicazione per i cittadini
 * </p>
 * @author Davide Mainardi
 * @author Marc Cepraga
 * @author Luca Muggiasca
 * @author Brenno Re
 */
public class Cittadini {

    private static Cittadini istanza;

    public static Cittadini getInstance(){
        if (istanza == null) {
            istanza = new Cittadini();
        }
        return istanza;
    }

    public void cercaCentroVaccinale(){
        Scanner in = new Scanner(System.in);
        //GestioneCentriVaccinali gestioneCentriVaccinali = new GestioneCentriVaccinali();
        boolean exit = false;
        while (!exit){
            System.out.println("MENU Cerca centro Vaccinalie:");
            System.out.println("0 - ricerca per nome del centro");
            System.out.println("1 - ricerca per comune e tipologia");
            System.out.println("2 - Esci dalla ricerca");
            System.out.print("Enter op: ");
            int op = in.nextInt();
            switch (op){
                case 0:
                    //visualizzaInfoCentroVaccinale(gestioneCentriVaccinali.getCentroVaccinalePerNome());
                    break;
                case 1:
                    //visualizzaInfoCentroVaccinale(gestioneCentriVaccinali.getCentroVaccinalePerComuneTipologia());
                    break;
                case 2:
                    exit = true;
                    break;
            }
        }
    }

    /**
     * il metodo restituisce le info di un centro vaccinale
     * @param listaCentriVaccinali
     * @return info del CentroVaccinale
     */
    public CentroVaccinale visualizzaInfoCentroVaccinale(ArrayList<CentroVaccinale> listaCentriVaccinali){
        Scanner in = new Scanner(System.in);
        if (listaCentriVaccinali != null){
            System.out.println("\nLista Ouptup ricerca:");
            for (int i = 0; i < listaCentriVaccinali.size(); i++){
                System.out.println(""+i +" - "+listaCentriVaccinali.get(i).getNomeCentroVaccinale()+"");
            }
            System.out.print("Enter op: ");
            int op = in.nextInt();
            System.out.println("Di seguito le informazioni del centro selezionato:");
            System.out.print(listaCentriVaccinali.get(op).toString());
            return listaCentriVaccinali.get(op);
        } else {
            System.out.println("\nhai sbagliato a scrivere gli argomenti di ricerca:");
            return null;
        }
    }

    /**
     * gestione credenziali per il login del cittadino
     */
    public void login(){
        //GestioneCittadinoRegistrato gestioneCittadinoRegistrato = new GestioneCittadinoRegistrato();
        Scanner in = new Scanner(System.in);
        System.out.print("Inserisci il tuo Userid ");
        String userid = in.nextLine();
        System.out.print("Inserisci la tua Password ");
        String password = in.nextLine();
        //if (gestioneCittadinoRegistrato.rispostaCittadinoEsiste(userid,password)){


        //} else {
            System.out.print("Login errato");
        //}
    }


}
