package ire.view.animations;

import ire.view.SceneControls;
import ire.view.buttons.BackButton;
import ire.view.buttons.NextButton;
import java.awt.Button;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;

public abstract class EnergyAnimation implements Animationable{

  private final ResourceBundle languageResources;
  private final SceneControls sceneControls;
  private int getCurrentAnimation;

  public EnergyAnimation(ResourceBundle languageResources, SceneControls sceneControls) {
    this.languageResources = languageResources;
    this.sceneControls = sceneControls;
  }

  /**
   * Starts the animation by displaying the necessary buttons and putting the shapes/ images on the
   * screen
   */
  public void startAnimation() {
    if (sceneControls.getRoot().isPresent()) {
      ButtonBase test = new BackButton(languageResources, sceneControls).getCurrButton();
      test.setLayoutX(20);
      test.setLayoutY(20);
      sceneControls.getRoot().get().getChildren().add(new BackButton(languageResources,
          sceneControls).getCurrInteractiveFeature());
      sceneControls.getRoot().get().getChildren().add(new NextButton(languageResources,
          this).getCurrInteractiveFeature());
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
