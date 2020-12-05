package ire.view.energyTypes;

import ire.view.SceneControls;
import javafx.scene.Node;

public class ExampleGameView extends RenewableEnergyType{

  public ExampleGameView(SceneControls sceneControls) {
    super(sceneControls, "example");
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

  @Override
  public void stepGame(double elapsedTime) {

  }
}
