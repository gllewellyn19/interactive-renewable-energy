package ire.view.buttons;

import ire.view.StartEnergyTypeable;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

/**
 * This button takes the user to the general hydroelectric power page
 *
 * @author Grace Llewellyn
 */
public class HydroButton extends BoardButton {

  private final StartEnergyTypeable startEnergyTypeable;

  public HydroButton(ResourceBundle resources, StartEnergyTypeable startEnergyTypeable) {
    super(resources, new Button(), "HydroButton");
    this.startEnergyTypeable = startEnergyTypeable;
    initializeButton();
  }

  /**
   * initializes button to start hydro screen when pressed
   */
  @Override
  protected void initializeButton() {
    super.initializeButton();
    super.getCurrButton().setOnAction(event -> startEnergyTypeable.startNewEnergyType("hydro"));
  }
}
