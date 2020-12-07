package ire.view.animations;

import ire.view.ErrorPrintable;
import ire.view.SceneControls;
import java.util.ResourceBundle;

/**
 * This class creates the animation of solar energy in a step by step model with pictures and
 * descriptions.
 *
 * @author Grace Llewellyn
 */
public class SolarEnergyAnimation extends EnergyAnimation {

  public static final String ANIMATION_FILE_PATH = "solarAnimation/";
  public static final int TOTAL_NUMBER_ANIMATIONS = 4;

  public SolarEnergyAnimation(ResourceBundle languageResources,
      SceneControls sceneControls, ErrorPrintable errorPrintable) {
    super(languageResources, sceneControls, errorPrintable);
  }

  @Override
  public void startAnimation() {
    try {
      super.startAnimation();
      if (super.getSceneControls().getRoot().isPresent()) {
        super.createLeftImage(ANIMATION_FILE_PATH + "sunRays.jpg");
        super.createRightImage(ANIMATION_FILE_PATH + "solarFarm.jpg");
        super.createExplanationText("sunShines");
      }
    } catch (IllegalArgumentException e) {
      super.getErrorPrintable().printErrorMessageAlert("missingImageAnimation");
    }
  }

  @Override
  public void stepToNextAnimation() {
    if (super.getSceneControls().getRoot().isPresent()) {
      try {
        super.getSceneControls().getRoot().get().getChildren().clear();
        super.enableStepBackButton();
        super.addButtons();
        super.incrementCurrentAnimation();
        int currentAnimation = super.getCurrentAnimation();
        if (currentAnimation == 0) {
          startAnimation();
        } else if (currentAnimation == 1) {
          super.createExplanationText("introPanel");
          super.createCenterImage(ANIMATION_FILE_PATH + "solarCellNoElectrons.png");
        } else if (currentAnimation == 2) {
          super.createExplanationText("sunRaysHitPanel");
          super.createCenterImage(ANIMATION_FILE_PATH + "solarCellWithElectrons.png");
        } else if (currentAnimation == 3) {
          super.createExplanationText("electronsMovement");
          super.createCenterImage(ANIMATION_FILE_PATH + "solarCellWithElectrons.png");
        } else if (currentAnimation == 4) {
          super.createExplanationText("endAnimationMessage");
        } else {
          super.disableNextButton();
        }
        if (super.getCurrentAnimation() >= TOTAL_NUMBER_ANIMATIONS) {
          super.disableNextButton();
        }
      } catch (IllegalArgumentException e) {
        super.getErrorPrintable().printErrorMessageAlert("missingImageAnimation");
      }
    }
  }
}
