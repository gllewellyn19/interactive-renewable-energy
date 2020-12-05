package ire.view.animations;

import ire.view.SceneControls;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HydroEnergyAnimation extends EnergyAnimation {

  public HydroEnergyAnimation(ResourceBundle languageResources,
      SceneControls sceneControls) {
    super(languageResources, sceneControls);
  }

  @Override
  public void startAnimation() {
    super.startAnimation();
    if (super.getSceneControls().getRoot().isPresent()) {
      ImageView waveImageView = new ImageView();
      Image waveImage = new Image("hydroAnimation/wave.jpg");
      waveImageView.setImage(waveImage);
      waveImageView.setFitHeight(200);
      waveImageView.setFitWidth(200);
      waveImageView.setX(200);
      waveImageView.setY(200);
      super.getSceneControls().getRoot().get().getChildren().add(waveImageView);
    }
  }

  @Override
  public void stepToNextAnimation() {

  }
}
