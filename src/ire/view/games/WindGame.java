package ire.view.games;

import ire.view.SceneControls;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class WindGame extends Game{

  public WindGame(ResourceBundle languageResources, SceneControls sceneControls) {
    super(sceneControls);
  }

  @Override
  public void handleKeyInput(KeyCode code) {

  }

  @Override
  public Node getGamePicture() {
    return null;
  }

  @Override
  public void startGame() {

  }

  @Override
  public void stepGame(double elapsedTime) {

  }
}
