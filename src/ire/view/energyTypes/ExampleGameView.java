package ire.view.energyTypes;

import ire.view.GameStatus;
import ire.view.SceneControls;
import ire.view.buttons.BackButton;
import ire.view.games.ExampleGame;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ExampleGameView extends RenewableEnergyType{

  public ExampleGameView(ResourceBundle languageResources, SceneControls sceneControls) {
    super(sceneControls, languageResources, "example");
    super.setEnergyGame(new ExampleGame(languageResources, sceneControls));
  }


}
