import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * La MainClass è la classe principale del programma.
 * <p>
 *     Questa classe ha lo scopo di lanciare la prima pagina della GUI.
 * </p>
 * <p>
 *     La prima finestra della GUI, è la Homepage
 * </p>
 * @author Davide Mainardi
 * @author Marc Cepraga
 * @author Luca Muggiasca
 * @author Brenno Re
 */

public class MainClass extends Application {

    /**
     * Metodo Start
     * <p>
     *     In questo metodo viene caricato il file della GUI HomePage.fxml per essere avviato
     * </p>
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("home/HomePage.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}