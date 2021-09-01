module com.uninsubria.testjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens centrivaccinali to javafx.fxml;
    exports centrivaccinali;
}