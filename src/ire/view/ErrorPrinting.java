package ire.view;

import ire.Main;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * This class helps divide the responsibility of gameView to better follow single responsibility
 * principle and does the error printing for the game
 *
 * @author Grace Llewellyn
 */
public class ErrorPrinting implements ErrorPrintable{

  public static final String DEFAULT_MESSAGE_MISSING_EXCEPTIONS = "The file for resources exceptions"
      + "is missing";
  public static final String EXCEPTIONS_FOLDER = "exceptions/";
  public static final String EXCEPTIONS_FILE = "Exceptions";

  private LanguageControls languageControls;

  public ErrorPrinting(LanguageControls languageControls) {
    this.languageControls = languageControls;
  }

  /**
   * @param key - key contained in exception .properties file with exception message as value Prints
   *            the given error message as an alert. Given a key that corresponds to a resource
   *            bundle with the correct error message
   */
  public void printErrorMessageAlert(String key) {
    createAlert(key);
  }

  /**
   * Formulates an error to be printed.
   * @param key gets exception type
   * @param additionalMessage describes the exception instance
   */
  public void printErrorMessageAlert(String key, String additionalMessage) {
    try {
      Alert errorAlert = new Alert(AlertType.ERROR);
      ResourceBundle exceptions = ResourceBundle.getBundle(Main.DEFAULT_RESOURCES_PACKAGE +
          EXCEPTIONS_FOLDER + languageControls.getLanguage().toLowerCase() + EXCEPTIONS_FILE);
      errorAlert.setHeaderText(exceptions.getString("headerForExceptionsAlerts"));
      errorAlert.setContentText(String.format(exceptions.getString(key), additionalMessage));
      errorAlert.showAndWait();
    } catch (MissingResourceException e) {
      defaultAlertIfNoExceptionsBundle();
    }
  }

  /**
   * @param key-              key contained in exception .properties file with exception message as
   *                          value
   */
  private void createAlert(String key) {
    try {
      Alert errorAlert = new Alert(AlertType.ERROR);
      ResourceBundle exceptions = ResourceBundle.getBundle(Main.DEFAULT_RESOURCES_PACKAGE +
          EXCEPTIONS_FOLDER + languageControls.getLanguage().toLowerCase() +
          EXCEPTIONS_FILE);
      errorAlert.setHeaderText(exceptions.getString("headerForExceptionsAlerts"));
      errorAlert.setContentText(exceptions.getString(key));
      errorAlert.setResizable(true);
      errorAlert.showAndWait();
    } catch (MissingResourceException e) {
      defaultAlertIfNoExceptionsBundle();
    }
  }

  /*
   * The default alert if cannot find the exceptions bundle because cannot get the exceptions to
   * print so must use default values
   */
  private void defaultAlertIfNoExceptionsBundle() {
    Alert errorAlert = new Alert(AlertType.ERROR);
    errorAlert.setContentText(DEFAULT_MESSAGE_MISSING_EXCEPTIONS);
    errorAlert.showAndWait();
  }

}
