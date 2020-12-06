package ire.view.games;

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

public class SolarGame extends Game {

  private static final int NEW_SUN_COUNT = 50;
  private static final String FILE_PATH = "solarGame/";
  private static final int[] SCORES_TO_LEVEL_UP = {50, 90, 150};
  private static final int[] SUN_SPEEDS = {60, 100, 150};
  private static final int MAX_LEVEL = 3;
  private static final int DEFAULT_PANEL_STEP = 20;
  private static final int DEFAULT_STARTING_LIVES = 3;

  private Rectangle panel;
  private List<Circle> suns = new ArrayList<>();
  private List<Double> xDirection;
  private List<Double> yDirection;
  private int sunCount;
  private final Random rand;
  private int lives;

  public SolarGame(ResourceBundle languageResources, SceneControls sceneControls) {
    super(sceneControls, languageResources);
    rand = new Random();
  }

  /*
   * Allows the user to move the panel
   */
  @Override
  public void handleKeyInput(KeyCode code) {
    super.handleKeyInput(code);
    if (code == KeyCode.A) {
      unPauseIfPaused();
      if (panel.getX() - DEFAULT_PANEL_STEP >= 0) {
        panel.setX(panel.getX() - DEFAULT_PANEL_STEP);
      }
    }
    if (code == KeyCode.S) {
      unPauseIfPaused();
      if (panel.getX() + panel.getWidth() + DEFAULT_PANEL_STEP <= super.getSceneControls()
          .getSceneWidth()) {
        panel.setX(panel.getX() + DEFAULT_PANEL_STEP);
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
    sunCount = 0;
    xDirection = new ArrayList<>();
    xDirection.add(rand.nextDouble());
    yDirection = new ArrayList<>();
    suns = new ArrayList<>();
    yDirection.add(.80);

    Circle sun = new Circle(100, 10, 20);
    Image img1 = new Image(FILE_PATH+"sun.png");
    sun.setFill(new ImagePattern(img1));
    suns.add(sun);
    panel = new Rectangle(super.getSceneControls().getSceneWidth()/2.0 - 75,
        super.getSceneControls().getSceneHeight() - 100, 150, 30);
    Image img2 = new Image(FILE_PATH+"solar_panel.png");
    panel.setFill(new ImagePattern(img2));
    if (super.getSceneControls().getRoot().isPresent()) {
      super.getSceneControls().getRoot().get().getChildren().add(sun);
      super.getSceneControls().getRoot().get().getChildren().add(panel);
      super.unPause();
    }
  }

  /*
   * Steps through the game- called every second or so by main
   */
  @Override
  public void stepGame(double elapsedTime) {
    if (!super.isPaused()) {
      updateSuns(elapsedTime);
      sunCount += 1;
      checkForNewSun();
      updateGameDisplay(SCORES_TO_LEVEL_UP);
    }
  }

  @Override
  protected int getLives() {
    return lives;
  }

  /*
   * Adds a new sun if it's time
   */
  private void checkForNewSun() {
    if(sunCount == NEW_SUN_COUNT){
      Circle sun = new Circle(rand.nextInt((int)super.getSceneControls().getSceneWidth()), 10, 20);
      Image img1 = new Image(FILE_PATH+"sun.png");
      sun.setFill(new ImagePattern(img1));
      suns.add(sun);
      xDirection.add(rand.nextDouble());
      yDirection.add(1.0);
      if (super.getSceneControls().getRoot().isPresent()) {
        super.getSceneControls().getRoot().get().getChildren().add(sun);
      }
      sunCount = 0;
    }
  }

  /*
   * Changes the locations of the suns if necessary
   */
  private void updateSuns(double elapsedTime){
    if (!super.isPaused()) {
      for (int i = 0; i < suns.size(); i++) {
        Circle sun = suns.get(i);
        double newBallX = sun.getCenterX() + xDirection.get(i) * SUN_SPEEDS[super.getLevel()-1] * elapsedTime;
        double newBallY = sun.getCenterY() + yDirection.get(i) * SUN_SPEEDS[super.getLevel()-1] * elapsedTime;
        sun.setCenterX(newBallX);
        sun.setCenterY(newBallY);
        if (sun.intersects(panel.getLayoutBounds())) {
          super.increaseScore(10);
          removeSunFromRoot(sun);
          xDirection.remove(i);
          suns.remove(sun);
        } else if (sun.getCenterX() > super.getSceneControls().getSceneWidth()
            || sun.getCenterX() <= 0) {
          double speed = xDirection.get(i);
          xDirection.remove(i);
          xDirection.add(i, speed * -1);
        } else if (sun.getCenterY() > panel.getY() + panel.getHeight()) {
          lives -= 1;
          removeSunFromRoot(sun);
          xDirection.remove(i);
          suns.remove(sun);
        }
      }
    }
  }

  /*
   * Removes sun from the root when they have passed the end which means they are no longer
   * visible
   */
  private void removeSunFromRoot(Circle sun) {
    if (super.getSceneControls().getRoot().isPresent()) {
      try {
        super.getSceneControls().getRoot().get().getChildren().remove(sun);
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
    for (Circle sun: suns) {
      removeSunFromRoot(sun);
    }
    suns.clear();
    xDirection.clear();
    yDirection.clear();
  }

  protected int getMaxLevel() {
    return MAX_LEVEL;
  }

  protected String getEnergyType() {
    return "Solar";
  }

}
