package ire;

import ire.model.GamePlay;
import ire.view.GameView;
import ire.view.SceneControls;
import java.awt.Dimension;
import java.util.Optional;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Main extends Application implements SceneControls {

  public static final String RESOURCES = "resources/";
  public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES;
  public static final Dimension DEFAULT_SIZE = new Dimension(800, 800);
  public static final String DEFAULT_TITLE = "Interactive Renewable Energy";
  public static final String DEFAULT_RESOURCES_PACKAGE = RESOURCES.replace("/", ".");

  private Stage stage;
  private Scene scene;
  private GameView gameView;
  private GamePlay gamePlay;

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Starts the game- needs this because overrides Application
   * @param stage Stage to set the scene of
   */
  @Override
  public void start(Stage stage) {
    gameView = new GameView(this);
    gamePlay = new GamePlay();
    Optional<Scene> startingScene = gameView.makeAnInitialScene(DEFAULT_SIZE.width,
        DEFAULT_SIZE.height);
    if (startingScene.isPresent()) {
      scene = startingScene.get();
      scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
      stage.setScene(scene);
      stage.setTitle(DEFAULT_TITLE);
      stage.show();
      this.stage = stage;
    }
  }

  /**
   * Called whenever the back button is called and restarts the game with the current stage
   */
  @Override
  public void restart() {
    start(stage);
  }

  /**
   * Creates a default game when the user presses the a cheat key
   *
   * @param code cheat key passed in that can have different meanings
   */
  private void handleKeyInput(KeyCode code) {

  }

  /**
   * Sets the current scene and only classes with interfaces can do this since no classes gets
   * the entire main class
   *

  public void setScene(Scene s) {
    this.scene = s;
    stage.setScene(scene);
  } */

  @Override
  public void createGeneralEnergyTypeScene() {
    Optional<Scene> newScene = gameView.createGeneralEnergyTypeScene(scene.getWidth(), scene.getHeight());
    if (newScene.isPresent()) {
      scene = newScene.get();
      stage.setScene(scene);
    }
  }

  /**
   * Creates the screen for the animation of the current type of renewable energy that has been
   * selected
   */
  @Override
  public void createAnimationScreen() {
    Optional<Scene> newScene = gameView.createAnimationScreen(scene.getWidth(), scene.getHeight());
    if (newScene.isPresent()) {
      scene = newScene.get();
      stage.setScene(scene);
    }
  }

  /**
   * Creates the screen for the game of the current type of renewable energy that has been
   * selected
   */
  @Override
  public void createGameScreen() {
    Optional<Scene> newScene = gameView.createGameScreen(scene.getWidth(), scene.getHeight());
    if (newScene.isPresent()) {
      scene = newScene.get();
      stage.setScene(scene);
    }
  }

  public Stage getStage() {
    return stage;
  }
}
