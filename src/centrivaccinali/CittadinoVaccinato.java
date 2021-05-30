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

    public void CentroVaccinale(String nomeCentroVaccinale, String nomeCittadino, String cognomeCittadino, String codiceFiscaleCittadino, String dataVaccinazione, String vaccinoSomministrato, int idVaccinazione) {
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

    public void setNomeCittadino(String nomeCittadino) { this.nomeCittadino = nomeCittadino; }

    public String getCognomeCittadino() {return cognomeCittadino; }

    public void setCognomeCittadino(String cognomeCittadino) {this.cognomeCittadino = cognomeCittadino; }

    public String getcodiceFiscaleCittadino() {return codiceFiscaleCittadino; }

    public void setcodiceFiscaleCittadino(String codiceFiscaleCittadino) {this.codiceFiscaleCittadino = codiceFiscaleCittadino; }

    public String getdataVaccinazione() {return dataVaccinazione; }

    public void setdataVaccinazione(String dataVaccinazione) {this.dataVaccinazione = dataVaccinazione; }

    public String getvaccinoSomministrato() {return vaccinoSomministrato; }

    public void setvaccinoSomministrato(String vaccinoSomministrato) {this.vaccinoSomministrato = vaccinoSomministrato; }

    public int getidVaccinazione() {return idVaccinazione; }

    public void setidVaccinazione(int idVaccinazione) {this.idVaccinazione = idVaccinazione; }

}


