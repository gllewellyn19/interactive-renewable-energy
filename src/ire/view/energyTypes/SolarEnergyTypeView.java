package ire.view.energyTypes;

import ire.view.SceneControls;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class SolarEnergyTypeView extends RenewableEnergyType {

  private final SceneControls sceneControls;

  public SolarEnergyTypeView(SceneControls sceneControls) {
    super(sceneControls, "solar");
    this.sceneControls = sceneControls;
  }

  @Override
  public void handleKeyInput(KeyCode code) {

  }

  @Override
  public Node getEnergyTypePicture() {
    return null;
  }

  @Override
  public Node createEnergyTypeDisplay() {
    return null;
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
