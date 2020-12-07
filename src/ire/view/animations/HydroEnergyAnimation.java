package ire.view.animations;

import ire.view.ErrorPrintable;
import ire.view.SceneControls;
import java.util.ResourceBundle;

/**
 * This class creates the animation of hydropower in a step by step model with pictures and
 * descriptions.
 *
 * @author Grace Llewellyn
 */
public class HydroEnergyAnimation extends EnergyAnimation {

  public static final String ANIMATION_FILE_PATH = "hydroAnimation/";
  public static final int TOTAL_NUMBER_ANIMATIONS = 5;

  public HydroEnergyAnimation(ResourceBundle languageResources,
      SceneControls sceneControls, ErrorPrintable errorPrintable) {
    super(languageResources, sceneControls, errorPrintable);
  }

  @Override
  public void startAnimation() {
    try {
      super.startAnimation();
      if (super.getSceneControls().getRoot().isPresent()) {
        super.createLeftImage(ANIMATION_FILE_PATH+"water.jpg");
        super.createRightImage(ANIMATION_FILE_PATH+"dam.jpg");
        super.createExplanationText("waterFlows");
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
        super.createExplanationText("waterSpinsTurbine");
        super.createLeftImage(ANIMATION_FILE_PATH+"water.jpg");
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
