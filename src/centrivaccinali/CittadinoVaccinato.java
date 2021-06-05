package centrivaccinali;

/**
 * @author Davide Mainardi
 * @author Marc Cepraga
 * @author Luca Muggiasca
 * @author Brenno Re
 */

public class CittadinoVaccinato {

    String nomeCentroVaccinale, nomeCittadino, cognomeCittadino, codiceFiscaleCittadino, dataVaccinazione, vaccinoSomministrato;
    short id, idVaccinazione;

    /**
     * Metodo costruttore per le istanze della classe CittadinoVaccinato
     *
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

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public short getIdVaccinazione() {
        return idVaccinazione;
    }

    public void setIdVaccinazione(short idVaccinazione) {
        this.idVaccinazione = idVaccinazione;
    }
}


