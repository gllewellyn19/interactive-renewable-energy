package ire.view.energyTypes;

import ire.view.SceneControls;
import javafx.scene.Node;

public class HydroGameTypeView extends RenewableEnergyType{

  public HydroGameTypeView(SceneControls sceneControls) {
    super(sceneControls, "hydro");
  }

  @Override
  public Node createEnergyTypeDisplay() {
    return null;
  }

}
