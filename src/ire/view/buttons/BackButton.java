package ire.view.buttons;

import ire.view.SceneControls;
import java.util.ResourceBundle;
import javafx.scene.control.Button;

/**
 * This button takes the user back to the splash screen. Pressing the B key has the same effect
 * as this button
 *
 * @author Grace Llewellyn
 */
public class BackButton extends BoardButton{

  private final SceneControls sceneControls;

  public BackButton(ResourceBundle resources, SceneControls sceneControls) {
    super(resources, new Button(), "BackButton");
    this.sceneControls = sceneControls;
    initializeButton();
  }

  /**
   * initializes button to go back to the splash screen when pressed
   */
  @Override
  protected void initializeButton() {
    super.initializeButton();
    super.getCurrButton().setOnAction(event -> sceneControls.restart());
  }
}
