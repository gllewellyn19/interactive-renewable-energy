package ire.view.energyTypes;

import ire.view.LanguageControls;
import ire.view.SceneControls;
import ire.view.buttons.BackButton;
import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class ExampleGameView extends RenewableEnergyType{

  private Circle ball;
  private Rectangle rectangle;
  private int xDirection = 1;
  private int yDirection = 1;
  private int ballSpeed = 20;
  private SceneControls sceneControls;
  private boolean paused = false;
  private ResourceBundle languageResources;

  public ExampleGameView(ResourceBundle languageResources, SceneControls sceneControls) {
    super(sceneControls, languageResources, "example");
    this.sceneControls = sceneControls;
    this.languageResources = languageResources;
  }

  @Override
  public void handleKeyInput(KeyCode code) {
    if (sceneControls.getInGameCurrently()) {
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

    if (sceneControls.getRoot().isPresent()) {
      sceneControls.getRoot().get().getChildren().add(ball);
      sceneControls.getRoot().get().getChildren().add(rectangle);
      sceneControls.getRoot().get().getChildren().add(new BackButton(languageResources,
          sceneControls).getCurrInteractiveFeature());
    }
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
