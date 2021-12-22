package centrivaccinali;

import gestionefile.GestioneCsv;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

/**
 * la classe si occupa di gestire i centri vaccinali
 * @author Davide Mainardi
 * @author Marc Cepraga
 * @author Luca Muggiasca
 * @author Brenno Re
 */
public class GestioneCentriVaccinali extends GestioneCsv {

    /**
     *l' <code>istanza</code> indica l'oggetto della classe GestioneCentriVaccinali
     */
    private static GestioneCentriVaccinali istanza;

        /**
         * Costruttore per le istanze della classe GestioneCentriVaccinali
         */
    private GestioneCentriVaccinali() throws URISyntaxException {
            super("CentriVaccinali.dati", new String[]{"Id","Nome Centro","Qual Indirizzo","Nome Indirizzo","Civico","Comune","Provincia","Cap","Tipologia"});
        }

    /**
     * design pattern singleton
     * @return istanza di GestioneCentriVaccinali
     */
    public static GestioneCentriVaccinali getInstance() throws URISyntaxException {
        if (istanza == null) {
            istanza = new GestioneCentriVaccinali();
        }
        return istanza;
    }

    /**
     * il metodo serve all'operatore per registrare un nuovo centro vaccinale
     * @param centroVaccinale
     */
    public void registraCentroVaccinale(CentroVaccinale centroVaccinale) {
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
            scritturaFile(linea.toString());
    }

    /**
     * il metodo verifica l'esistenza di un centroVaccinale
     * @param nomeCentroVaccinale
     * @return esiste o non esiste
     */
    public boolean cercaCentroEsiste(String nomeCentroVaccinale){
        boolean exist = false;
        ArrayList<CentroVaccinale> lista = searchCentroByName(nomeCentroVaccinale);
        for (CentroVaccinale centroVaccinale: lista) {
            if (centroVaccinale.getNomeCentroVaccinale().equals(nomeCentroVaccinale))
                exist = true;
        }
        return exist;
    }

    /**
     * legge ogni riga dal file centroVaccinale.csv e per ognuna di essa istanzia oggetti di tipo centroVaccinale
     * @return listaCentriVaccinali e' una lista di CentriVaccinali
     */
    public ArrayList<CentroVaccinale> getCentriVaccinali(){
        ArrayList<CentroVaccinale> listaCentriVaccinali = new ArrayList<>();
        ArrayList<String> listaRigheFile = letturaFile();
        /*
        Ricostruzione oggetti scritti nel file
         */
        for (String line: listaRigheFile) {
            //System.out.println(line);
            String[] rawObject = line.split(SEPARATORE_CSV);
            CentroVaccinale centroVaccinale = new CentroVaccinale();
            centroVaccinale.setId(Short.parseShort(rawObject[0]));
            centroVaccinale.setNomeCentroVaccinale(rawObject[1]);
            centroVaccinale.setQualificatoreIndirizzo(rawObject[2]);
            centroVaccinale.setNomeIndirizzo(rawObject[3]);
            centroVaccinale.setNumeroCivico(Integer.parseInt(rawObject[4]));
            centroVaccinale.setComune(rawObject[5]);
            centroVaccinale.setSiglaProvincia(rawObject[6]);
            centroVaccinale.setCap(Integer.parseInt(rawObject[7]));
            centroVaccinale.setTipologia(rawObject[8]);
            listaCentriVaccinali.add(centroVaccinale);
        }
        return listaCentriVaccinali;
    }

    /**
     * il metodo cerca all'interno del file centro Vaccinale utilizzando come parametro di ricerca il nome del centro vaccinale stesso
     * @param nomeCentroVaccinale è il parametro di ricerca
     * @return ListaRisultati centri vaccinali trovati nel file csv
     */

    public ArrayList<CentroVaccinale> searchCentroByName(String nomeCentroVaccinale) {
        ArrayList<CentroVaccinale> listaCentriVaccinali = getCentriVaccinali();
        ArrayList<CentroVaccinale> listaRisultati = new ArrayList<>();
        for (CentroVaccinale centroVaccinale: listaCentriVaccinali) {
            centroVaccinale.toString();
            if (centroVaccinale.getNomeCentroVaccinale().toLowerCase().contains(nomeCentroVaccinale.toLowerCase()))
                listaRisultati.add(centroVaccinale);
        }
        return listaRisultati;
    }

    /**
     * il metodo si occupa di cercare il centro vaccinale di interesse tramite comune e tipologia all'interno del file csv
     * @param comune il nome dell comune
     * @param tipologia il nome della tipologia
     * @return listarisultati contiene i centri vaccinali trovati
     */
    public ArrayList<CentroVaccinale> searchCentroByComuneAndTipologia(String comune, String tipologia) {
        ArrayList<CentroVaccinale> listaCentriVaccinali = getCentriVaccinali();
        ArrayList<CentroVaccinale> listaRisultati = new ArrayList<>();
        for (CentroVaccinale centroVaccinale: listaCentriVaccinali) {
            if (centroVaccinale.getComune().toLowerCase().contains(comune.toLowerCase()) | centroVaccinale.getTipologia().toLowerCase().contains(tipologia.toLowerCase()))
                listaRisultati.add(centroVaccinale);
        }
        return listaRisultati;
    }

}


