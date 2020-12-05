package ire.view.energyTypes;

import ire.view.SceneControls;
import javafx.scene.Node;

public class WindEnergyTypeView extends RenewableEnergyType {

  private final SceneControls sceneControls;

  public WindEnergyTypeView(SceneControls sceneControls) {
    super(sceneControls, "wind");
    this.sceneControls = sceneControls;
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
