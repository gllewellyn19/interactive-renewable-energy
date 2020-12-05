package ire.view.buttons;

import ire.view.GameStatus;
import ire.view.SceneControls;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

public class StartAnimationButton extends BoardButton{

  private SceneControls sceneControls;

  public StartAnimationButton(ResourceBundle resources, SceneControls sceneControls) {
    super(resources, new Button(), "StartAnimationButton");
    this.sceneControls = sceneControls;
    initializeButton();
  }

  /**
   * initializes button to start the game when pressed
   */
  @Override
  protected void initializeButton() {
    super.initializeButton();
    super.getCurrButton().setOnAction(event -> sceneControls.startGame(GameStatus.ANIMATION));
  }

}
