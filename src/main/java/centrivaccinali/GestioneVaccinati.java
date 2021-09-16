package centrivaccinali;

import cittadini.EventoAvverso;
import gestionefile.GestioneCsv;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class GestioneVaccinati extends GestioneCsv {

    private static GestioneVaccinati istanza;
    private String nomeCentroVaccinale;
    /**
     * Metodo costruttore <code>GestioneCSV</code>
     * @param nomeCentroVaccinale
     */
    private GestioneVaccinati(String nomeCentroVaccinale) {
        super("Vaccinati_"+nomeCentroVaccinale.replace(" ","_")+".dati",
                new String[]{"Id","Nome Centro", "Nome Cittadino", "Cognome Cittadino", "CodiceFiscale", "Data", "Vaccino", "id Vaccinazione", "EventiAvversi"});
        this.nomeCentroVaccinale = nomeCentroVaccinale;
    }

    public static GestioneVaccinati getInstance(String nomeCentroVaccinale){
        if (istanza == null) {
            istanza = new GestioneVaccinati(nomeCentroVaccinale);
        }
        return istanza;
    }

    /**
     * il metodo registra il CittadinoVaccinato in vaccinatiVector
     *
     */
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

    public short nextIdUniv() {
        GestioneCsv vaccinati = new GestioneCsv("Vaccinati.dati",new String[]{"Id Univoco", "Centro Vaccinale", "Id Interno"});
        vaccinati.verificaFile();
        short idUniv = 0;
        while (vaccinati.ricercaIdEsiste(""+idUniv)!=false){
            idUniv++;
        }
        return idUniv;
    }

    /**
     * il metodo inserisce un cittadinoVaccinato in vaccinatiVector
     * @param vaccinatiVector
     */
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

    /**
     * il metodo inserisce un cittadinoVaccinato in vaccinatiVector
     * @param
     */
    public void registraVaccinato(CittadinoVaccinato cittadinoVaccinato) {
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
            linea.append(eventiAvversiToString(new ArrayList<EventoAvverso>()));
            scritturaFile(linea.toString());
        GestioneCsv vaccinati = new GestioneCsv("Vaccinati.dati",new String[]{"Id Univoco", "Centro Vaccinale", "Id Interno"});
        vaccinati.verificaFile();
        //scrittura parallela dei dati univoci nel file Vaccinati.dati
        vaccinati.scritturaFile(cittadinoVaccinato.getIdVaccinazione()+SEPARATORE_CSV+nomeCentroVaccinale+SEPARATORE_CSV+ cittadinoVaccinato.getId());
    }

    public void inserisciEventiAvversi(int index, EventoAvverso eventoAvverso) {
        ArrayList<CittadinoVaccinato> listaCittadinoVaccinato = getCittadiniVaccinati();
        deleteAndCreate();
        for (int i = 0; i < listaCittadinoVaccinato.size(); i++) {
            StringBuffer linea = new StringBuffer();
            linea.append(listaCittadinoVaccinato.get(i).getId());
            linea.append(SEPARATORE_CSV);
            linea.append(listaCittadinoVaccinato.get(i).getNomeCentroVaccinale());
            linea.append(SEPARATORE_CSV);
            linea.append(listaCittadinoVaccinato.get(i).getNomeCittadino());
            linea.append(SEPARATORE_CSV);
            linea.append(listaCittadinoVaccinato.get(i).getCognomeCittadino());
            linea.append(SEPARATORE_CSV);
            linea.append(listaCittadinoVaccinato.get(i).getCodiceFiscaleCittadino());
            linea.append(SEPARATORE_CSV);
            linea.append(listaCittadinoVaccinato.get(i).getDataVaccinazione());
            linea.append(SEPARATORE_CSV);
            linea.append(listaCittadinoVaccinato.get(i).getVaccinoSomministrato());
            linea.append(SEPARATORE_CSV);
            linea.append(listaCittadinoVaccinato.get(i).getIdVaccinazione());
            linea.append(SEPARATORE_CSV);
            if (index == i) {
                ArrayList<EventoAvverso> eventoAvversi = new ArrayList<>();
                eventoAvversi.add(eventoAvverso);
                linea.append(eventiAvversiToString(eventoAvversi));
            } else {
                linea.append(eventiAvversiToString(listaCittadinoVaccinato.get(i).getEventiAvversi()));
            }
            scritturaFile(linea.toString());
        }
    }

    public ArrayList<CittadinoVaccinato> getCittadiniVaccinati() {
        ArrayList<CittadinoVaccinato> listaCittadinoVaccinato = new ArrayList<>();
        ArrayList<String> listaRigheFile = letturaFile();
        /*
        Ricostruzione oggetti scritti nel file
         */
        for (String line: listaRigheFile) {
            //System.out.println(line);
            String[] rawObject = line.split(SEPARATORE_CSV);
            CittadinoVaccinato cittadinoVaccinato = new CittadinoVaccinato();
            cittadinoVaccinato.setId(Short.parseShort(rawObject[0]));
            cittadinoVaccinato.setNomeCentroVaccinale(rawObject[1]);
            cittadinoVaccinato.setNomeCittadino(rawObject[2]);
            cittadinoVaccinato.setCognomeCittadino(rawObject[3]);
            cittadinoVaccinato.setCodiceFiscaleCittadino(rawObject[4]);
            cittadinoVaccinato.setDataVaccinazione(rawObject[5]);
            cittadinoVaccinato.setVaccinoSomministrato(rawObject[6]);
            cittadinoVaccinato.setIdVaccinazione(Short.parseShort(rawObject[7]));
            cittadinoVaccinato.setEventiAvversi(eventiAvversiToArray(rawObject[8]));
            listaCittadinoVaccinato.add(cittadinoVaccinato);
        }
        return listaCittadinoVaccinato;
    }

    public String eventiAvversiToString (ArrayList<EventoAvverso> eventiAvversi) {
        StringBuffer linea = new StringBuffer();
        for (EventoAvverso eventoAvverso: eventiAvversi) {
            linea.append(eventoAvverso.getEvento());
            linea.append(".");
            linea.append(eventoAvverso.getSeverità());
            linea.append(".");
            linea.append(eventoAvverso.getNote());
            linea.append("|");
        }
        return linea.toString();
    }

    public ArrayList<EventoAvverso> eventiAvversiToArray (String eventi) {
        ArrayList<EventoAvverso> eventiAvversi = new ArrayList<>();
        String[] rawList = eventi.split("|");
        for (String evento: rawList) {
            String[] rawObject = evento.split(".");
            EventoAvverso eventoAvverso = new EventoAvverso();
            eventoAvverso.setEvento(rawObject[0]);
            eventoAvverso.setSeverità(Byte.parseByte(rawObject[1]));
            eventoAvverso.setNote(rawObject[2]);
            eventiAvversi.add(eventoAvverso);
        }
        return eventiAvversi;
    }

}
