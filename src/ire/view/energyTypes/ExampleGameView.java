package ire.view.energyTypes;

import ire.view.SceneControls;
import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ExampleGameView extends RenewableEnergyType{

  private Circle ball;
  private int xDirection = 1;
  private int yDirection = 1;
  private int ballSpeed = 20;
  private SceneControls sceneControls;
  private boolean paused = false;

  public ExampleGameView(SceneControls sceneControls) {
    super(sceneControls, "example");
    this.sceneControls = sceneControls;
  }

  @Override
  public Node getEnergyTypePicture() {
    return null;
  }

  @Override
  public Node createEnergyTypeDisplay() {
    return null;
  }

  @Override
  public Node getGamePicture() {
    return null;
  }

  @Override
  public void startGame() {
    ball = new Circle(100, 100, 20);
    ball.setFill(Color.BLACK);
    if (sceneControls.getRoot().isPresent()) {
      System.out.println("added ball to the root");
      sceneControls.getRoot().get().getChildren().add(ball);
    }
    System.out.println("just created ball");
  }

  @Override
  public void stepGame(double elapsedTime) {
    if (!paused) {
      double newBallX = ball.getCenterX() + xDirection * ballSpeed * elapsedTime;
      double newBallY = ball.getCenterY() + yDirection * ballSpeed * elapsedTime;
      ball.setCenterX(newBallX);
      ball.setCenterY(newBallY);
      if (ball.getCenterX() > sceneControls.getSceneWidth()) {

      } else if (ball.getCenterY() > sceneControls.getSceneHeight()) {

      }
    }
  }
}
