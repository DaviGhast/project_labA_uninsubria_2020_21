package centrivaccinali;

import gestionefile.GestioneCsv;

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
            ArrayList<EventoAvverso> eventiAvversi = new ArrayList<EventoAvverso>();
            eventiAvversi.add(new EventoAvverso());
            linea.append(eventiAvversiToString(eventiAvversi));
            scritturaFile(linea.toString());
        GestioneCsv vaccinati = new GestioneCsv("Vaccinati.dati",new String[]{"Id Univoco", "Centro Vaccinale", "Id Interno"});
        vaccinati.verificaFile();
        //scrittura parallela dei dati univoci nel file Vaccinati.dati
        vaccinati.scritturaFile(cittadinoVaccinato.getIdVaccinazione()+SEPARATORE_CSV+nomeCentroVaccinale+SEPARATORE_CSV+ cittadinoVaccinato.getId());
    }

    public void inserisciEventiAvversi(short idVaccinato, EventoAvverso eventoAvverso) {
        ArrayList<CittadinoVaccinato> listaCittadiniVaccinati = getCittadiniVaccinati();
        deleteAndCreate();
        for (CittadinoVaccinato cittadinoVaccinato: listaCittadiniVaccinati) {
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
            ArrayList<EventoAvverso> eventiAvversi = cittadinoVaccinato.getEventiAvversi();
            if (cittadinoVaccinato.getIdVaccinazione() == idVaccinato){
                eventiAvversi.add(eventoAvverso);
            }
            linea.append(eventiAvversiToString(eventiAvversi));
            scritturaFile(linea.toString());
        }
    }

    public ArrayList<CittadinoVaccinato> getCittadiniVaccinati() {
        ArrayList<CittadinoVaccinato> listaCittadiniVaccinati = new ArrayList<>();
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
            //System.out.println(rawObject[8]);
            if (rawObject[8].contains("null")){
                ArrayList<EventoAvverso> eventiAvversi = eventiAvversiToArray(rawObject[8]);
                eventiAvversi.clear();
                cittadinoVaccinato.setEventiAvversi(eventiAvversi);
            } else {
                cittadinoVaccinato.setEventiAvversi(eventiAvversiToArray(rawObject[8]));
            }
            listaCittadiniVaccinati.add(cittadinoVaccinato);
        }
        return listaCittadiniVaccinati;
    }

    public String eventiAvversiToString (ArrayList<EventoAvverso> eventiAvversi) {
        StringBuffer linea = new StringBuffer();
        for (EventoAvverso eventoAvverso: eventiAvversi) {
            linea.append(eventoAvverso.getEvento());
            linea.append("#");
            linea.append(eventoAvverso.getSeverita());
            linea.append("#");
            linea.append(eventoAvverso.getNote());
            linea.append("#");
            linea.append("/");
        }
        return linea.toString();
    }

    public ArrayList<EventoAvverso> eventiAvversiToArray (String eventi) {
        ArrayList<EventoAvverso> eventiAvversi = new ArrayList<>();
        //System.out.println(eventi);
        String[] rawList = eventi.split("/");
        for (String evento: rawList) {
            //System.out.println(evento);
            String[] rawObject = evento.split("#");
            EventoAvverso eventoAvverso = new EventoAvverso();
            //System.out.println(rawObject[0]);
            eventoAvverso.setEvento(rawObject[0]);
            eventoAvverso.setSeverita(Byte.parseByte(rawObject[1]));
            eventoAvverso.setNote(rawObject[2]);
            eventiAvversi.add(eventoAvverso);
        }
        return eventiAvversi;
    }

    public ArrayList<InfoEventoAvversoAnonimo> getAllEventiAvversi (ArrayList<CittadinoVaccinato> listaCittadiniVaccinati) {
        ArrayList<InfoEventoAvversoAnonimo> listaEventiAvversi = new ArrayList<>();
        InfoEventoAvversoAnonimo infoEventoAvversoAnonimo;
        for (CittadinoVaccinato cittadinoVaccinato: listaCittadiniVaccinati) {
            for (EventoAvverso eventoAvverso: cittadinoVaccinato.getEventiAvversi()) {
                infoEventoAvversoAnonimo = new InfoEventoAvversoAnonimo();
                infoEventoAvversoAnonimo.setVaccinoSomministrato(cittadinoVaccinato.getVaccinoSomministrato());
                infoEventoAvversoAnonimo.setEvento(eventoAvverso.getEvento());
                infoEventoAvversoAnonimo.setSeverita(eventoAvverso.getSeverita());
                infoEventoAvversoAnonimo.setNote(eventoAvverso.getNote());
                listaEventiAvversi.add(infoEventoAvversoAnonimo);
            }
        }
        return listaEventiAvversi;
    }

}
