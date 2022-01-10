package cittadini;

/**
 * Classe <code>CittadinoRegistrato</code>
 * <p>
 *     Questa classe ha lo scopo di descrivere l'oggetto CittadinoRegistrato
 * </p>
 * @author Davide Mainardi 746490 VA
 * @author Marc Cepraga 744101 VA
 * @author Luca Muggiasca 744565 VA
 * @author Brenno Re 747060 VA
 */
public class CittadinoRegistrato {

    /**
     * <code>nome</code> indica il nome del CittadinoRegistrato
     */
    private String nome;

    /**
     * <code>cognome</code> indica il cognome del CittadinoRegistrato
     */
    private String cognome;

    /**
     * <code>email</code> indica l'email del CittadinoRegistrato
     */
    private String email;

    /**
     * <code>userid</code> indica la userid del CittadinoRegistrato
     */
    private String userid;

    /**
     * <code>password</code> indica la password del CittadinoRegistrato
     */
    private String password;

    /**
     * <code>idVaccinazione</code> indica l'idVaccinazione del CittadinoRegistrato
     */
    private short idVaccinazione;

    /**
     * Costruttore per l'oggetto CittadinoRegistrato
     * @param nome indica il nome del CittadinoRegistrato
     * @param cognome indica il cognome del CittadinoRegistrato
     * @param email indica l'email del CittadinoRegistrato
     * @param userid  indica la userid del CittadinoRegistrato
     * @param password indica la password del CittadinoRegistrato
     * @param idVaccinazione indica l'id di vaccinazione
     */
    public CittadinoRegistrato(String nome, String cognome, String email, String userid, String password, short idVaccinazione) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.userid = userid;
        this.password = password;
        this.idVaccinazione = idVaccinazione;
    }
    /**
     *costruttore vuoto
     */
    public CittadinoRegistrato() {

    }
    /**
     * @return nome del CittadinoRegistrato
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome del CittadinoRegistrato
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return cognome del CittadinoRegistrato
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @param cognome del CittadinoRegistrato
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * @return  email del CittadinoRegistrato
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email del CittadinoRegistrato
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return userid del CittadinoRegistrato
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid del CittadinoRegistrato
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return password del CittadinoRegistrato
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password del CittadinoRegistrato
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return idVaccinazione del CittadinoRegistrato
     */
    public short getIdVaccinazione() {
        return idVaccinazione;
    }

    /**
     * @param idVaccinazione del CittadinoRegistrato
     */
    public void setIdVaccinazione(short idVaccinazione) {
        this.idVaccinazione = idVaccinazione;
    }
}
