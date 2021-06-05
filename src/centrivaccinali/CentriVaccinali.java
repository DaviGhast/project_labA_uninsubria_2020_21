
package centrivaccinali;
import gestionefile.GestioneCSV;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

/**
 * CentriVaccinali è la classe principale della sotto applicazione Centri Vaccinali.
 *
 * @author Davide Mainardi
 * @author Marc Cepraga
 * @author Luca Muggiasca
 * @author Brenno Re
 */

public class CentriVaccinali {

    public static void main(String[] args) {

        loopMenu();

    }

    /**
     *
     * @throws IOException
     */
    public static void loopMenu () {
        Scanner in = new Scanner(System.in);
        GestioneCentrivaccinali gestioneCentrivaccinali;
        GestioneVaccinati gestioneVaccinati;
        boolean exit = false;
        while (!exit){
            System.out.println("MENU Operatori vaccinali:");
            System.out.println("0 - Registra nuovo centro vaccinale");
            System.out.println("1 - Registra nuovo vaccinato");
            System.out.println("2 - Esci e torna alla Homepage");
            System.out.print("Enter op: ");
            int op = in.nextInt();
            switch (op){
                case 0:
                    gestioneCentrivaccinali = new GestioneCentrivaccinali();
                    gestioneCentrivaccinali.verificaFile();
                    gestioneCentrivaccinali.scriviCentriVaccinali(
                            gestioneCentrivaccinali.registraCentroVaccinale());
                    gestioneCentrivaccinali.letturaFile();
                    break;
                case 1:
                    gestioneCentrivaccinali = new GestioneCentrivaccinali();
                    gestioneVaccinati = new GestioneVaccinati(gestioneCentrivaccinali.cercaCentro());
                    gestioneVaccinati.verificaFile();
                    gestioneVaccinati.scriviVaccinati(gestioneVaccinati.registraVaccinati());
                    gestioneVaccinati.letturaFile();
                    break;
                case 2:
                    exit = true;
                    break;
            }
        }
    }


}

class GestioneCentrivaccinali extends GestioneCSV {

    /**
     * Metodo costruttore <code>GestioneCSV</code>
     *
     */
    public GestioneCentrivaccinali() {
        super("CentriVaccinali.dati", new String[]{"Id","Nome Centro","Qual Indirizzo","Nome Indirizzo","Civico","Comune","Provincia","Cap","Tipologia"});
    }

    public void scriviCentriVaccinali(Vector<CentroVaccinale> centriVaccinaliVector) {
        for (CentroVaccinale centroVaccinale : centriVaccinaliVector) {
            StringBuffer linea = new StringBuffer();
            linea.append(centroVaccinale.getId());
            linea.append(SEPARATORE_CSV);
            linea.append(centroVaccinale.getNomeCentroVaccinale());
            linea.append(SEPARATORE_CSV);
            linea.append(centroVaccinale.getQualificatoreIndirizzo());
            linea.append(SEPARATORE_CSV);
            linea.append(centroVaccinale.getNomeIndirizzo());
            linea.append(SEPARATORE_CSV);
            linea.append(centroVaccinale.getNumeroCivico());
            linea.append(SEPARATORE_CSV);
            linea.append(centroVaccinale.getComune());
            linea.append(SEPARATORE_CSV);
            linea.append(centroVaccinale.getSiglaProvincia());
            linea.append(SEPARATORE_CSV);
            linea.append(centroVaccinale.getCap());
            linea.append(SEPARATORE_CSV);
            linea.append(centroVaccinale.getTipologia());
            linea.append(SEPARATORE_CSV);
            scritturaFile(linea.toString());
        }
    }

    public Vector<CentroVaccinale> registraCentroVaccinale(){
        Scanner in = new Scanner(System.in);
        Vector<CentroVaccinale> centriVaccinaliVector = new Vector<>();
        boolean exit = false;
        while (!exit){
            //creazione e ricerca id libero
            short id = 0;
            while (ricercaIdEsiste(""+id)!=false){
                id++;
            }
            //richieste info in input
            System.out.print("Inserisci nome Centro Vaccinale: ");
            String nomeCentroVaccinale = in.nextLine();
            System.out.print("Inserisci qualificatoreIndirizzo: ");
            String qualificatoreIndirizzo = in.nextLine();
            System.out.print("Inserisci nomeIndirizzo: ");
            String nomeIndirizzo = in.nextLine();
            System.out.print("Inserisci numeroCivico: ");
            String numeroCivico = in.nextLine();
            System.out.print("Inserisci comune: ");
            String comune = in.nextLine();
            System.out.print("Inserisci siglaProvincia: ");
            String siglaProvincia = in.nextLine();
            siglaProvincia = siglaProvincia.toUpperCase();
            System.out.print("Inserisci cap: ");
            String cap = in.nextLine();
            System.out.print("Inserisci tipologia: ");
            String tipologia = in.nextLine();
            tipologia = tipologia.substring(0,1).toUpperCase()+tipologia.substring(1).toLowerCase();
            //inserimento oggetto CentroVaccinale nel vettore
            centriVaccinaliVector.add(new CentroVaccinale(id,nomeCentroVaccinale,qualificatoreIndirizzo,nomeIndirizzo,comune,siglaProvincia,tipologia,Integer.parseInt(numeroCivico),Integer.parseInt(cap)));
            System.out.println("Oggetto caricato nel vettore");
            //vuoi continuare
            boolean controllo = false;
            String opzione = "";
            while (!controllo){
                System.out.print("Vuoi inserire un nuovo Centro Vaccinale? [S/N] ");
                opzione = in.next();
                opzione = opzione.toUpperCase();
                if (opzione.equals("S")||opzione.equals("SI")||opzione.equals("N")||opzione.equals("NO")){
                    controllo = true;
                } else {
                    System.out.print("Errore di inserimento, ritenta");
                }
                System.out.println();
            }
            switch (opzione){
                case "S":
                case "SI":
                    exit = false;
                    break;
                case "N":
                case "NO":
                    exit = true;
            }
        }
        return centriVaccinaliVector;
    }

    public String cercaCentro (){
        Scanner in = new Scanner(System.in);
        boolean esci = false;
        String nomeCentroVaccinale = "";
        while (!esci){
            System.out.print("Inserisci nome Centro Vaccinale: ");
            nomeCentroVaccinale = in.nextLine();
            if (ricercaCentroEsiste(nomeCentroVaccinale) == true){
                esci = true;
            } else {
                System.out.print("Centro inesistente, magari hai sbagliato a scrivere");
            }
        }
        return nomeCentroVaccinale;
    }

    public boolean ricercaCentroEsiste(String nomeCentroVaccinale) {
        boolean exist = false;
        int count = numRisultatiPerTutto(nomeCentroVaccinale);
        Vector<String[]> rows = ricercaRighePerTutto(nomeCentroVaccinale);
        for (int i = 0; i < count; i++) {
            String[] row = rows.elementAt(i);
            if (row[1].equals(nomeCentroVaccinale)){
                exist = true;
            }
        }
        return exist;
    }

}

class GestioneVaccinati extends GestioneCSV {

    private String nomeCentroVaccinale;
    /**
     * Metodo costruttore <code>GestioneCSV</code>
     * @param nomeCentroVaccinale
     */
    public GestioneVaccinati(String nomeCentroVaccinale) {
        super("Vaccinati_"+nomeCentroVaccinale.replace(" ","_")+".dati",
                new String[]{"Id","Nome Centro"});
        this.nomeCentroVaccinale = nomeCentroVaccinale;
    }

    public Vector<CittadinoVaccinato> registraVaccinati() {
        Scanner in = new Scanner(System.in);
        Vector<CittadinoVaccinato> vaccinatiVector = new Vector<>();
        boolean exit = false;
        while (!exit){
            //creazione e ricerca id libero
            short id = 0;
            while (ricercaIdEsiste(""+id)!=false){
                id++;
            }
            //richieste info in input
            System.out.print("Inserisci nomeCittadino: ");
            String nomeCittadino = in.nextLine();
            System.out.print("Inserisci cognomeCittadino: ");
            String cognomeCittadino = in.nextLine();
            System.out.print("Inserisci codiceFiscaleCittadino: ");
            String codiceFiscaleCittadino = in.nextLine();
            String dataVaccinazione = "";
            //inserimento data
            DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            boolean controlloData = false;
            String opzioneData = "";
            while (!controlloData){
                System.out.print("Vuoi prendere la data di oggi? [S/N] ");
                opzioneData = in.nextLine();
                opzioneData = opzioneData.toUpperCase();
                if (opzioneData.equals("S")||opzioneData.equals("SI")||opzioneData.equals("N")||opzioneData.equals("NO")){
                    controlloData = true;
                } else {
                    System.out.print("Errore di inserimento, ritenta");
                }
            }
            switch (opzioneData){
                case "S":
                case "SI":
                    //automatico
                    Date oggi = Calendar.getInstance().getTime();
                    dataVaccinazione = formato.format(oggi);
                    break;
                case "N":
                case "NO":
                    //manuale
                    String giorno = "0",mese = "0",anno = "0";
                    while (giorno.length() != 2 && Integer.parseInt(giorno) <= 31){
                        System.out.print("Inserisci giorno in numero [gg]: ");
                        giorno = in.nextLine();
                    }
                    while (mese.length() != 2 && Integer.parseInt(mese) <= 12){
                        System.out.print("Inserisci mese in numero [mm]: ");
                        mese = in.nextLine();
                    }
                    while (anno.length() != 4){
                        System.out.print("Inserisci anno in numero [anno]: ");
                        anno = in.nextLine();
                    }
                    dataVaccinazione = giorno+"/"+mese+"/"+anno;
                    break;
            }
            System.out.print("Inserisci vaccinoSomministrato: ");
            String vaccinoSomministrato = in.nextLine();
            //creazione e ricerca id univoco libero
            GestioneCSV vaccinati = new GestioneCSV("Vaccinati.dati",new String[]{"Id Univoco", "Centro Vaccinale", "Id Interno"});
            vaccinati.verificaFile();
            short idUniv = 0;
            while (vaccinati.ricercaIdEsiste(""+idUniv)!=false){
                idUniv++;
            }
            //scrittura parallela dei dati univoci nel file Vaccinati.dati
            vaccinati.scritturaFile(idUniv+SEPARATORE_CSV+nomeCentroVaccinale+SEPARATORE_CSV+id);
            //inserimento oggetto CentroVaccinale nel vettore
            vaccinatiVector.add(new CittadinoVaccinato(id,nomeCentroVaccinale,nomeCittadino,cognomeCittadino,codiceFiscaleCittadino,dataVaccinazione,vaccinoSomministrato,idUniv));
            System.out.println("Oggetto caricato nel vettore");
            //vuoi continuare
            boolean controllo = false;
            String opzione = "";
            while (!controllo){
                System.out.print("Vuoi inserire un nuovo Vaccinato? [S/N] ");
                opzione = in.next();
                opzione = opzione.toUpperCase();
                if (opzione.equals("S")||opzione.equals("SI")||opzione.equals("N")||opzione.equals("NO")){
                    controllo = true;
                } else {
                    System.out.print("Errore di inserimento, ritenta");
                }
            }
            switch (opzione){
                case "S":
                case "SI":
                    exit = false;
                    break;
                case "N":
                case "NO":
                    exit = true;
                    break;
            }
        }
        return vaccinatiVector;
    }

    public void scriviVaccinati(Vector<CittadinoVaccinato> vaccinatiVector) {
        for (CittadinoVaccinato cittadinoVaccinato : vaccinatiVector) {
            StringBuffer linea = new StringBuffer();
            linea.append(cittadinoVaccinato.getId());
            linea.append(SEPARATORE_CSV);
            linea.append(cittadinoVaccinato.getNomeCentroVaccinale());
            linea.append(SEPARATORE_CSV);
            linea.append(cittadinoVaccinato.getNomeCittadino());
            linea.append(SEPARATORE_CSV);
            linea.append(cittadinoVaccinato.getCognomeCittadino());
            linea.append(SEPARATORE_CSV);
            linea.append(cittadinoVaccinato.getCodiceFiscaleCittadino());
            linea.append(SEPARATORE_CSV);
            linea.append(cittadinoVaccinato.getDataVaccinazione());
            linea.append(SEPARATORE_CSV);
            linea.append(cittadinoVaccinato.getVaccinoSomministrato());
            linea.append(SEPARATORE_CSV);
            linea.append(cittadinoVaccinato.getIdVaccinazione());
            linea.append(SEPARATORE_CSV);
            scritturaFile(linea.toString());
        }
    }

}
