package ire.view.energyTypes;

import ire.view.ErrorPrintable;
import ire.view.SceneControls;
import ire.view.animations.SolarEnergyAnimation;
import ire.view.games.SolarGame;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class SolarEnergyTypeView extends RenewableEnergyType {

  public SolarEnergyTypeView(ResourceBundle languageResources, SceneControls sceneControls,
      ErrorPrintable errorPrintable) {
    super(sceneControls, languageResources, "solar");
    super.setEnergyAnimation(new SolarEnergyAnimation(languageResources, sceneControls, errorPrintable));
    super.setEnergyGame(new SolarGame(languageResources, sceneControls));
  }

}
