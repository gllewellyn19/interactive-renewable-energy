package ire.view;

import ire.view.buttons.BoardInteractiveFeature;
import ire.view.buttons.HydroButton;
import ire.view.buttons.SolarButton;
import ire.view.buttons.WindButton;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class ButtonsMaintainer {

  private Map<String, BoardInteractiveFeature> buttons;

  public ButtonsMaintainer(ResourceBundle languageResources, StartEnergyTypeable startEnergyTypeable) {
    buttons = new HashMap<>();
    buttons.put("solar", new SolarButton(languageResources, startEnergyTypeable));
    buttons.put("hydro", new WindButton(languageResources, startEnergyTypeable));
    buttons.put("wind", new HydroButton(languageResources, startEnergyTypeable));
  }

  /*
   * Creates a vertical box of all the different types of renewable energy
   */
  protected Node createEnergyOptions() {
    VBox startingButtons = new VBox();
    startingButtons.getChildren().add(buttons.get("solar").getCurrInteractiveFeature());
    startingButtons.getChildren().add(buttons.get("hydro").getCurrInteractiveFeature());
    startingButtons.getChildren().add(buttons.get("wind").getCurrInteractiveFeature());
    return startingButtons;
  }

  protected Node createOptionsGeneralEnergyType(){
    return null;
  }

}
