package ire.view.buttons;

import ire.view.StartEnergyTypeable;
import java.util.ResourceBundle;
import javafx.scene.control.Button;


/**
 * This button takes the user to the general wind energy power page
 *
 * @author Grace Llewellyn
 */
public class WindButton extends BoardButton {

  private final StartEnergyTypeable startEnergyTypeable;

  public WindButton(ResourceBundle resources, StartEnergyTypeable startEnergyTypeable) {
    super(resources, new Button(), "WindButton");
    this.startEnergyTypeable = startEnergyTypeable;
    initializeButton();
  }

  /**
   * initializes button to start wind screen when pressed
   */
  @Override
  protected void initializeButton() {
    super.initializeButton();
    super.getCurrButton().setOnAction(event -> startEnergyTypeable.startNewEnergyType("wind"));
  }

}
