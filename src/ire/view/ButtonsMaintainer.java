package ire.view;

import ire.view.buttons.BackButton;
import ire.view.buttons.BoardInteractiveFeature;
import ire.view.buttons.GoToAnimationButton;
import ire.view.buttons.GoToGameButton;
import ire.view.buttons.HydroButton;
import ire.view.buttons.SolarButton;
import ire.view.buttons.StartAnimationButton;
import ire.view.buttons.StartGameButton;
import ire.view.buttons.WindButton;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ButtonsMaintainer {

  private Map<String, BoardInteractiveFeature> buttons;

  public ButtonsMaintainer(ResourceBundle languageResources, StartEnergyTypeable startEnergyTypeable,
      SceneControls sceneControls, ErrorPrintable errorPrintable) {
    buttons = new HashMap<>();
    try {
      buttons.put("solar", new SolarButton(languageResources, startEnergyTypeable));
      buttons.put("hydro", new WindButton(languageResources, startEnergyTypeable));
      buttons.put("wind", new HydroButton(languageResources, startEnergyTypeable));
      buttons.put("back", new BackButton(languageResources, sceneControls));
      buttons.put("goToGame", new GoToGameButton(languageResources, sceneControls));
      buttons.put("goToAnimation", new GoToAnimationButton(languageResources, sceneControls));
      buttons.put("startGame", new StartGameButton(languageResources, sceneControls));
      buttons.put("startAnimation", new StartAnimationButton(languageResources, sceneControls));
    } catch(MissingResourceException e) {
      errorPrintable.printErrorMessageAlert("missingResourceException", e.getKey());
    }
  }

  /*
   * Creates a vertical box of all the different types of renewable energy for the splash screen
   */
  protected Node createEnergyOptions() {
    VBox startingButtons = new VBox();
    startingButtons.getChildren().add(buttons.get("solar").getCurrInteractiveFeature());
    startingButtons.getChildren().add(buttons.get("hydro").getCurrInteractiveFeature());
    startingButtons.getChildren().add(buttons.get("wind").getCurrInteractiveFeature());
    return startingButtons;
  }

  /*
   * Creates the options for when entering a new general type of energy
   */
  protected Node createOptionsGeneralEnergyType(){
    HBox generalButtonsGame = new HBox();
    generalButtonsGame.getChildren().add(buttons.get("back").getCurrInteractiveFeature());
    generalButtonsGame.getChildren().add(buttons.get("goToGame").getCurrInteractiveFeature());
    generalButtonsGame.getChildren().add(buttons.get("goToAnimation").getCurrInteractiveFeature());
    return generalButtonsGame;
  }

  /*
   * Creates the options for when entering the game of a type of renewable energy
   */
  protected Node createOptionsEnergyTypeGame(){
    VBox gameButtons = new VBox();
    gameButtons.getChildren().add(buttons.get("back").getCurrInteractiveFeature());
    gameButtons.getChildren().add(buttons.get("startGame").getCurrInteractiveFeature());
    return gameButtons;
  }
  /*
   * Creates the options for when entering the animation of a type of renewable energy
   */
  protected Node createOptionsEnergyTypeAnimation() {
    VBox animationButtons = new VBox();
    animationButtons.getChildren().add(buttons.get("back").getCurrInteractiveFeature());
    animationButtons.getChildren().add(buttons.get("startAnimation").getCurrInteractiveFeature());
    return animationButtons;
  }

}
