package ire.view.animations;

import ire.view.ErrorPrintable;
import ire.view.SceneControls;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HydroEnergyAnimation extends EnergyAnimation {

  public static final String ANIMATION_FILE_PATH = "hydroAnimation/";
  public static final int TOTAL_NUMBER_ANIMATIONS = 5;
  private final ErrorPrintable errorPrintable;

  public HydroEnergyAnimation(ResourceBundle languageResources,
      SceneControls sceneControls, ErrorPrintable errorPrintable) {
    super(languageResources, sceneControls);
    this.errorPrintable = errorPrintable;
  }

  @Override
  public void startAnimation() {
    try {
      super.startAnimation();
      if (super.getSceneControls().getRoot().isPresent()) {
        createLeftImage("water.jpg");
        createRightImage("dam.jpg");
        createExplanationText("waterFlows");
      }
      super.incrementCurrentAnimation();
    } catch (IllegalArgumentException e) {
      errorPrintable.printErrorMessageAlert("missingImageAnimation");
    }
  }

  @Override
  public void stepToNextAnimation() {
    if (super.getSceneControls().getRoot().isPresent()) {
      int currentAnimation = super.getCurrentAnimation();
      if (currentAnimation == 1) {
        super.getSceneControls().getRoot().get().getChildren().clear();
        createExplanationText("waterSpinsTurbine");
        createLeftImage("water.jpg");
        createRightImage("turbine.jpg");
      } else if (currentAnimation == 2) {
        super.getSceneControls().getRoot().get().getChildren().clear();
        createExplanationText("turbineSpins");
        createCenterImage("generator.jpg");
      } else if (currentAnimation == 3) {
        super.getSceneControls().getRoot().get().getChildren().clear();
        createExplanationText("rotorSpins");
        createCenterImage("generator.jpg");
      } else if (currentAnimation == 4) {
        super.getSceneControls().getRoot().get().getChildren().clear();
        createExplanationText("electricityCreated");
        createCenterImage("generator.jpg");
      } else {
        super.disableNextButton();
      }
      super.incrementCurrentAnimation();
      if (super.getCurrentAnimation() >= TOTAL_NUMBER_ANIMATIONS) {
        super.disableNextButton();
      }
    }
  }

  private void createExplanationText(String key) {
    super.addButtons();
    Text explanationText = new Text(super.getLanguageResources().getString(key));
    explanationText.setX(250);
    explanationText.setY(100);
    explanationText.setFont(new Font(24));
    super.getSceneControls().getRoot().get().getChildren().add(explanationText);
  }

  private void createLeftImage(String imageFilePath) {
    ImageView imageView = new ImageView();
    Image image = new Image(ANIMATION_FILE_PATH+imageFilePath);
    imageView.setImage(image);
    imageView.setFitHeight(200);
    imageView.setFitWidth(200);
    imageView.setX(200);
    imageView.setY(200);
    super.getSceneControls().getRoot().get().getChildren().add(imageView);
  }

  private void createRightImage(String imageFilePath) {
    ImageView imageView = new ImageView();
    Image image = new Image(ANIMATION_FILE_PATH+imageFilePath);
    imageView.setImage(image);
    imageView.setFitHeight(200);
    imageView.setFitWidth(200);
    imageView.setX(450);
    imageView.setY(200);
    super.getSceneControls().getRoot().get().getChildren().add(imageView);
  }

  private void createCenterImage(String imageFilePath) {
    ImageView imageView = new ImageView();
    Image image = new Image(ANIMATION_FILE_PATH+imageFilePath);
    imageView.setImage(image);
    imageView.setFitHeight(300);
    imageView.setFitWidth(300);
    imageView.setX(300);
    imageView.setY(300);
    super.getSceneControls().getRoot().get().getChildren().add(imageView);
  }
}
