package ire.view.animations;

import ire.view.ErrorPrintable;
import ire.view.SceneControls;
import java.util.ResourceBundle;

public class WindEnergyAnimation extends EnergyAnimation{

  public static final String ANIMATION_FILE_PATH = "windAnimation/";
  public static final int TOTAL_NUMBER_ANIMATIONS = 5;


  public WindEnergyAnimation(ResourceBundle languageResources,
      SceneControls sceneControls, ErrorPrintable errorPrintable) {
    super(languageResources, sceneControls, errorPrintable);
  }
  @Override
  public void startAnimation() {
    try {
      super.startAnimation();
      if (super.getSceneControls().getRoot().isPresent()) {
        super.createLeftImage(ANIMATION_FILE_PATH+"wind.jpg");
        super.createRightImage(ANIMATION_FILE_PATH+"windFarm.jpg");
        super.createExplanationText("windFlows");
      }
    } catch (IllegalArgumentException e) {
      super.getErrorPrintable().printErrorMessageAlert("missingImageAnimation");
    }
  }

  @Override
  public void stepToNextAnimation() {
    if (super.getSceneControls().getRoot().isPresent()) {
      super.getSceneControls().getRoot().get().getChildren().clear();
      super.enableStepBackButton();
      super.addButtons();
      super.incrementCurrentAnimation();
      int currentAnimation = super.getCurrentAnimation();
      if (currentAnimation == 0) {
        startAnimation();
      } else if (currentAnimation == 1) {
        super.createExplanationText("windSpinsTurbine");
        super.createLeftImage(ANIMATION_FILE_PATH+"wind.jpg");
        super.createRightImage(ANIMATION_FILE_PATH+"turbine.jpg");
      } else if (currentAnimation == 2) {
        super.createExplanationText("turbineSpins");
        super.createCenterImage(ANIMATION_FILE_PATH+"generator.jpg");
      } else if (currentAnimation == 3) {
        super.createExplanationText("rotorSpins");
        super.createCenterImage(ANIMATION_FILE_PATH+"generator.jpg");
      } else if (currentAnimation == 4) {
        super.createExplanationText("electricityCreated");
        super.createCenterImage(ANIMATION_FILE_PATH+"generator.jpg");
      } else if (currentAnimation == 5) {
          super.createExplanationText("endAnimationMessage");
      } else {
        super.disableNextButton();
      }
      if (super.getCurrentAnimation() >= TOTAL_NUMBER_ANIMATIONS) {
        super.disableNextButton();
      }
    }
  }
}
