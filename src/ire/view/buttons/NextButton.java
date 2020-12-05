package ire.view.buttons;

import ire.view.SceneControls;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class NextButton extends BoardButton {

  private SceneControls sceneControls;
  private int currentAnimationScene;

  public NextButton(ResourceBundle resources, SceneControls sceneControls) {
    super(resources, new Button(), "NextButton");
    this.sceneControls = sceneControls;
    initializeButton();
  }

  /**
   * initializes button to go back to the splash screen when pressed
   */
  @Override
  protected void initializeButton() {
    super.initializeButton();
    super.getCurrButton().setOnAction(event -> moveToNextAnimationScene());
  }

  private void moveToNextAnimationScene() {

  }

}
