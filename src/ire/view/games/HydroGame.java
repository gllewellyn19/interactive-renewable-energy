package ire.view.games;

import ire.Main;
import ire.view.GameStatus;
import ire.view.SceneControls;
import ire.view.buttons.BackButton;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HydroGame extends Game {

  public static final int DEFAULT_TURBINE_STEP = 10;
  public static final int DAM_START_Y = Main.DEFAULT_SIZE.width - 200;
  public static final int INC_SCORE_BY = 10;
  private static final String FILE_PATH = "hydroGame/";
  private static final int DEFAULT_STARTING_LIVES = 3;
  private static final int MAX_NUM_LEVEL = 3;
  private static final int[] SCORES_TO_LEVEL_UP = {50, 80, 120};
  private static final int[] FISH_SPEED = {70, 100, 150};

  private Rectangle turbine;
  private Rectangle fish;
  private int lives;

  public HydroGame(ResourceBundle languageResources, SceneControls sceneControls) {
    super(sceneControls, languageResources);
  }

  /*
   * Allows the user to move the turbine with the keys and prevents the user from taking the turbine
   * off the screen
   */
  @Override
  public void handleKeyInput(KeyCode code) {
    super.handleKeyInput(code);
    if (code == KeyCode.A) {
      unPauseIfPaused();
      if (turbine.getX() - DEFAULT_TURBINE_STEP >= 0) {
        turbine.setX(turbine.getX() - DEFAULT_TURBINE_STEP);
      }
    }
    if (code == KeyCode.S) {
      unPauseIfPaused();
      if (turbine.getX() + turbine.getWidth() + DEFAULT_TURBINE_STEP <= super
          .getSceneControls()
          .getSceneWidth()) {
        turbine.setX(turbine.getX() + DEFAULT_TURBINE_STEP);
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

    turbine = new Rectangle(Main.DEFAULT_SIZE.width / 2.0, Main.DEFAULT_SIZE.width - 200, 200, 200);
    turbine.setFill(new ImagePattern(new Image(FILE_PATH + "propeller.png")));
    fish = new Rectangle(Math.random() * ((Main.DEFAULT_SIZE.width - 75) + 1),
        10, 75, 75);
    fish.setFill(new ImagePattern(new Image(FILE_PATH + "fish.png")));

    if (super.getSceneControls().getRoot().isPresent()) {
      super.getSceneControls().getRoot().get().getChildren().add(fish);
      ImageView damImageView = new ImageView();
      Image damImage = new Image(FILE_PATH + "dam.jpg");
      damImageView.setImage(damImage);
      damImageView.setFitHeight(200);
      damImageView.setFitWidth(Main.DEFAULT_SIZE.width);
      damImageView.setX(0);
      damImageView.setY(DAM_START_Y);
      super.getSceneControls().getRoot().get().getChildren().add(damImageView);
      super.getSceneControls().getRoot().get().getChildren().add(turbine);
      super.unPause();
    }
  }

  /*
   * Steps through the game- called every second or so by main
   */
  @Override
  public void stepGame(double elapsedTime) {
    if (!super.isPaused()) {
      double newFishY = fish.getY() + FISH_SPEED[super.getLevel() - 1] * elapsedTime;
      fish.setY(newFishY);
      checkFishHitDam();
      updateGameDisplay(SCORES_TO_LEVEL_UP);
    }
  }

  /*
   * Checks to see if the fish hits the dam- if it did resets its position
   * If the fish did not hit the turbine then increases the score
   */
  private void checkFishHitDam() {
    if (fish.getY() + fish.getHeight() >= DAM_START_Y) {
      if (!fishInBoundsTurbine()) {
        super.increaseScore(INC_SCORE_BY);
      } else {
        lives--;
      }
      resetFish();
    }
  }

  private void resetFish() {
    fish.setX(Math.random() * ((Main.DEFAULT_SIZE.width - fish.getWidth()) + 1));
    fish.setY(10);
  }

  @Override
  protected int getLives() {
    return lives;
  }

  @Override
  protected int getPointsToNextLevel() {
    if (super.getLevel() - 1 < SCORES_TO_LEVEL_UP.length) {
      return SCORES_TO_LEVEL_UP[super.getLevel() - 1] - super.getScore();
    } else {
      return 0;
    }
  }

  @Override
  protected void restartObstacles() {
    resetFish();
  }

  /*
   * Returns true if the fish was hit by the turbine
   */
  private boolean fishInBoundsTurbine() {
    return (fish.getX() >= turbine.getX() && fish.getX() <= (turbine.getX() + turbine.getWidth()))
        ||
        ((fish.getX() + fish.getWidth()) >= turbine.getX() && (fish.getX() + fish.getWidth()) <=
            (turbine.getX() + turbine.getWidth()));
  }

  protected int getMaxLevel() {
    return MAX_NUM_LEVEL;
  }

  protected String getEnergyType() {
    return "Hydro";
  }


}