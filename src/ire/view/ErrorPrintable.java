package ire.view;

/**
 * This interface enables error printing throughout the program
 *
 * @author Grace Llewellyn
 */
public interface ErrorPrintable {

  void printErrorMessageAlert(String key);
  void printErrorMessageAlert(String key, String additionalMessage);

}
