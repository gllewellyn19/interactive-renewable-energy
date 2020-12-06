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

  private final SceneControls sceneControls;
  private final ResourceBundle languageResources;
  private boolean pause = true;
  private boolean gameOver = false;
  private int level;
  private int score;
  private Text gameInfoDisplay;

  public Game(SceneControls sceneControls, ResourceBundle languageResources) {
    this.sceneControls = sceneControls;
    this.languageResources = languageResources;
  }

  public void handleKeyInput(KeyCode code) {
    if (code == KeyCode.P) {
      startGame();
    }
    if (code == KeyCode.H && !gameOver) {
      pause = !pause;
    }
  }

  public abstract Node getGamePicture();

  public void startGame() {
    level = 1;
    score = 0;
    gameInfoDisplay = new Text(createTextDisplay());
    gameInfoDisplay.setX(Main.DEFAULT_SIZE.width - 100);
    gameInfoDisplay.setY(50);
    gameInfoDisplay.setFont(new Font(24));

    if (sceneControls.getRoot().isPresent()) {
      sceneControls.getRoot().get().getChildren().add(new BackButton(languageResources,
          sceneControls).getCurrInteractiveFeature());
      sceneControls.getRoot().get().getChildren().add(gameInfoDisplay);
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

  /*
   * Creates the text for the display board to show the score, lives and level.
   */
  protected String createTextDisplay() {
    return SCORE_INDICATOR + score + "\n" + LIVES_INDICATOR + getLives() + "\n" + LEVEL_INDICATOR
        + level;
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
      level++;
      if (level > maxLevel) {
        showGameWinningOrLosingMessage("gameWinningMessage"+energyType);
      }
    }
    gameInfoDisplay.setText(createTextDisplay());
  }

  protected ResourceBundle getLanguageResources() {
    return languageResources;
  }

  protected void increaseScore(int incBy) {
    score+=incBy;
  }

  protected int getScore() {
    return score;
  }

  protected int getLevel() {
    return level;
  }

  protected void increaseLevelByOne() {
    level++;
  }
}
