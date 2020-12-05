package ire.view.energyTypes;

import ire.view.SceneControls;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class HydroEnergyTypeView extends RenewableEnergyType{

  private SceneControls sceneControls;

  public HydroEnergyTypeView(SceneControls sceneControls) {
    super(sceneControls, "hydro");
    this.sceneControls = sceneControls;
  }

  @Override
  public void handleKeyInput(KeyCode code) {

  }

  //for the animation
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
