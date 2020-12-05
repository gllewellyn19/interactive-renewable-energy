package ire.view.energyTypes;

import ire.view.SceneControls;
import ire.view.animations.HydroEnergyAnimation;
import ire.view.games.HydroGame;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class HydroEnergyTypeView extends RenewableEnergyType{

  public HydroEnergyTypeView(ResourceBundle languageResources, SceneControls sceneControls) {
    super(sceneControls, languageResources, "hydro");
    super.setEnergyAnimation(new HydroEnergyAnimation(languageResources, sceneControls));
    super.setEnergyGame(new HydroGame(languageResources, sceneControls));
  }

}
