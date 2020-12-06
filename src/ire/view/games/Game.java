package ire.view.games;

import ire.view.SceneControls;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public abstract class Game {

  private final SceneControls sceneControls;

  public Game(SceneControls sceneControls) {
    this.sceneControls = sceneControls;
  }

  public void handleKeyInput(KeyCode code) {
    if (code == KeyCode.P) {
      startGame();
    }
  }

  public abstract Node getGamePicture();

  public abstract void startGame();

  public abstract void stepGame(double elapsedTime);

  public SceneControls getSceneControls() {
    return sceneControls;
  }
}
