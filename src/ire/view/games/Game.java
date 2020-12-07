package ire.view.games;

import ire.view.SceneControls;
import ire.view.buttons.BackButton;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * This abstract class provides a template for the games in this project. The subclasses can
 * override any of this functionality if they differ from these methods.
 *
 * @author Grace Llewellyn
 */
public abstract class Game {

  private static final String SCORE_INDICATOR = "Score: ";
  private static final String LIVES_INDICATOR = "Lives: ";
  private static final String LEVEL_INDICATOR = "Level: ";
  private static final String POINTS_TO_NEXT_LEVEL_INDICATOR = "Points to next\nlevel: ";

  private final SceneControls sceneControls;
  private final ResourceBundle languageResources;
  private boolean pause = true;
  private boolean gameOver = false;
  private int level;
  private int score;
  private Text gameInfoDisplay;
  private Text funFact;

  public Game(SceneControls sceneControls, ResourceBundle languageResources) {
    this.sceneControls = sceneControls;
    this.languageResources = languageResources;
  }

  /*
   * Manages the different cheat keys for the game
   */
  public void handleKeyInput(KeyCode code) {
    if (code == KeyCode.R) {
      startGame();
    }
    else if (code == KeyCode.H && !gameOver) {
      funFact.setText("");
      pause = !pause;
    }
    else if (code == KeyCode.L && !gameOver) {
      moveToNextLevel();
    }
  }

  /*
   * Returns a screen shot of the game to show the user
   */
  public Node getGamePicture() {
    ImageView gameScreenShot = new ImageView(new Image(getFilePath()+"screenshotGame.png"));
    gameScreenShot.setFitWidth(500);
    gameScreenShot.setFitHeight(500);
    return gameScreenShot;
  }

  public abstract String getFilePath();

  /**
   * Starts the game- either through a restart or the user presses the start game button on the
   * general energy screen
   */
  public void startGame() {
    gameOver = false;
    level = 1;
    score = 0;
    gameInfoDisplay = new Text(createTextDisplay());
    gameInfoDisplay.setX(sceneControls.getSceneWidth() - 170);
    gameInfoDisplay.setY(20);
    gameInfoDisplay.setFont(new Font(20));
    funFact = new Text();
    funFact.setX(90);
    funFact.setY(sceneControls.getSceneHeight()/2.0 - 200);
    funFact.setFont(new Font(30));

    if (sceneControls.getRoot().isPresent()) {
      sceneControls.getRoot().get().getChildren().add(new BackButton(languageResources,
          sceneControls).getCurrInteractiveFeature());
      sceneControls.getRoot().get().getChildren().add(gameInfoDisplay);
      sceneControls.getRoot().get().getChildren().add(funFact);
    }
  }

  public abstract void stepGame(double elapsedTime);

  public SceneControls getSceneControls() {
    return sceneControls;
  }


  protected void unPause() {
    pause = false;
  }

  protected boolean isPaused() {
    return pause;
  }

  /*
   * Pauses the game and tells the user that they won or lost
   */
  protected void showGameWinningOrLosingMessage(String gameMessageKey) {
    gameOver = true;
    pause = true;
    Text gameMessage = new Text(languageResources.getString(gameMessageKey));
    gameMessage.setX(90);
    gameMessage.setY(sceneControls.getSceneHeight()/2.0 - 200);
    gameMessage.setFont(new Font(30));
    sceneControls.getRoot().get().getChildren().add(gameMessage);
  }

  protected abstract int getLives();

  protected abstract int getPointsToNextLevel();

  protected int getScore() {
    return score;
  }

  /*
   * Creates the text for the display board to show the score, lives and level.
   */
  protected String createTextDisplay() {
    return SCORE_INDICATOR + score + "\n" + LIVES_INDICATOR + getLives() + "\n" + LEVEL_INDICATOR
        + level + "\n" + POINTS_TO_NEXT_LEVEL_INDICATOR + getPointsToNextLevel();
  }

  /*
   * Updates the display of the game information to reflect a change in the lives, score, and level.
   * Also tells the user if they have won or lost the game
   */
  protected void updateGameDisplay(int[] scoresToLevelUp) {
    if (getLives() == 0) {
      showGameWinningOrLosingMessage("gameLosingMessage"+getEnergyType());
    }
    if (score >= scoresToLevelUp[level-1]) {
      moveToNextLevel();
    }
    gameInfoDisplay.setText(createTextDisplay());
  }

  protected void increaseScore(int incBy) {
    score+=incBy;
  }

  protected int getLevel() {
    return level;
  }

  /*
   * Shows a fun fact after the user has leveled up. Pauses the game to show this message
   */
  private void showFunFact(String funFactKey) {
    pause = true;
    funFact.setText(languageResources.getString(funFactKey));
  }

  /*
   * Skips to the next level or the end of the game. This happens when the user presses a cheat key
   */
  private void moveToNextLevel() {
    if (level >= getMaxLevel()) {
      showGameWinningOrLosingMessage("gameWinningMessage"+getEnergyType());
    } else {
      showFunFact("funFact"+level+getEnergyType());
      level++;
      restartObstacles();
    }
    gameInfoDisplay.setText(createTextDisplay());
  }

  /*
   * Called when the user wants to change the level- removes the obstacles or places them back at
   * the start
   */
  protected abstract void restartObstacles();

  protected abstract int getMaxLevel();

  protected abstract String getEnergyType();

  protected void unPauseIfPaused() {
    if (isPaused() && funFact.getText().equals("")) {
       unPause();
    }
  }
}
