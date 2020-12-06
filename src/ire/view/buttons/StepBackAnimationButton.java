package ire.view.buttons;

import ire.view.animations.Animationable;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class StepBackAnimationButton extends BoardButton {

  private final Animationable animationable;

  public StepBackAnimationButton(ResourceBundle resources, Animationable animationable) {
    super(resources, new Button(), "StepBackAnimationButton");
    this.animationable = animationable;
    initializeButton();
  }

  /**
   * initializes button to go back to the splash screen when pressed
   */
  @Override
  protected void initializeButton() {
    super.initializeButton();
    super.getCurrButton().setOnAction(event -> animationable.backToLastAnimation());
  }

}
