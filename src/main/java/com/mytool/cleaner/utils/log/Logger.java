package com.mytool.cleaner.utils.log;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class Logger {

  private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(Logger.class.getName());

  public static void error(String s) {
    LOGGER.severe(s);
  }

  public static void warn(String s) {
    LOGGER.warning(s);
  }

  public static void info(String s) {
    LOGGER.info(s);
  }

  public static void debug(String s) {
    LOGGER.fine(s);
  }

  public static void finest(String s) {
    LOGGER.finest(s);
  }

  public static void init(String logPath, boolean verbose) {
    System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tc][%2$s][%4$s] %5$s%n"); // [Date][Logger Name][Level] Message
    LOGGER.setLevel(Level.ALL);
    LOGGER.setUseParentHandlers(false);
    try {
      ConsoleHandler consoleHandler = new ConsoleHandler();
      consoleHandler.setLevel(verbose ? Level.FINE : Level.INFO);
      consoleHandler.setFormatter(new SimpleFormatter());
      LOGGER.addHandler(consoleHandler);

      // 10485760L：表示10M
      String filePattern = logPath + "%g.log";
      FileHandler fileHandler = new FileHandler(filePattern, 10485760L, 1, true);
      fileHandler.setLevel(Level.ALL);
      fileHandler.setFormatter(new SimpleFormatter());
      LOGGER.addHandler(fileHandler);
    } catch (Exception e) {
      LOGGER.severe("Error: Logger couldn't be created:" + e);
    }
  }

}
