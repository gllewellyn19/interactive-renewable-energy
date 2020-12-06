package ire.view.games;

import ire.Main;
import ire.view.GameStatus;
import ire.view.SceneControls;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;

public class WindGame extends Game {

  private static final int NEW_TURBINE = 40;
  private static final String FILE_PATH = "windGame/";
  private static final int[] SCORES_TO_LEVEL_UP = {30, 60, 100};
  private static final int[] WIND_MILL_SPEEDS = {-100, -150, -200};
  private static final int MAX_LEVEL = 3;
  private static final int DEFAULT_BIRD_STEP = 20;
  private static final int DEFAULT_STARTING_LIVES = 3;

  private Circle bird;
  private List<Rectangle> turbines = new ArrayList<>();
  private double xDirection;
  private int winMillCount;
  private Random rand;
  private int lives;

  public WindGame(ResourceBundle languageResources, SceneControls sceneControls) {
    super(sceneControls, languageResources);
    rand = new Random();
  }

  @Override
  public void handleKeyInput(KeyCode code) {
    super.handleKeyInput(code);
    if (code == KeyCode.W) {
      if (bird.getCenterY() - bird.getRadius() - DEFAULT_BIRD_STEP >= 0) {
        bird.setCenterY(bird.getCenterY() - DEFAULT_BIRD_STEP);
      }
    }
    if (code == KeyCode.Z) {
      if (bird.getCenterY() + bird.getRadius() + DEFAULT_BIRD_STEP <= super.getSceneControls()
          .getSceneHeight()) {
        bird.setCenterY(bird.getCenterY() + DEFAULT_BIRD_STEP);
      }
    }
  }

  @Override
  public String getFilePath() {
    return FILE_PATH;
  }

  /*
   * Starts the game by setting up all the shapes and pictures and unpauses the game
   */
  @Override
  public void startGame() {
    super.getSceneControls().getRoot().get().getChildren().clear();
    lives = DEFAULT_STARTING_LIVES;
    super.startGame();
    winMillCount = 0;
    xDirection = .80;

    double height = rand.nextDouble()*.7*super.getSceneControls().getSceneHeight();
    Rectangle turbine = new Rectangle(super.getSceneControls().getSceneWidth(),0,100, height);
    Image img1 = new Image(FILE_PATH+"turbine.png");
    turbine.setFill(new ImagePattern(img1));
    turbines.add(turbine);
    bird = new Circle(50, super.getSceneControls().getSceneHeight()/2, 50);
    Image img2 = new Image(FILE_PATH+"bird.png");
    bird.setFill(new ImagePattern(img2));
    if (super.getSceneControls().getRoot().isPresent()) {
      super.getSceneControls().getRoot().get().getChildren().add(turbine);
      super.getSceneControls().getRoot().get().getChildren().add(bird);
      super.unPause();
    }
  }

  /*
   * Steps through the game- called every second or so by main
   */
  @Override
  public void stepGame(double elapsedTime) {
    if (!super.isPaused()) {
      updateTurbines(elapsedTime);
      winMillCount += 1;
    }
    checkForNewTurbine();
    super.updateGameDisplay(SCORES_TO_LEVEL_UP);
  }

  @Override
  protected int getLives() {
    return lives;
  }

  /*
   * Adds a new turbine if it's time
   */
  private void checkForNewTurbine() {
    if(winMillCount == NEW_TURBINE){
      double spot = rand.nextDouble();
      double height = rand.nextDouble()*.7*super.getSceneControls().getSceneHeight();
      double start = 0;
      if(spot < 0.5){
        start = super.getSceneControls().getSceneHeight()-height;
      }
      Rectangle turbine = new Rectangle(super.getSceneControls().getSceneWidth(),start, 100, height);
      Image img1 = new Image(FILE_PATH+"turbine.png");
      turbine.setFill(new ImagePattern(img1));
      turbines.add(turbine);
      if (super.getSceneControls().getRoot().isPresent()) {
        super.getSceneControls().getRoot().get().getChildren().add(turbine);
      }
      winMillCount = 0;
    }
  }

  /*
   * Creates the graphic of the turbines moving across the screen at the correct speed
   */
  private void updateTurbines(double elapsedTime){
    for(int i = 0; i< turbines.size(); i++){
      Rectangle turbine = turbines.get(i);
      double newTurbineX = turbine.getX() + xDirection * WIND_MILL_SPEEDS[super.getLevel()-1] * elapsedTime;
      turbine.setX(newTurbineX);
      if(turbine.intersects(bird.getLayoutBounds())){
        lives -= 1;
        removeTurbineFromRoot(turbine);
        turbines.remove(turbine);
      } else if (turbine.getX() < bird.getCenterX() - bird.getRadius()) {
        super.increaseScore(10);
        removeTurbineFromRoot(turbine);
        turbines.remove(turbine);
      }
    }
  }

  /*
   * Removes turbines from the root when they have passed the end which means they are no longer
   * visible
   */
  private void removeTurbineFromRoot(Rectangle turbine) {
    if (super.getSceneControls().getRoot().isPresent()) {
      try {
        super.getSceneControls().getRoot().get().getChildren().remove(turbine);
      } catch (IllegalArgumentException ignored) {
      }
    }
  }

  @Override
  protected int getPointsToNextLevel() {
    if (super.getLevel()-1 <SCORES_TO_LEVEL_UP.length) {
      return SCORES_TO_LEVEL_UP[super.getLevel() - 1] - super.getScore();
    } else {
      return 0;
    }
  }

  @Override
  protected void restartObstacles() {
    for (Rectangle turbine: turbines) {
      removeTurbineFromRoot(turbine);
    }
  }

  protected int getMaxLevel() {
    return MAX_LEVEL;
  }

  protected String getEnergyType() {
    return "Wind";
  }

}