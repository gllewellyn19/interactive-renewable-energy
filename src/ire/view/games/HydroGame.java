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

public class HydroGame extends Game {

  private static final int NEW_SUN_COUNT = 50;

  private Rectangle prop;
  private List<Circle> fish = new ArrayList<>();
  private double xDirection;
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

  public HydroGame(ResourceBundle languageResources, SceneControls sceneControls) {
    super(sceneControls);
    this.languageResources = languageResources;
    sunCount = 0;
    paused = true;
    sunSpeed = 100;
    rand = new Random();
    xDirection = .80;
    lives = 3;
    score = 0;
    scoreText = new Text();
    scoreText.setX(10);
    scoreText.setY(50);
    scoreText.setText("Score: "+score);
    livesText = new Text();
    livesText.setX(10);
    livesText.setY(70);
    livesText.setText("Lives: "+score);
  }

  @Override
  public void handleKeyInput(KeyCode code) {
    if (super.getSceneControls().getGameStatus() == GameStatus.GAME) {
      if (code == KeyCode.A) {
        prop.setX(prop.getX() - 20);
      }
      if (code == KeyCode.S) {
        prop.setX(prop.getX() + 20);
      }
    }
  }

  @Override
  public Node getGamePicture() {
    return null;
  }

  @Override
  public void startGame() {
    Circle sun = new Circle(100, 10, 20);
    Image img1 = new Image("/games/fish.png");
    sun.setFill(new ImagePattern(img1));
    fish.add(sun);
    prop = new Rectangle(super.getSceneControls().getSceneWidth() - 100, super.getSceneControls().getSceneHeight() - 100, 100, 10);
    Image img2 = new Image("/games/propellor.png");
    prop.setFill(new ImagePattern(img2));
    if (super.getSceneControls().getRoot().isPresent()) {
      super.getSceneControls().getRoot().get().getChildren().add(sun);
      super.getSceneControls().getRoot().get().getChildren().add(prop);
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
    scoreText.setText("Score: "+score);
    livesText.setText("Lives: "+lives);
  }

  private void checkForNewSun() {
    if(sunCount == NEW_SUN_COUNT){
      Circle sun = new Circle(rand.nextInt((int)super.getSceneControls().getSceneWidth()), 10, 20);
      Image img1 = new Image("/games/sun.png");
      sun.setFill(new ImagePattern(img1));
      fish.add(sun);
      xDirection.add(rand.nextDouble());
      yDirection.add(yDirection.get(0));
      if (super.getSceneControls().getRoot().isPresent()) {
        super.getSceneControls().getRoot().get().getChildren().add(sun);
      }
      sunCount = 0;
    }
  }

  private void updateSuns(double elapsedTime){
    for(int i = 0; i< fish.size(); i++){
      Circle sun = fish.get(i);
      double newBallX = sun.getCenterX() + xDirection.get(i) * sunSpeed * elapsedTime;
      double newBallY = sun.getCenterY() + yDirection.get(i) * sunSpeed * elapsedTime;
      sun.setCenterX(newBallX);
      sun.setCenterY(newBallY);
      if(sun.intersects(prop.getLayoutBounds())){
        score += 10;
        removeSunFromRoot(sun);
        xDirection.remove(i);
        fish.remove(sun);
      } else if (sun.getCenterX() > super.getSceneControls().getSceneWidth() || sun.getCenterX() <= 0) {
        double speed = xDirection.get(i);
        xDirection.remove(i);
        xDirection.add(i,speed*-1);
      } else if (sun.getCenterY() > prop.getY() + prop.getHeight()) {
        lives -= 1;
        removeSunFromRoot(sun);
        xDirection.remove(i);
        fish.remove(sun);
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
        catch(IllegalArgumentException e){
          //do nothing
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