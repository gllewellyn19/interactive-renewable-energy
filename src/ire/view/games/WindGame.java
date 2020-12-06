package ire.view.games;

import ire.Main;
import ire.view.GameStatus;
import ire.view.SceneControls;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import ire.view.buttons.BackButton;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WindGame extends Game {

  private static final int NEW_TURBINE = 40;
  private static final String FILE_PATH = "windGame/";
  private static final String SCORE_INDICATOR = "Score: ";
  private static final String LIVES_INDICATOR = "Lives: ";
  private static final String LEVEL_INDICATOR = "Level: ";
  private static final int[] SCORES_TO_LEVEL_UP = {30, 60, 100};
  private static final int[] WIND_MILL_SPEEDS = {-100, -150, -200};
  private static final int MAX_NUM_LEVELS = 3;

  private Circle bird;
  private List<Rectangle> turbines = new ArrayList<>();
  private double xDirection;
  private boolean paused;
  private final ResourceBundle languageResources;
  private int winMillCount;
  private Random rand;
  private int lives;
  private int score;
  private int level;
  private Text gameInfoDisplay;

  public WindGame(ResourceBundle languageResources, SceneControls sceneControls) {
    super(sceneControls);
    this.languageResources = languageResources;
    rand = new Random();
  }

  @Override
  public void handleKeyInput(KeyCode code) {
    super.handleKeyInput(code);
      if (code == KeyCode.W) {
        bird.setCenterY(bird.getCenterY() - 20);
      }
      if (code == KeyCode.Z) {
        bird.setCenterY(bird.getCenterY() + 20);
      }
  }

  @Override
  public Node getGamePicture() {
    return null;
  }

  @Override
  public void startGame() {
    super.getSceneControls().getRoot().get().getChildren().clear();
    winMillCount = 0;
    paused = true;
    xDirection = .80;
    lives = 3;
    score = 0;
    lives = 3;
    score = 0;
    level = 1;
    gameInfoDisplay = new Text(createTextDisplay());
    gameInfoDisplay.setX(Main.DEFAULT_SIZE.width - 100);
    gameInfoDisplay.setY(50);
    gameInfoDisplay.setFont(new Font(24));

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
      super.getSceneControls().getRoot().get().getChildren().add(new BackButton(languageResources,
              super.getSceneControls()).getCurrInteractiveFeature());
      super.getSceneControls().getRoot().get().getChildren().add(gameInfoDisplay);
    }
    paused = false;
  }

  @Override
  public void stepGame(double elapsedTime) {
    if (!paused) {
      updateTurbines(elapsedTime);
      winMillCount += 1;
    }
    checkForNewTurbine();
    updateGameDisplay();
  }

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

  private void updateTurbines(double elapsedTime){
    for(int i = 0; i< turbines.size(); i++){
      Rectangle turbine = turbines.get(i);
      double newTurbineX = turbine.getX() + xDirection * WIND_MILL_SPEEDS[level-1] * elapsedTime;
      turbine.setX(newTurbineX);
      if(turbine.intersects(bird.getLayoutBounds())){
        lives -= 1;
        removeTurbineFromRoot(turbine);
        turbines.remove(turbine);
      } else if (turbine.getX() < bird.getCenterX() - bird.getRadius()) {
        score += 10;
        removeTurbineFromRoot(turbine);
        turbines.remove(turbine);
      }
    }
  }

  private void removeTurbineFromRoot(Rectangle turbine) {
    if (super.getSceneControls().getRoot().isPresent()) {
      try {
        super.getSceneControls().getRoot().get().getChildren().remove(turbine);
      } catch (IllegalArgumentException ignored) {
      }
    }
  }

  /*
   * Updates the display of the game information to reflect a change in the lives, score, and level.
   * Also tells the user if they have won or lost the game
   */
  private void updateGameDisplay() {
    if (lives == 0) {
      showGameWinningOrLosingMessage("gameLosingMessageWind");
    }
    if (score >= SCORES_TO_LEVEL_UP[level-1]) {
      level ++;
      if (level > MAX_NUM_LEVELS) {
        showGameWinningOrLosingMessage("gameWinningMessageWind");
      }
    }
    gameInfoDisplay.setText(createTextDisplay());
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
   * Creates the text for the display board to show the score, lives and level.
   */
  private String createTextDisplay() {
    return SCORE_INDICATOR + score + "\n" + LIVES_INDICATOR + lives + "\n" + LEVEL_INDICATOR
        + level;
  }
}