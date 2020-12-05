package ire.view.games;

import ire.view.GameStatus;
import ire.view.SceneControls;
import ire.view.buttons.BackButton;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ExampleGame extends Game {

  private Circle ball;
  private Rectangle rectangle;
  private int xDirection = 1;
  private int yDirection = 1;
  private int ballSpeed = 20;
  private boolean paused = true;
  private final ResourceBundle languageResources;

  public ExampleGame(ResourceBundle languageResources, SceneControls sceneControls) {
    super(sceneControls);
    this.languageResources = languageResources;
  }

  @Override
  public void handleKeyInput(KeyCode code) {
    if (super.getSceneControls().getGameStatus() == GameStatus.GAME) {
      if (code == KeyCode.A) {
        rectangle.setX(rectangle.getX() - 10);
      }
      if (code == KeyCode.S) {
        rectangle.setX(rectangle.getX() + 10);
      }
    }
  }

  @Override
  public Node getGamePicture() {
    return null;
  }

  @Override
  public void startGame() {
    ball = new Circle(100, 100, 20);
    ball.setFill(Color.BLACK);
    rectangle = new Rectangle(300, 300, 100, 10);
    rectangle.setFill(Color.BLACK);
    if (super.getSceneControls().getRoot().isPresent()) {
      super.getSceneControls().getRoot().get().getChildren().add(ball);
      super.getSceneControls().getRoot().get().getChildren().add(rectangle);
      super.getSceneControls().getRoot().get().getChildren().add(new BackButton(languageResources,
          super.getSceneControls()).getCurrInteractiveFeature());
      ImageView waveImageView = new ImageView();
      Image waveImage = new Image("hydroAnimation/wave.jpg");
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
      double newBallX = ball.getCenterX() + xDirection * ballSpeed * elapsedTime;
      double newBallY = ball.getCenterY() + yDirection * ballSpeed * elapsedTime;
      ball.setCenterX(newBallX);
      ball.setCenterY(newBallY);
      if (ball.getCenterX() > super.getSceneControls().getSceneWidth()) {

      } else if (ball.getCenterY() > super.getSceneControls().getSceneHeight()) {

      }
    }
  }
}
