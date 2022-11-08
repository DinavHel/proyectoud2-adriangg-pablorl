module com.example.projectud1_agg_prl {
    requires javafx.controls;
    requires javafx.fxml;

    requires json.simple;
    requires com.fasterxml.jackson.databind;
	requires java.sql;


    opens com.example.projectud1_agg_prl to javafx.fxml;
    exports com.example.projectud1_agg_prl;
}