package ire.view.energyTypes;

import ire.view.SceneControls;
import ire.view.animations.HydroEnergyAnimation;
import ire.view.animations.SolarEnergyAnimation;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class SolarEnergyTypeView extends RenewableEnergyType {

  private final SceneControls sceneControls;

  public SolarEnergyTypeView(ResourceBundle languageResources, SceneControls sceneControls) {
    super(sceneControls, languageResources, "solar");
    this.sceneControls = sceneControls;
    super.setEnergyAnimation(new SolarEnergyAnimation(languageResources, sceneControls));

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
