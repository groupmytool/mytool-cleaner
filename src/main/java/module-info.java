module com.mytool.cleaner {

  requires javafx.controls;
  requires javafx.fxml;
  requires java.desktop;
  requires dd.plist;
  requires com.google.gson;
  requires com.github.zafarkhaja.semver;

  opens com.mytool.cleaner.controller to javafx.fxml;
  opens com.mytool.cleaner.controller.uninstall to javafx.fxml;
  opens com.mytool.cleaner.controller.diskspace to javafx.fxml;
  opens com.mytool.cleaner.model to com.google.gson;

  exports com.mytool.cleaner to javafx.graphics;

}