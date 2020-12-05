package ire.view.energyTypes;

import ire.view.SceneControls;
import javafx.scene.Node;

public class HydroEnergyTypeView extends RenewableEnergyType{

  public HydroEnergyTypeView(SceneControls sceneControls) {
    super(sceneControls, "hydro");
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
  public Node createEnergyTypeGame() {
    return null;
  }

}
