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
  private static final String SCORE_INDICATOR = "Score: ";
  private static final String LIVES_INDICATOR = "Lives: ";
  private static final String LEVEL_INDICATOR = "Level: ";
  private static final String FILE_PATH = "hydroGame/";
  private static final int DEFAULT_STARTING_LIVES = 3;
  private static final int MAX_LEVEL = 3;
  private static final int[] SCORES_TO_LEVEL_UP = {50, 75, 100};
  private static final int[] FISH_SPEED = {100, 150, 200};


  private Rectangle turbine;
  private final ResourceBundle languageResources;
  private boolean paused = true;
  private Rectangle fish;
  private int score;
  private Text gameInfoDisplay;
  private int lives;
  private int level;

  public HydroGame(ResourceBundle languageResources, SceneControls sceneControls) {
    super(sceneControls);
    this.languageResources = languageResources;
  }

  /*
   * Allows the user to move the turbine with the keys and prevents the user from taking the turbine
   * off the screen
   */
  @Override
  public void handleKeyInput(KeyCode code) {
      super.handleKeyInput(code);
      if (code == KeyCode.A) {
          if (turbine.getX() - DEFAULT_TURBINE_STEP >= 0) {
              turbine.setX(turbine.getX() - DEFAULT_TURBINE_STEP);
          }
      }
      if (code == KeyCode.S) {
          if (turbine.getX() + turbine.getWidth() + DEFAULT_TURBINE_STEP <= super.getSceneControls()
              .getSceneWidth()) {
              turbine.setX(turbine.getX() + DEFAULT_TURBINE_STEP);
          }
      }
  }

  @Override
  public Node getGamePicture() {
    return null;
  }

  @Override
  public void startGame() {
      score = 0;
      lives = DEFAULT_STARTING_LIVES;
      level = 1;
      super.getSceneControls().getRoot().get().getChildren().clear();
    turbine = new Rectangle(Main.DEFAULT_SIZE.width / 2.0, Main.DEFAULT_SIZE.width - 200, 200, 200);
    turbine.setFill(new ImagePattern(new Image(FILE_PATH + "propeller.png")));
    fish = new Rectangle(Math.random() * ((Main.DEFAULT_SIZE.width - fish.getWidth()) + 1),
        10, 75, 75);
    fish.setFill(new ImagePattern(new Image(FILE_PATH + "fish.jpg")));
    gameInfoDisplay = new Text(createTextDisplay());
    gameInfoDisplay.setX(Main.DEFAULT_SIZE.width - 100);
    gameInfoDisplay.setY(50);
    gameInfoDisplay.setFont(new Font(24));
    if (super.getSceneControls().getRoot().isPresent()) {
      super.getSceneControls().getRoot().get().getChildren().add(fish);
      super.getSceneControls().getRoot().get().getChildren().add(new BackButton(languageResources,
          super.getSceneControls()).getCurrInteractiveFeature());
      super.getSceneControls().getRoot().get().getChildren().add(gameInfoDisplay);
      ImageView damImageView = new ImageView();
      Image damImage = new Image(FILE_PATH + "dam.jpg");
      damImageView.setImage(damImage);
      damImageView.setFitHeight(200);
      damImageView.setFitWidth(Main.DEFAULT_SIZE.width);
      damImageView.setX(0);
      damImageView.setY(DAM_START_Y);
      super.getSceneControls().getRoot().get().getChildren().add(damImageView);
      super.getSceneControls().getRoot().get().getChildren().add(turbine);
    }
    paused = false;
  }

  @Override
  public void stepGame(double elapsedTime) {
    if (!paused) {
      double newFishY = fish.getY() + FISH_SPEED[level-1] * elapsedTime;
      fish.setY(newFishY);
      checkFishHitDam();
    }
  }

  /*
   * Checks to see if the fish hits the dam- if it did resets its position
   * If the fish did not hit the turbine then increases the score
   */
  private void checkFishHitDam() {
    if (fish.getY() + fish.getHeight() >= DAM_START_Y) {
      if (!fishInBoundsTurbine()) {
        score += INC_SCORE_BY;
        updateGameDisplay();
        if (score > SCORES_TO_LEVEL_UP[level-1]) {
            level++;
            updateGameDisplay();
            if (level > MAX_LEVEL) {
                showGameWinningOrLosingMessage("gameWinningMessageHydro");
            }
        }
      }
      else {
          lives--;
          updateGameDisplay();
          if (lives <=0 ){
              showGameWinningOrLosingMessage("gameLosingMessageHydro");
          }
      }
      //reset the fish
      fish.setX(Math.random() * ((Main.DEFAULT_SIZE.width - fish.getWidth()) + 1));
      fish.setY(10);
    }
  }

  /*
   * Pauses the game and tells the user that they won or lost
   */
  private void showGameWinningOrLosingMessage(String gameMessageKey) {
      paused = true;
      Text gameMessage = new Text(languageResources.getString(gameMessageKey));
      gameMessage.setX(Main.DEFAULT_SIZE.width/2.0-200);
      gameMessage.setY(Main.DEFAULT_SIZE.height/2.0);
      super.getSceneControls().getRoot().get().getChildren().add(gameMessage);
  }

  /*
   * Updates the display of the game information to reflect a change in the lives, score, and level.
   */
  private void updateGameDisplay() {
    gameInfoDisplay.setText(createTextDisplay());
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

  /*
   * Creates the text for the display board to show the score, lives and level.
   */
  private String createTextDisplay() {
    return SCORE_INDICATOR + score + "\n" + LIVES_INDICATOR + lives + "\n" + LEVEL_INDICATOR
        + level;
  }
}