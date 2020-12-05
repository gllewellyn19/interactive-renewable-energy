package ire.view.energyTypes;

import ire.view.SceneControls;
import javafx.scene.Node;

public class SolarEnergyTypeView extends RenewableEnergyType {

  public SolarEnergyTypeView(SceneControls sceneControls) {
    super(sceneControls, "solar");
  }

  @Override
  public Node createEnergyTypeDisplay() {
    return null;
  }
}
