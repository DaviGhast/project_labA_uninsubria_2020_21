package centrivaccinali;

import gestionefile.GestioneCsv;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

public class GestioneVaccinati extends GestioneCsv {

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
        Vector<CittadinoVaccinato> vaccinatiVector = new Vector<CittadinoVaccinato>();
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
                    //TODO Aggiornare con uso Date
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
            //TODO modifica vaccino sarà un Enum
            String vaccinoSomministrato = in.nextLine();
            //creazione e ricerca id univoco libero
            GestioneCsv vaccinati = new GestioneCsv("Vaccinati.dati",new String[]{"Id Univoco", "Centro Vaccinale", "Id Interno"});
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
