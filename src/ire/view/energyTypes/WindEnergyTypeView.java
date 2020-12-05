package ire.view.energyTypes;

import ire.view.SceneControls;
import ire.view.animations.HydroEnergyAnimation;
import ire.view.animations.WindEnergyAnimation;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class WindEnergyTypeView extends RenewableEnergyType {

  private final SceneControls sceneControls;

  public WindEnergyTypeView(ResourceBundle languageResources, SceneControls sceneControls) {
    super(sceneControls, languageResources, "wind");
    this.sceneControls = sceneControls;
    super.setEnergyAnimation(new WindEnergyAnimation(languageResources, sceneControls));
  }

  @Override
  public void handleKeyInput(KeyCode code) {

  }

  @Override
  public Node getGamePicture() {
    return null;
  }

  @Override
  public void startGame() {

  }

  @Override
  public void stepGame(double elapsedTime) {

  }

}
