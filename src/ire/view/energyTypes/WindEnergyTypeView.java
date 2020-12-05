package ire.view.energyTypes;

import ire.view.SceneControls;
import ire.view.animations.WindEnergyAnimation;
import ire.view.games.WindGame;
import java.util.ResourceBundle;

public class WindEnergyTypeView extends RenewableEnergyType {

  public WindEnergyTypeView(ResourceBundle languageResources, SceneControls sceneControls) {
    super(sceneControls, languageResources, "wind");
    super.setEnergyAnimation(new WindEnergyAnimation(languageResources, sceneControls));
    super.setEnergyGame(new WindGame(languageResources, sceneControls));
  }

}
