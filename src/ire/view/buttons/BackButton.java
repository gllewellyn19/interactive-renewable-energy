package ire.view.buttons;

import ire.view.SceneControls;
import ire.view.StartEnergyTypeable;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class BackButton extends BoardButton{

  private final SceneControls sceneControls;

  public BackButton(ResourceBundle resources, SceneControls sceneControls) {
    super(resources, new Button(), "BackButton");
    this.sceneControls = sceneControls;
    initializeButton();
  }

  /**
   * initializes button to go back to the splash screen when pressed
   */
  @Override
  protected void initializeButton() {
    super.initializeButton();
    super.getCurrButton().setOnAction(event -> sceneControls.restart());
  }
}
