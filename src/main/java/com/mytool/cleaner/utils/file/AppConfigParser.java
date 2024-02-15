package com.mytool.cleaner.utils.file;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppConfigParser {

  public static HashMap<String, ArrayList<HashMap<String, List<String>>>> appConfMap = new HashMap<>();

  public static void parseAppConfig() {
    // Get the URL to the resources/conf directory
    URL confDirUrl = AppConfigParser.class.getResource("/conf");
    if (confDirUrl == null) {
      System.err.println("Could not find conf directory");
      return;
    }

    // Read all files in the directory
    // Note: This only works if the conf directory is not inside a JAR file
    try {
      Gson gson = new Gson();
      Files.list(Paths.get(confDirUrl.toURI()))
          .filter(file -> file.getFileName().toString().endsWith(".json"))
          .forEach(file -> {
            try (Reader reader = Files.newBufferedReader(file)) {
              // Convert JSON File to Java Object
              Type type = new TypeToken<ArrayList<HashMap<String, List<String>>>>() {
              }.getType();
              ArrayList<HashMap<String, List<String>>> configArrayList = gson.fromJson(reader, type);
              String configName = file.getFileName().toString();
              String cfBundleIdentifier = configName.substring(0, configName.lastIndexOf("."));
              appConfMap.put(cfBundleIdentifier, configArrayList);
            } catch (IOException e) {
              e.printStackTrace();
            }
          });
    } catch (IOException | URISyntaxException e) {
      System.err.println("Error reading files from conf directory: " + e.getMessage());
    }
  }

}
