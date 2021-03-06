package ire;

import ire.view.GameStatus;
import ire.view.GameView;
import ire.view.SceneControls;
import java.awt.Dimension;
import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class creates all parts of the project and handles the part of the project that is concerned
 * with being an application such as setting the stage/ scene/ root. 
 *
 * @author Grace Llewellyn
 */
public class Main extends Application implements SceneControls {

  public static final String RESOURCES = "resources/";
  public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES;
  public static final Dimension DEFAULT_SIZE = new Dimension(800, 800);
  public static final String DEFAULT_TITLE = "Interactive Renewable Energy";
  public static final String DEFAULT_RESOURCES_PACKAGE = RESOURCES.replace("/", ".");
  public static final double SECOND_DELAY = .1;

  private Stage stage;
  private Scene scene;
  private GameView gameView;
  private GameStatus gameStatus = GameStatus.NEITHER;
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
      Timeline animation = new Timeline();
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
    if (gameStatus == GameStatus.GAME) {
      //System.out.println("in game and calling the step function");
      gameView.getCurrentRenewableEnergyType().stepGame(elapsedTime);
    } else if (gameStatus == GameStatus.ANIMATION) {
      //System.out.println("in animation and calling the step function");
      gameView.getCurrentRenewableEnergyType().stepAnimation(elapsedTime);
    }
  }

  /**
   * Called whenever the back button is called and restarts the game with the current stage
   */
  @Override
  public void restart() {
    gameStatus = GameStatus.NEITHER;
    start(stage);
  }

  /**
   * Creates a default game when the user presses the a cheat key
   *
   * @param code cheat key passed in that can have different meanings
   */
  private void handleKeyInput(KeyCode code) {
    if (gameView.getCurrentRenewableEnergyType()!=null) {
      gameView.getCurrentRenewableEnergyType().handleKeyInput(code);
    }
    if (code == KeyCode.B) {
      restart();
    }
  }

  @Override
  public void createGeneralEnergyTypeScene() {
    Optional<Scene> newScene = gameView.createGeneralEnergyTypeScene(scene.getWidth(), scene.getHeight());
    if (newScene.isPresent()) {
      scene = newScene.get();
      scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
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
      scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
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
      scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
      stage.setScene(scene);
    }
  }

  /**
   * Starts the game and instead of using a BorderPane for the parent of the scene, uses a group
   * so that objects can be added
   * @param gameOrAnimation true if a game should start and false if animation should start
   */
  @Override
  public void startGame(GameStatus gameOrAnimation) {
    gameStatus = gameOrAnimation;
    root = Optional.of(new Group());
    double prevWidth = scene.getWidth();
    double prevHeight = scene.getHeight();
    scene = new Scene(root.get(), prevWidth, prevHeight, GameView.DEFAULT_BACKGROUND);
    scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    stage.setScene(scene);
    if (gameStatus == GameStatus.GAME) {
      gameView.getCurrentRenewableEnergyType().startGame();
    } else if (gameStatus == GameStatus.ANIMATION){
      gameView.getCurrentRenewableEnergyType().startAnimation();
    }
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

  public GameStatus getGameStatus() {
    return gameStatus;
  }
}
