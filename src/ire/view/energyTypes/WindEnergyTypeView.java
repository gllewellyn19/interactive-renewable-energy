package ire.view.energyTypes;

import ire.view.ErrorPrintable;
import ire.view.SceneControls;
import ire.view.animations.WindEnergyAnimation;
import ire.view.games.WindGame;
import java.util.ResourceBundle;

/**
 * This class presents wind energy as a general energy option
 *
 * @author Grace Llewellyn
 */
public class WindEnergyTypeView extends RenewableEnergyType {

  public WindEnergyTypeView(ResourceBundle languageResources, SceneControls sceneControls,
      ErrorPrintable errorPrintable) {
    super(sceneControls, languageResources, "wind");
    super.setEnergyAnimation(new WindEnergyAnimation(languageResources, sceneControls, errorPrintable));
    super.setEnergyGame(new WindGame(languageResources, sceneControls));
  }

}
