package ire.view.buttons;

import ire.view.StartEnergyTypeable;
import java.util.ResourceBundle;
import javafx.scene.control.Button;


/**
 * This button takes the user to the general solar power page
 *
 * @author Grace Llewellyn
 */
public class SolarButton extends BoardButton{

  private final StartEnergyTypeable startEnergyTypeable;

  public SolarButton(ResourceBundle resources, StartEnergyTypeable startEnergyTypeable) {
    super(resources, new Button(), "SolarButton");
    this.startEnergyTypeable = startEnergyTypeable;
    initializeButton();
  }

  /**
   * initializes button to start solar screen when pressed
   */
  @Override
  protected void initializeButton() {
    super.initializeButton();
    super.getCurrButton().setOnAction(event -> startEnergyTypeable.startNewEnergyType("solar"));
  }

}
