package ire;

import ire.model.GamePlay;
import ire.view.GameView;
import ire.view.SceneControls;
import java.awt.Dimension;
import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application implements SceneControls {

  public static final String RESOURCES = "resources/";
  public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES;
  public static final Dimension DEFAULT_SIZE = new Dimension(800, 800);
  public static final String DEFAULT_TITLE = "Interactive Renewable Energy";
  public static final String DEFAULT_RESOURCES_PACKAGE = RESOURCES.replace("/", ".");
  public static final double SECOND_DELAY = 1;

  private Stage stage;
  private Scene scene;
  private GameView gameView;
  private GamePlay gamePlay;
  private Timeline animation; //FIXME: Cams if you ever want to change the animation rate use:
  // animation.setRate(Math.pow(animation.getCurrentRate(),modifyingRate));
  private boolean inGameCurrently = false;
  private Optional<Group> root = Optional.empty();

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
      KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
      animation = new Timeline();
      animation.setCycleCount(Timeline.INDEFINITE);
      animation.getKeyFrames().add(frame);
      animation.play();
    }
  }

  /**
   * Called every second or so, but only when an active game is playing
   *
   * @param elapsedTime time since last step
   */
  public void step(double elapsedTime) {
    if (inGameCurrently) {
      System.out.println("in game and calling the step function");
      gameView.stepCurrentGame(elapsedTime);
    }
  }

  /**
   * Called whenever the back button is called and restarts the game with the current stage
   */
  @Override
  public void restart() {
    inGameCurrently = false;
    start(stage);
  }

  /**
   * Creates a default game when the user presses the a cheat key
   *
   * @param code cheat key passed in that can have different meanings
   */
  //FIXME: Cams for some reason this is not catching when arrows are pressed so you can either try
  // to figure it out or just not use arrows
  private void handleKeyInput(KeyCode code) {
    if (gameView.getCurrentRenewableEnergyType()!=null) {
      gameView.getCurrentRenewableEnergyType().handleKeyInput(code);
    }
  }

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

  /**
   * Starts the game and instead of using a BorderPane for the parent of the scene, uses a group
   * so that objects can be added
   */
  @Override
  public void startGame() {
    inGameCurrently = true;
    root = Optional.of(new Group());
    double prevWidth = scene.getWidth();
    double prevHeight = scene.getHeight();
    scene = new Scene(root.get(), prevWidth, prevHeight, GameView.DEFAULT_BACKGROUND);
    scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    stage.setScene(scene);
    gameView.getCurrentRenewableEnergyType().startGame();
  }

  @Override
  public double getSceneWidth() {
    return scene.getWidth();
  }

  @Override
  public double getSceneHeight() {
    return scene.getHeight();
  }

  @Override
  public Optional<Group> getRoot() {
    return root;
  }

  public Stage getStage() {
    return stage;
  }

  public boolean getInGameCurrently() {
    return inGameCurrently;
  }
}
