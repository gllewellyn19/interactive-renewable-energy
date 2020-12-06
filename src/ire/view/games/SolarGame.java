package ire.view.games;

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
import javafx.scene.text.Text;

public class SolarGame extends Game {

  private static final int NEW_SUN_COUNT = 50;
  private static final String FILE_PATH = "solarGame/";
  private static final String SCORE_INDICATOR = "Score: ";
  private static final String LIVES_INDICATOR = "Lives: ";
  private static final String LEVEL_INDICATOR = "Level: ";

  private Rectangle panel;
  private List<Circle> suns = new ArrayList<>();
  private List<Double> xDirection;
  private List<Double> yDirection;
  private int sunSpeed;
  private boolean paused;
  private final ResourceBundle languageResources;
  private int sunCount;
  private Random rand;
  private int lives;
  private int score;
  private Text scoreText;
  private Text livesText;

  public SolarGame(ResourceBundle languageResources, SceneControls sceneControls) {
    super(sceneControls);
    this.languageResources = languageResources;
    rand = new Random();
  }

  @Override
  public void handleKeyInput(KeyCode code) {
    super.handleKeyInput(code);
      if (code == KeyCode.A) {
        panel.setX(panel.getX() - 20);
      }
      if (code == KeyCode.S) {
        panel.setX(panel.getX() + 20);
      }
  }

  @Override
  public Node getGamePicture() {
    return null;
  }

  @Override
  public void startGame() {
    super.getSceneControls().getRoot().get().getChildren().clear();
    sunCount = 0;
    paused = true;
    sunSpeed = 100;

    xDirection = new ArrayList<>();
    xDirection.add(rand.nextDouble());
    yDirection = new ArrayList<>();
    suns = new ArrayList<>();
    yDirection.add(.80);
    lives = 3;
    score = 0;
    scoreText = new Text();
    scoreText.setX(10);
    scoreText.setY(50);
    scoreText.setText(SCORE_INDICATOR+score);
    livesText = new Text();
    livesText.setX(10);
    livesText.setY(70);
    livesText.setText(LIVES_INDICATOR+lives);

    Circle sun = new Circle(100, 10, 20);
    Image img1 = new Image(FILE_PATH+"sun.png");
    sun.setFill(new ImagePattern(img1));
    suns.add(sun);
    panel = new Rectangle(super.getSceneControls().getSceneWidth() - 100, super.getSceneControls().getSceneHeight() - 100, 100, 10);
    Image img2 = new Image(FILE_PATH+"solar_panel.png");
    panel.setFill(new ImagePattern(img2));
    if (super.getSceneControls().getRoot().isPresent()) {
      super.getSceneControls().getRoot().get().getChildren().add(sun);
      super.getSceneControls().getRoot().get().getChildren().add(panel);
      super.getSceneControls().getRoot().get().getChildren().add(new BackButton(languageResources,
              super.getSceneControls()).getCurrInteractiveFeature());
      super.getSceneControls().getRoot().get().getChildren().add(scoreText);
      super.getSceneControls().getRoot().get().getChildren().add(livesText);
    }
    paused = false;
  }

  @Override
  public void stepGame(double elapsedTime) {
    if (!paused) {
      updateSuns(elapsedTime);
      sunCount += 1;
    }
    checkForNewSun();
    checkIfAlive();
    updateTexts();
  }

  private void updateTexts(){
    scoreText.setText(SCORE_INDICATOR+score);
    livesText.setText(LIVES_INDICATOR+lives);
  }

  private void checkForNewSun() {
    if(sunCount == NEW_SUN_COUNT){
      Circle sun = new Circle(rand.nextInt((int)super.getSceneControls().getSceneWidth()), 10, 20);
      Image img1 = new Image(FILE_PATH+"sun.png");
      sun.setFill(new ImagePattern(img1));
      suns.add(sun);
      xDirection.add(rand.nextDouble());
      yDirection.add(yDirection.get(0));
      if (super.getSceneControls().getRoot().isPresent()) {
        super.getSceneControls().getRoot().get().getChildren().add(sun);
      }
      sunCount = 0;
    }
  }

  private void updateSuns(double elapsedTime){
    if (!paused) {
      for (int i = 0; i < suns.size(); i++) {
        Circle sun = suns.get(i);
        double newBallX = sun.getCenterX() + xDirection.get(i) * sunSpeed * elapsedTime;
        double newBallY = sun.getCenterY() + yDirection.get(i) * sunSpeed * elapsedTime;
        sun.setCenterX(newBallX);
        sun.setCenterY(newBallY);
        if (sun.intersects(panel.getLayoutBounds())) {
          score += 10;
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

  private void removeSunFromRoot(Circle sun) {
    if (super.getSceneControls().getRoot().isPresent()) {
      try {
        super.getSceneControls().getRoot().get().getChildren().remove(sun);
      } catch (IllegalArgumentException e) {
        //nothing happens
      }
    }
  }

  private void checkIfAlive(){
    if(lives == 0){
      paused = true;
      if (super.getSceneControls().getRoot().isPresent()) {
        try{
          super.getSceneControls().getRoot().get().getChildren().remove(scoreText);
          super.getSceneControls().getRoot().get().getChildren().remove(livesText);
        }
        catch(IllegalArgumentException ignored){

        }
      }
      Text loseText = new Text();
      loseText.setText("You lose. Your score is: "+score);
      loseText.setX(10);
      loseText.setY(super.getSceneControls().getSceneHeight()/2);
      if (super.getSceneControls().getRoot().isPresent()) {
        super.getSceneControls().getRoot().get().getChildren().add(loseText);
      }
    }
  }
}
