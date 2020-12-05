package ire.view.animations;

import ire.view.SceneControls;
import ire.view.buttons.BackButton;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public abstract class EnergyAnimation {

  private final ResourceBundle languageResources;
  private final SceneControls sceneControls;

  public EnergyAnimation(ResourceBundle languageResources, SceneControls sceneControls) {
    this.languageResources = languageResources;
    this.sceneControls = sceneControls;
  }

  public void startAnimation() {
    if (sceneControls.getRoot().isPresent()) {
      sceneControls.getRoot().get().getChildren().add(new BackButton(languageResources,
          sceneControls).getCurrInteractiveFeature());
    }
  }

}
