package ire.view.energyTypes;

import ire.view.ErrorPrintable;
import ire.view.ErrorPrinting;
import ire.view.SceneControls;
import ire.view.animations.HydroEnergyAnimation;
import ire.view.games.HydroGame;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class HydroEnergyTypeView extends RenewableEnergyType{

  public HydroEnergyTypeView(ResourceBundle languageResources, SceneControls sceneControls,
      ErrorPrintable errorPrintable) {
    super(sceneControls, languageResources, "hydro");
    super.setEnergyAnimation(new HydroEnergyAnimation(languageResources, sceneControls, errorPrintable));
    super.setEnergyGame(new HydroGame(languageResources, sceneControls));
  }

}
