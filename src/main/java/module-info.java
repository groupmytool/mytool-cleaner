module com.mytool.cleaner {

  requires javafx.controls;
  requires java.desktop;
  requires dd.plist;
  requires com.google.gson;
  requires com.github.zafarkhaja.semver;

  exports com.mytool.cleaner to javafx.graphics;

}