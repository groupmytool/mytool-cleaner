module com.mytool.cleaner {

  requires javafx.controls;
  requires javafx.fxml;
  requires java.desktop;
  requires dd.plist;

  opens com.mytool.cleaner.controller to javafx.fxml;
  opens com.mytool.cleaner.controller.module to javafx.fxml;
  opens com.mytool.cleaner.controller.uninstall to javafx.fxml;

  exports com.mytool.cleaner to javafx.graphics;

}