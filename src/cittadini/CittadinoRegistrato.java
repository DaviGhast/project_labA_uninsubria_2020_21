package cittadini;

/**
 * Classe <code>CittadinoRegistrato</code>
 * <p>
 *     Questa classe ha lo scopo di descrivere l'oggetto CittadinoRegistrato
 * </p>
 * @author Davide Mainardi
 * @author Marc Cepraga
 * @author Luca Muggiasca
 * @author Brenno Re
 */
public class CittadinoRegistrato {

    private String nome;
    private String cognome;
    private String email;
    private String userid;
    private String password;
    private short idVaccinazione;

    public CittadinoRegistrato(String nome, String cognome, String email, String userid, String password, short idVaccinazione) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.userid = userid;
        this.password = password;
        this.idVaccinazione = idVaccinazione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getIdVaccinazione() {
        return idVaccinazione;
    }

    public void setIdVaccinazione(short idVaccinazione) {
        this.idVaccinazione = idVaccinazione;
    }
}
