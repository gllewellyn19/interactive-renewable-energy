package ire.view.buttons;

import ire.view.SceneControls;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

/**
 * This button takes the user to the game screen where they can start the game
 *
 * @author Grace Llewellyn
 */
public class GoToGameButton extends BoardButton {

  private SceneControls sceneControls;

  public GoToGameButton(ResourceBundle resources, SceneControls sceneControls) {
    super(resources, new Button(), "GoToGameButton");
    this.sceneControls = sceneControls;
    initializeButton();
  }

  /**
   * initializes button to go back to the splash screen when pressed
   */
  @Override
  protected void initializeButton() {
    super.initializeButton();
    super.getCurrButton().setOnAction(event -> sceneControls.createGameScreen());
  }

}
