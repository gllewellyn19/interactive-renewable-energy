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

public class WindGame extends Game {

  private static final int NEW_TURBINE = 40;
  private static final String FILE_PATH = "windGame/";

  private Circle bird;
  private List<Rectangle> turbines = new ArrayList<>();
  private double xDirection;
  private int sunSpeed;
  private boolean paused;
  private final ResourceBundle languageResources;
  private int sunCount;
  private Random rand;
  private int lives;
  private int score;
  private Text scoreText;
  private Text livesText;

  public WindGame(ResourceBundle languageResources, SceneControls sceneControls) {
    super(sceneControls);
    this.languageResources = languageResources;
    sunCount = 0;
    paused = true;
    sunSpeed = -150;
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
      if (code == KeyCode.W) {
        bird.setCenterY(bird.getCenterY() - 20);
      }
      if (code == KeyCode.Z) {
        bird.setCenterY(bird.getCenterY() + 20);
      }
    }
  }

  @Override
  public Node getGamePicture() {
    return null;
  }

  @Override
  public void startGame() {
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
      super.getSceneControls().getRoot().get().getChildren().add(scoreText);
      super.getSceneControls().getRoot().get().getChildren().add(livesText);
    }
    paused = false;
  }

  @Override
  public void stepGame(double elapsedTime) {
    if (!paused) {
      updateTurbines(elapsedTime);
      sunCount += 1;
    }
    checkForNewTurbine();
    checkIfAlive();
    updateTexts();
  }

  private void updateTexts(){
    scoreText.setText("Score: "+score);
    livesText.setText("Lives: "+lives);
  }

  private void checkForNewTurbine() {
    if(sunCount == NEW_TURBINE){
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
      sunCount = 0;
    }
  }

  private void updateTurbines(double elapsedTime){
    for(int i = 0; i< turbines.size(); i++){
      Rectangle turbine = turbines.get(i);
      double newTurbineX = turbine.getX() + xDirection * sunSpeed * elapsedTime;
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