package ire.view.animations;

import ire.view.SceneControls;
import ire.view.buttons.BackButton;
import ire.view.buttons.NextButton;
import java.awt.Button;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.input.KeyCode;

public abstract class EnergyAnimation implements Animationable{

  private final ResourceBundle languageResources;
  private final SceneControls sceneControls;
  private int getCurrentAnimation;

  public EnergyAnimation(ResourceBundle languageResources, SceneControls sceneControls) {
    this.languageResources = languageResources;
    this.sceneControls = sceneControls;
  }

  /**
   * Starts the animation by displaying the necessary buttons
   */
  public void startAnimation() {
    if (sceneControls.getRoot().isPresent()) {
      ButtonBase backButton = new BackButton(languageResources, sceneControls).getCurrButton();
      backButton.setLayoutX(0);
      backButton.setLayoutY(0);
      sceneControls.getRoot().get().getChildren().add(backButton);
      ButtonBase nextButton = new NextButton(languageResources, this).
          getCurrButton();
      nextButton.setLayoutX(128);
      nextButton.setLayoutY(0);
      sceneControls.getRoot().get().getChildren().add(nextButton);
    }
  }

  /**
   * Checks if the user entered a cheat key and does the necessary action. For example, the user
   * pressing n moves to the next animation
   *
   * @param code code that could be cheat key
   */
  public void handleKeyInput(KeyCode code) {
    if (code == KeyCode.N) {
      stepToNextAnimation();
    }
  }

  public void stepAnimation(double elapsedTime) {

  }

  @Override
  public abstract void stepToNextAnimation();

  public SceneControls getSceneControls() {
    return sceneControls;
  }
}
