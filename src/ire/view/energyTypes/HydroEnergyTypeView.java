package ire.view.energyTypes;

import ire.view.SceneControls;
import ire.view.animations.HydroEnergyAnimation;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class HydroEnergyTypeView extends RenewableEnergyType{

  private SceneControls sceneControls;

  public HydroEnergyTypeView(ResourceBundle languageResources, SceneControls sceneControls) {
    super(sceneControls, languageResources, "hydro");
    this.sceneControls = sceneControls;
    super.setEnergyAnimation(new HydroEnergyAnimation(languageResources, sceneControls));
  }

  @Override
  public void handleKeyInput(KeyCode code) {

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
