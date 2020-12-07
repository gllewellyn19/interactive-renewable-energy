package ire.view.energyTypes;

import ire.view.ErrorPrintable;
import ire.view.SceneControls;
import ire.view.animations.HydroEnergyAnimation;
import ire.view.games.HydroGame;
import java.util.ResourceBundle;


/**
 * This class presents hydropower as a general energy option
 *
 * @author Grace Llewellyn
 */
public class HydroEnergyTypeView extends RenewableEnergyType{

  public HydroEnergyTypeView(ResourceBundle languageResources, SceneControls sceneControls,
      ErrorPrintable errorPrintable) {
    super(sceneControls, languageResources, "hydro");
    super.setEnergyAnimation(new HydroEnergyAnimation(languageResources, sceneControls, errorPrintable));
    super.setEnergyGame(new HydroGame(languageResources, sceneControls));
  }

}
