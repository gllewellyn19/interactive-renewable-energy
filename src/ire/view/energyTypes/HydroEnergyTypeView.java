package ire.view.energyTypes;

import ire.view.SceneControls;
import javafx.scene.Node;

public class HydroEnergyTypeView extends RenewableEnergyType{

  private SceneControls sceneControls;

  public HydroEnergyTypeView(SceneControls sceneControls) {
    super(sceneControls, "hydro");
    this.sceneControls = sceneControls;
  }

  @Override
  public Node getEnergyTypePicture() {
    return null;
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
