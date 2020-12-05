package ire.view.energyTypes;

import ire.view.SceneControls;
import javafx.scene.Node;

public abstract class RenewableEnergyType {

  private String energyType;
  private SceneControls sceneControls;

  public RenewableEnergyType(SceneControls sceneControls, String energyType) {
    this.energyType = energyType;
    this.sceneControls = sceneControls;
  }

  public void initializeEnergyType() {
    sceneControls.createGameScene();
  }

  public String getEnergyType() {
    return energyType;
  }

  public abstract Node createEnergyTypeDisplay();
}
