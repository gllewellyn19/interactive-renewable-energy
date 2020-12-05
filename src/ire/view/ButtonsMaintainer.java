package ire.view;

import ire.view.buttons.BackButton;
import ire.view.buttons.BoardInteractiveFeature;
import ire.view.buttons.GoToAnimationButton;
import ire.view.buttons.GoToGameButton;
import ire.view.buttons.HydroButton;
import ire.view.buttons.NextButton;
import ire.view.buttons.SolarButton;
import ire.view.buttons.WindButton;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
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
      buttons.put("next", new NextButton(languageResources, sceneControls));
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
  //FIXME: Cams implement this for all the buttons you want to show up for your game.
  // with how this looks in GameView.createGameScreen(width, height), all the buttons will go in the
  // left but you can modify the line of that code that creates the border pane to add buttons in
  // different places
  // The line I'm referring to:
  // BorderPane displayLayout = new BorderPane(currentRenewableEnergyType.createEnergyTypeGame(),
  //     topText, null, null, buttonsMaintainer.createOptionsEnergyTypeGame());
  protected Node createOptionsEnergyTypeGame(){
    return null;
  }
  /*
   * Creates the options for when entering the animation of a type of renewable energy
   */
  protected Node createOptionsEnergyTypeAnimation() {
    VBox animationButtons = new VBox();
    animationButtons.getChildren().add(buttons.get("back").getCurrInteractiveFeature());
    animationButtons.getChildren().add(buttons.get("next").getCurrInteractiveFeature());
    return animationButtons;
  }

}
