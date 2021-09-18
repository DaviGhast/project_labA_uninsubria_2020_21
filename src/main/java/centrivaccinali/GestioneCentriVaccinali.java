package centrivaccinali;

import gestionefile.GestioneCsv;

import java.io.*;
import java.util.*;

public class GestioneCentriVaccinali extends GestioneCsv {

    private static GestioneCentriVaccinali istanza;

        /**
         * Metodo costruttore <code>GestioneCSV</code>
         *
         */
    private GestioneCentriVaccinali() {
            super("CentriVaccinali.dati", new String[]{"Id","Nome Centro","Qual Indirizzo","Nome Indirizzo","Civico","Comune","Provincia","Cap","Tipologia"});
        }

    public static GestioneCentriVaccinali getInstance(){
        if (istanza == null) {
            istanza = new GestioneCentriVaccinali();
        }
        return istanza;
    }

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

