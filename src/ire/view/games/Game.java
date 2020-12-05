package ire.view.games;

import ire.view.SceneControls;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public abstract class Game {

  private final SceneControls sceneControls;

  public Game(SceneControls sceneControls) {
    this.sceneControls = sceneControls;
  }

  public abstract void handleKeyInput(KeyCode code);

  //FIXME: Cams if you want a picture to show up on the initial screen for the game then implement this
  // method
  public abstract Node getGamePicture();

  //FIXME: Cams implement this function to show how you want your game to initially look- this will
  // be displayed in the middle of the screen- can use sceneControls to get the root to add shapes to
  public abstract void startGame();

  //FIXME: Cams implement this function to step through your game- this is called every second or so
  // but only when an active game is happening
  public abstract void stepGame(double elapsedTime);

  public SceneControls getSceneControls() {
    return sceneControls;
  }
}
