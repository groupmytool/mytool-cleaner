module com.mytool.cleaner {

  requires com.google.gson;
  requires com.github.zafarkhaja.semver;
  requires dd.plist;
  requires javafx.controls;
  requires java.desktop;
  requires java.logging;

  exports com.mytool.cleaner to javafx.graphics;

}