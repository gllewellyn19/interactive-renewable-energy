package ire.view.energyTypes;

import ire.view.SceneControls;
import javafx.scene.Node;

public class WindEnergyTypeView extends RenewableEnergyType {

  public WindEnergyTypeView(SceneControls sceneControls) {
    super(sceneControls, "wind");
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
  public Node createEnergyTypeGame() {
    return null;
  }

}
