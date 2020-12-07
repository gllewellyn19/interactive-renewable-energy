package ire.view.animations;

import ire.view.ErrorPrintable;
import ire.view.SceneControls;
import ire.view.buttons.BackButton;
import ire.view.buttons.NextButton;
import ire.view.buttons.StepBackAnimationButton;
import java.util.ResourceBundle;
import javafx.scene.control.ButtonBase;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * This abstract class is the template for any animations that are added to the learning experience.
 *
 * @author Grace Llewellyn
 */
public abstract class EnergyAnimation implements Animationable{

  private final ResourceBundle languageResources;
  private final SceneControls sceneControls;
  private final ErrorPrintable errorPrintable;
  private int currentAnimation = 0;
  private ButtonBase nextButton;
  private ButtonBase stepBackAnimation;
  private ButtonBase backButton;

  public EnergyAnimation(ResourceBundle languageResources, SceneControls sceneControls, ErrorPrintable
      errorPrintable) {
    this.languageResources = languageResources;
    this.sceneControls = sceneControls;
    this.errorPrintable = errorPrintable;
  }

  /**
   * Starts the animation by displaying the necessary buttons
   */
  public void startAnimation() {
    if (sceneControls.getRoot().isPresent()) {
      backButton = new BackButton(languageResources, sceneControls).getCurrButton();
      backButton.setLayoutX(0);
      backButton.setLayoutY(0);
      stepBackAnimation = new StepBackAnimationButton(languageResources, this).getCurrButton();
      stepBackAnimation.setLayoutX(128);
      stepBackAnimation.setLayoutY(0);
      stepBackAnimation.setDisable(true);
      nextButton = new NextButton(languageResources, this).
          getCurrButton();
      nextButton.setLayoutX(194);
      nextButton.setLayoutY(0);
    }
    addButtons();
  }

  /**
   * Checks if the user entered a cheat key and does the necessary action. For example, the user
   * pressing n moves to the next animation
   *
   * @param code code that could be cheat key
   */
  public void handleKeyInput(KeyCode code) {
    if (code == KeyCode.N) {
      stepToNextAnimation();
    }
  }

  public void stepAnimation(double elapsedTime) {

  }

  @Override
  public abstract void stepToNextAnimation();

  protected SceneControls getSceneControls() {
    return sceneControls;
  }

  protected ErrorPrintable getErrorPrintable() {
    return errorPrintable;
  }

  protected int getCurrentAnimation() {
    return currentAnimation;
  }

  protected void incrementCurrentAnimation(){
    currentAnimation++;
  }

  protected void disableNextButton() {
    nextButton.setDisable(true);
  }

  /*
   * Adds the next, step back, and back buttons to the animation
   */
  protected void addButtons() {
    if (sceneControls.getRoot().isPresent()) {
      sceneControls.getRoot().get().getChildren().add(backButton);
      sceneControls.getRoot().get().getChildren().add(stepBackAnimation);
      sceneControls.getRoot().get().getChildren().add(nextButton);
    }
  }

  protected void createExplanationText(String key) {
    Text explanationText = new Text(languageResources.getString(key));
    explanationText.setX(250);
    explanationText.setY(100);
    explanationText.setFont(new Font(24));
    sceneControls.getRoot().get().getChildren().add(explanationText);
  }

  protected void createLeftImage(String imageFilePath) {
    ImageView imageView = new ImageView();
    Image image = new Image(imageFilePath);
    imageView.setImage(image);
    imageView.setFitHeight(200);
    imageView.setFitWidth(200);
    imageView.setX(200);
    imageView.setY(200);
    sceneControls.getRoot().get().getChildren().add(imageView);
  }

  protected void createRightImage(String imageFilePath) {
    ImageView imageView = new ImageView();
    Image image = new Image(imageFilePath);
    imageView.setImage(image);
    imageView.setFitHeight(200);
    imageView.setFitWidth(200);
    imageView.setX(450);
    imageView.setY(200);
    sceneControls.getRoot().get().getChildren().add(imageView);
  }

  protected void createCenterImage(String imageFilePath) {
    ImageView imageView = new ImageView();
    Image image = new Image(imageFilePath);
    imageView.setImage(image);
    imageView.setFitHeight(300);
    imageView.setFitWidth(300);
    imageView.setX(300);
    imageView.setY(300);
    sceneControls.getRoot().get().getChildren().add(imageView);
  }

  public void backToLastAnimation() {
    currentAnimation-=2;
    if (currentAnimation<=0) {
      stepBackAnimation.setDisable(true);
    }
    stepToNextAnimation();
    if (currentAnimation <= 0) {
      stepBackAnimation.setDisable(true);
    }
  }

  protected void enableStepBackButton() {
    stepBackAnimation.setDisable(false);
  }

  protected void disableStepButton() {
    stepBackAnimation.setDisable(true);
  }


}
