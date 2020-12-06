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
  public static final int FISH_SPEED = 150;
  public static final int DAM_START_Y = Main.DEFAULT_SIZE.width - 200;
  public static final int INC_SCORE_BY = 10;
  public static final String SCORE_INDICATOR = "Score: ";

  private Rectangle turbine;
  private final ResourceBundle languageResources;
  private boolean paused = true;
  private Rectangle fish;
  private int score=0;
  private Text scoreDisplay;

  public HydroGame(ResourceBundle languageResources, SceneControls sceneControls) {
    super(sceneControls);
    this.languageResources = languageResources;
  }

  @Override
  public void handleKeyInput(KeyCode code) {
    if (super.getSceneControls().getGameStatus() == GameStatus.GAME) {
      if (code == KeyCode.A) {
        turbine.setX(turbine.getX() - DEFAULT_TURBINE_STEP);
      }
      if (code == KeyCode.S) {
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
    turbine = new Rectangle(Main.DEFAULT_SIZE.width/2.0, Main.DEFAULT_SIZE.width-200, 200, 200);
    turbine.setFill(new ImagePattern(new Image("hydroGame/turbine.jpg")));
    fish = new Rectangle(Main.DEFAULT_SIZE.width/2.0, 10, 75, 75);
    fish.setFill(new ImagePattern(new Image("hydroGame/fish.jpg")));
    scoreDisplay = new Text(SCORE_INDICATOR + "0");
    scoreDisplay.setX(Main.DEFAULT_SIZE.width-100);
    scoreDisplay.setY(50);
    scoreDisplay.setFont(new Font(24));
    if (super.getSceneControls().getRoot().isPresent()) {
      super.getSceneControls().getRoot().get().getChildren().add(fish);
      super.getSceneControls().getRoot().get().getChildren().add(new BackButton(languageResources,
          super.getSceneControls()).getCurrInteractiveFeature());
      super.getSceneControls().getRoot().get().getChildren().add(scoreDisplay);
      ImageView damImageView = new ImageView();
      Image damImage = new Image("hydroGame/dam.jpg");
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
      double newFishY = fish.getY() + FISH_SPEED * elapsedTime;
      fish.setY(newFishY);
      checkFishHitDam();
    }
  }

  /*
   * Checks to see if the fish hits the dam- if it did resets its position
   * If the fish did not hit the turbine then increases the score
   */
  private void checkFishHitDam() {
    System.out.println("fish y is "+fish.getY() +" and dam start is "+DAM_START_Y);
    if (fish.getY() + fish.getHeight() >= DAM_START_Y) {
      if (!fishInBoundsTurbine()) {
        score+=INC_SCORE_BY;
        updateScoreDisplay();
      }
      //reset the fish
      fish.setX(Math.random()*((Main.DEFAULT_SIZE.width- fish.getWidth()) + 1));
      fish.setY(10);
    }
  }

  private void updateScoreDisplay() {
    scoreDisplay.setText(SCORE_INDICATOR+score);
  }

  /*
   * Returns true if the fish was hit by the turbine
   */
  private boolean fishInBoundsTurbine() {
    return (fish.getX() >= turbine.getX() && fish.getX() <= (turbine.getX()+ turbine.getWidth())) ||
        ((fish.getX()+ fish.getWidth()) >= turbine.getX() && (fish.getX()+ fish.getWidth()) <=
            (turbine.getX()+ turbine.getWidth()));
  }
}
