package ire.view.buttons;

import ire.view.SceneControls;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class GoToAnimationButton extends BoardButton {

  private SceneControls sceneControls;

  public GoToAnimationButton(ResourceBundle resources, SceneControls sceneControls) {
    super(resources, new Button(), "GoToAnimationButton");
    this.sceneControls = sceneControls;
    initializeButton();
  }

  /**
   * initializes button to go back to the splash screen when pressed
   */
  @Override
  protected void initializeButton() {
    super.initializeButton();
    super.getCurrButton().setOnAction(event -> sceneControls.createAnimationScreen());
  }

}
