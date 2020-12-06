package ire.view.games;

import ire.Main;
import ire.view.SceneControls;
import ire.view.buttons.BackButton;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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

  public void handleKeyInput(KeyCode code) {
    if (code == KeyCode.P) {
      startGame();
    }
    if (code == KeyCode.H && !gameOver) {
      funFact.setText("");
      pause = !pause;
    }
  }

  public abstract Node getGamePicture();

  /**
   * Starts the game- either through a restart or the user presses the start game button on the
   * general energy screen
   */
  public void startGame() {
    level = 1;
    score = 0;
    gameInfoDisplay = new Text(createTextDisplay());
    gameInfoDisplay.setX(sceneControls.getSceneWidth() - 170);
    gameInfoDisplay.setY(20);
    gameInfoDisplay.setFont(new Font(20));
    funFact = new Text();
    funFact.setX(sceneControls.getSceneWidth()/2.0-100);
    funFact.setY(sceneControls.getSceneHeight()/2.0);
    funFact.setFont(new Font(48));

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
    gameMessage.setX(Main.DEFAULT_SIZE.width/2.0-200);
    gameMessage.setY(Main.DEFAULT_SIZE.height/2.0);
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
  protected void updateGameDisplay(int[] scoresToLevelUp, int maxLevel, String energyType) {
    if (getLives() == 0) {
      showGameWinningOrLosingMessage("gameLosingMessage"+energyType);
    }
    if (score >= scoresToLevelUp[level-1]) {
      if (level > maxLevel) {
        showGameWinningOrLosingMessage("gameWinningMessage"+energyType);
      } else {
        showFunFact("funFact"+level+energyType);
      }
      level++;
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
}
