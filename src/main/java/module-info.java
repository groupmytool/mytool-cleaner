module com.mytool.cleaner {

    requires javafx.controls;
    requires javafx.fxml;
    requires darculafx;

    opens com.mytool.cleaner to javafx.fxml;
    opens com.mytool.cleaner.controller to javafx.fxml;

    exports com.mytool.cleaner;
  opens com.mytool.cleaner.controller.module to javafx.fxml;
    opens com.mytool.cleaner.controller.uninstall to javafx.fxml;

}