package ire.view.buttons;

import ire.view.StartEnergyTypeable;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class WindButton extends BoardButton {

  private StartEnergyTypeable startEnergyTypeable;

  public WindButton(ResourceBundle resources, StartEnergyTypeable startEnergyTypeable) {
    super(resources, new Button(), "WindButton");
    this.startEnergyTypeable = startEnergyTypeable;
    initializeButton();
  }

  /**
   * initializes button to start wind screen when pressed
   */
  @Override
  public void initializeButton() {
    super.initializeButton();
    super.getCurrButton().setOnAction(event -> startEnergyTypeable.startNewEnergyType("wind"));
  }

}
