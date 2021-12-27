package centrivaccinali;

import java.util.ArrayList;

/**
 * classe oggetto per i cittadini vaccinati
 * @author Davide Mainardi
 * @author Marc Cepraga
 * @author Luca Muggiasca
 * @author Brenno Re
 */

public class CittadinoVaccinato {
    /**
     * <code>nomeCentroVaccinale</code> indica il nome del centrovaccinale
     */
    private String nomeCentroVaccinale;
    /**
     * <code>nomeCittadino</code> indica il nome del cittadino
     */
    private String nomeCittadino;
    /**
     * <code>cognomeCittadino</code> indica il cognome del cittadino
     */
    private String cognomeCittadino;
    /**
     * <code>codiceFiscaleCittadino</code> indica il codice fiscale del cittadino
     */
    private String codiceFiscaleCittadino;
    /**
     * <code>dataVaccinazione</code> indica la data della vaccinazione
     */
    private String dataVaccinazione;
    /**
     * <code>vaccinoSoministrato</code> indica il vaccinoSomministrato
     */
    private String vaccinoSomministrato;
     /**
     * <code>id</code> indica l'id generico
     */
    private short id;
    /**
     * <code>idVaccinazione</code> indica l'id vaccinazione
     */
    private short idVaccinazione;
    /**
     * <code>eventiAvversi</code> indica gli eventi avversi post vaccino
     */
    private ArrayList<EventoAvverso> eventiAvversi = new ArrayList<>();


    /**
     * Costruttore per le istanze della classe CittadinoVaccinato
     * @param nomeCentroVaccinale
     * @param nomeCittadino
     * @param cognomeCittadino
     * @param codiceFiscaleCittadino
     * @param dataVaccinazione
     * @param vaccinoSomministrato
     * @param id
     * @param idVaccinazione
     */

    public CittadinoVaccinato(short id, String nomeCentroVaccinale, String nomeCittadino, String cognomeCittadino, String codiceFiscaleCittadino, String dataVaccinazione, String vaccinoSomministrato, short idVaccinazione) {
        this.id = id;
        this.nomeCentroVaccinale = nomeCentroVaccinale;
        this.nomeCittadino = nomeCittadino;
        this.cognomeCittadino = cognomeCittadino;
        this.codiceFiscaleCittadino = codiceFiscaleCittadino;
        this.dataVaccinazione = dataVaccinazione;
        this.vaccinoSomministrato = vaccinoSomministrato;
        this.idVaccinazione = idVaccinazione;
    }

    /**
     * costruttore vuoto
     */
    public CittadinoVaccinato() {

    }

    /**
     * @return nomeCentroVaccinale indica il nome del centro vaccinale
     */
    public String getNomeCentroVaccinale() {

     return nomeCentroVaccinale;
    }

    /**
     * @param nomeCentroVaccinale indica il nome del centro vaccinale
     */
    public void setNomeCentroVaccinale(String nomeCentroVaccinale) {
        this.nomeCentroVaccinale = nomeCentroVaccinale;
    }

    /**
     * @return  nomeCittadino indica il nome del cittadino
     */
    public String getNomeCittadino() {
        return nomeCittadino;
    }

    /**
     * @param nomeCittadino indica il nome del cittadino
     */
    public void setNomeCittadino(String nomeCittadino) {
        this.nomeCittadino = nomeCittadino;
    }

    /**
     * @return  conomeCittadino indica il conome del cittadino
     */
    public String getCognomeCittadino() {
        return cognomeCittadino;
    }

    /**
     * @param cognomeCittadino indica il cognome del cittadino
     */
    public void setCognomeCittadino(String cognomeCittadino) {
        this.cognomeCittadino = cognomeCittadino;
    }

    /**
     * @return  nomeCittadino indica il nome del cittadino
     */
    public String getCodiceFiscaleCittadino() {
        return codiceFiscaleCittadino;
    }

    /**
     * @param codiceFiscaleCittadino  indica il codice fiscale del cittadino
     */
    public void setCodiceFiscaleCittadino(String codiceFiscaleCittadino) {
        this.codiceFiscaleCittadino = codiceFiscaleCittadino;
    }

    /**
     * @return dataVaccinazione  indica la data di vaccinazione del cittadino
     */
    public String getDataVaccinazione() {
        return dataVaccinazione;
    }

    /**
     * @param dataVaccinazione indica la data di vaccinazione del cittadino
     */
    public void setDataVaccinazione(String dataVaccinazione) {
        this.dataVaccinazione = dataVaccinazione;
    }

    /**
     * @return dataVaccinazione  indica la data di vaccinazione del cittadino
     */
    public String getVaccinoSomministrato() {
        return vaccinoSomministrato;
    }

    /**
     * @param vaccinoSomministrato indica il vaccino somministrato al cittadino
     */
    public void setVaccinoSomministrato(String vaccinoSomministrato) {
        this.vaccinoSomministrato = vaccinoSomministrato;
    }
    /**
     * @return id indica l'id univoco per il cittadino vaccinato del file.csv
     */
    public short getId() {
        return id;
    }

    /**
     * @param id indica l'id univoco per il cittadino vaccinato del file.csv
     */
    public void setId(short id) {
        this.id = id;
    }

    /**
     * @return idVaccinazione indica l'id della vaccinaione per potersi registrale al portale
     */
    public short getIdVaccinazione() {
        return idVaccinazione;
    }

    /**
     * @param idVaccinazione indica l'id della vaccinaione per potersi registrale al portale
     */
    public void setIdVaccinazione(short idVaccinazione) {
        this.idVaccinazione = idVaccinazione;
    }

    /**
     * @return eventiAvversi indica gli eventi avversi del vaccino
     */
    public ArrayList<EventoAvverso> getEventiAvversi() {
        return eventiAvversi;
    }

    /**
     * @param eventiAvversi indica gli eventi avversi del vaccino
     */
    public void setEventiAvversi(ArrayList<EventoAvverso> eventiAvversi) {
        this.eventiAvversi = eventiAvversi;
    }
}


