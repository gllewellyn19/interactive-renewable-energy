package ire.view.buttons;

import ire.view.SceneControls;
import ire.view.animations.Animationable;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class NextButton extends BoardButton {

  private Animationable animationable;

  public NextButton(ResourceBundle resources, Animationable animationable) {
    super(resources, new Button(), "NextButton");
    this.animationable = animationable;
    initializeButton();
  }

  /**
   * initializes button to go back to the splash screen when pressed
   */
  @Override
  protected void initializeButton() {
    super.initializeButton();
    super.getCurrButton().setOnAction(event -> animationable.stepToNextAnimation());
  }

}
