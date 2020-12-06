package ire.view.games;

import ire.view.GameStatus;
import ire.view.SceneControls;
import java.util.ResourceBundle;

import ire.view.buttons.BackButton;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SolarGame extends Game {

  private Circle sun;
  private Rectangle panel;
  private int xDirection;
  private int yDirection;
  private int ballSpeed;
  private boolean paused;
  private final ResourceBundle languageResources;
  private static final int NEW_SUN_COUNT = 100;
  private int sunCount;

  public SolarGame(ResourceBundle languageResources, SceneControls sceneControls) {
    super(sceneControls);
    this.languageResources = languageResources;
    sunCount = 0;
    paused = true;
    ballSpeed = 20;
    xDirection = 1;
    yDirection = 1;
  }

  @Override
  public void handleKeyInput(KeyCode code) {
    if (super.getSceneControls().getGameStatus() == GameStatus.GAME) {
      if (code == KeyCode.LEFT) {
        panel.setX(panel.getX() - 10);
      }
      if (code == KeyCode.RIGHT) {
        panel.setX(panel.getX() + 10);
      }
    }
  }

  @Override
  public Node getGamePicture() {
    return null;
  }

  @Override
  public void startGame() {
    sun = new Circle(100, 100, 20);
    Image img1 = new Image("/data/games/sun.png");
    sun.setFill(new ImagePattern(img1));
    //sun.setFill(Color.BLACK);
    panel = new Rectangle(700, 700, 100, 10);
    Image img2 = new Image("/data/games/solar_panel.png");
    panel.setFill(new ImagePattern(img2));
    //panel.setFill(Color.BLACK);
    if (super.getSceneControls().getRoot().isPresent()) {
      super.getSceneControls().getRoot().get().getChildren().add(sun);
      super.getSceneControls().getRoot().get().getChildren().add(panel);
      super.getSceneControls().getRoot().get().getChildren().add(new BackButton(languageResources,
              super.getSceneControls()).getCurrInteractiveFeature());
      ImageView waveImageView = new ImageView();
      Image waveImage = new Image("hydroAnimation/water.jpg");
      waveImageView.setImage(waveImage);
      waveImageView.setFitHeight(200);
      waveImageView.setFitWidth(200);
      waveImageView.setX(500);
      waveImageView.setY(500);
      super.getSceneControls().getRoot().get().getChildren().add(waveImageView);
    }
    paused = false;
  }

  @Override
  public void stepGame(double elapsedTime) {
    if (!paused) {
      double newBallX = sun.getCenterX() + xDirection * ballSpeed * elapsedTime;
      double newBallY = sun.getCenterY() + yDirection * ballSpeed * elapsedTime;
      sun.setCenterX(newBallX);
      sun.setCenterY(newBallY);
      if (sun.getCenterX() > super.getSceneControls().getSceneWidth()) {

      } else if (sun.getCenterY() > super.getSceneControls().getSceneHeight()) {

      }
      sunCount += 1;
    }
  }
}
