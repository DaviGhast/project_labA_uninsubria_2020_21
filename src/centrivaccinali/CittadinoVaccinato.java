package centrivaccinali;

/**
 * @author Davide Mainardi
 * @author Marc Cepraga
 * @author Luca Muggiasca
 * @author Brenno Re
 */

public class CittadinoVaccinato {

    String nomeCentroVaccinale, nomeCittadino, cognomeCittadino, codiceFiscaleCittadino, dataVaccinazione, vaccinoSomministrato;
    int idVaccinazione;

    /**
     * Metodo costruttore per le istanze della classe CittadinoVaccinato
     *
     * @param nomeCentroVaccinale
     * @param nomeCittadino
     * @param cognomeCittadino
     * @param codiceFiscaleCittadino
     * @param dataVaccinazione
     * @param vaccinoSomministrato
     * @param idVaccinazione
     */
    public CittadinoVaccinato(String nomeCentroVaccinale, String nomeCittadino, String cognomeCittadino, String codiceFiscaleCittadino, String dataVaccinazione, String vaccinoSomministrato, int idVaccinazione) {
        this.nomeCentroVaccinale = nomeCentroVaccinale;
        this.nomeCittadino = nomeCittadino;
        this.cognomeCittadino = cognomeCittadino;
        this.codiceFiscaleCittadino = codiceFiscaleCittadino;
        this.dataVaccinazione = dataVaccinazione;
        this.vaccinoSomministrato = vaccinoSomministrato;
        this.idVaccinazione = idVaccinazione;
    }

    public String getNomeCentroVaccinale() {
        return nomeCentroVaccinale;
    }

    public void setNomeCentroVaccinale(String nomeCentroVaccinale) {
        this.nomeCentroVaccinale = nomeCentroVaccinale;
    }

    public String getNomeCittadino() {
        return nomeCittadino;
    }

    public void setNomeCittadino(String nomeCittadino) {
        this.nomeCittadino = nomeCittadino;
    }

    public String getCognomeCittadino() {
        return cognomeCittadino;
    }

    public void setCognomeCittadino(String cognomeCittadino) {
        this.cognomeCittadino = cognomeCittadino;
    }

    public String getCodiceFiscaleCittadino() {
        return codiceFiscaleCittadino;
    }

    public void setCodiceFiscaleCittadino(String codiceFiscaleCittadino) {
        this.codiceFiscaleCittadino = codiceFiscaleCittadino;
    }

    public String getDataVaccinazione() {
        return dataVaccinazione;
    }

    public void setDataVaccinazione(String dataVaccinazione) {
        this.dataVaccinazione = dataVaccinazione;
    }

    public String getVaccinoSomministrato() {
        return vaccinoSomministrato;
    }

    public void setVaccinoSomministrato(String vaccinoSomministrato) {
        this.vaccinoSomministrato = vaccinoSomministrato;
    }

    public int getIdVaccinazione() {
        return idVaccinazione;
    }

    public void setIdVaccinazione(int idVaccinazione) {
        this.idVaccinazione = idVaccinazione;
    }

}