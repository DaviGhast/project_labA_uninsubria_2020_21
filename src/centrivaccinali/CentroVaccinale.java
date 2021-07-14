package centrivaccinali;

/**
 * Classe oggetto <code>CentroVaccinale</code>
 *
 * @author Davide Mainardi
 * @author Marc Cepraga
 * @author Luca Muggiasca
 * @author Brenno Re
 */
public class CentroVaccinale {

    private short id;
    private String nomeCentroVaccinale, qualificatoreIndirizzo, nomeIndirizzo, comune, siglaProvincia, tipologia;
    private int numeroCivico, cap;

    /**
     * <p>Metodo costruttore per l'oggetto CetroVaccinale</p>
     * @param id
     * @param nomeCentroVaccinale
     * @param qualificatoreIndirizzo
     * @param nomeIndirizzo
     * @param comune
     * @param siglaProvincia
     * @param tipologia
     * @param numeroCivico
     * @param cap
     */
    public CentroVaccinale(short id, String nomeCentroVaccinale, String qualificatoreIndirizzo, String nomeIndirizzo, String comune, String siglaProvincia, String tipologia, int numeroCivico, int cap){
        this.id = id;
        this.nomeCentroVaccinale = nomeCentroVaccinale;
        this.qualificatoreIndirizzo = qualificatoreIndirizzo;
        this.nomeIndirizzo = nomeIndirizzo;
        this.comune = comune;
        this.siglaProvincia = siglaProvincia;
        this.tipologia = tipologia;
        this.numeroCivico = numeroCivico;
        this.cap = cap;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getNomeCentroVaccinale() {
        return nomeCentroVaccinale;
    }

    public void setNomeCentroVaccinale(String nomeCentroVaccinale) {
        this.nomeCentroVaccinale = nomeCentroVaccinale;
    }

    public String getQualificatoreIndirizzo() {
        return qualificatoreIndirizzo;
    }

    public void setQualificatoreIndirizzo(String qualificatoreIndirizzo) {
        this.qualificatoreIndirizzo = qualificatoreIndirizzo;
    }

    public String getNomeIndirizzo() {
        return nomeIndirizzo;
    }

    public void setNomeIndirizzo(String nomeIndirizzo) {
        this.nomeIndirizzo = nomeIndirizzo;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getSiglaProvincia() {
        return siglaProvincia;
    }

    public void setSiglaProvincia(String siglaProvincia) {
        this.siglaProvincia = siglaProvincia;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public int getNumeroCivico() {
        return numeroCivico;
    }

    public void setNumeroCivico(int numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    @Override
    public String toString() {
        return "CentroVaccinale{" +
                "id=" + id +
                ", nomeCentroVaccinale='" + nomeCentroVaccinale + '\'' +
                ", qualificatoreIndirizzo='" + qualificatoreIndirizzo + '\'' +
                ", nomeIndirizzo='" + nomeIndirizzo + '\'' +
                ", comune='" + comune + '\'' +
                ", siglaProvincia='" + siglaProvincia + '\'' +
                ", tipologia='" + tipologia + '\'' +
                ", numeroCivico=" + numeroCivico +
                ", cap=" + cap +
                '}';
    }
}

