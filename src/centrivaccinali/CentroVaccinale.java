package centrivaccinali;

public class CentroVaccinale {

    public static void main(String[] args) {

    }
        private String nomeCentroVaccinale, qualificatoreindirizzo, nomeindirizzo, comune, siglaprovincia, tipologia;
        private int numerocivico, cap;

    public
        CentroVaccinale(String nomeCentroVaccinale, String qualificatoreindirizzo, String nomeindirizzo, String comune, String siglaprovincia, String tipologia, int numerocivico, int cap){
            this.nomeCentroVaccinale = nomeCentroVaccinale;
            this.qualificatoreindirizzo = qualificatoreindirizzo;
            this.nomeindirizzo = nomeindirizzo;
            this.comune = comune;
            this.siglaprovincia = siglaprovincia;
            this.tipologia = tipologia;
            this.numerocivico = numerocivico;
            this.cap = cap;
        }

        public String getnomeCentroVaccinale () {
            return nomeCentroVaccinale;
        }

        public void setnomeCentroVaccinale (String nomeCentroVaccinale){
            this.nomeCentroVaccinale = nomeCentroVaccinale;
        }

        public String getqualificatoreindirizzo () {
            return qualificatoreindirizzo;
        }

        public void qualificatoreindirizzo (String qualificatoreindirizzo){
            this.qualificatoreindirizzo = qualificatoreindirizzo;
        }

        public String getnomeindirizzo () {
            return nomeindirizzo;
        }

        public void setnomeindirizzo (String nomeindirizzo){
            this.nomeindirizzo = nomeindirizzo;
        }

        public String getcomune () {
            return comune;
        }

        public void setcomune (String comune){
            this.comune = comune;
        }

        public String getsiglaprovincia () {
            return siglaprovincia;
        }

        public void setsiglaprovincia (String siglaprovincia){
            this.siglaprovincia = siglaprovincia;
        }

        public String gettipologia () {
            return tipologia;
        }

        public void settipologia (String tipologia){
            this.tipologia = tipologia;
        }
        public int getnumerocivico () {
            return numerocivico;
        }

        public void setnumerocivico ( int numerocivico){
            this.numerocivico = numerocivico;
        }
        public int getcap () {
            return cap;
        }

        public void setcap ( int cap){
            this.cap = cap;
        }
    }
