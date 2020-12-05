package ire.view.energyTypes;

import ire.view.SceneControls;
import javafx.scene.Group;
import javafx.scene.Node;

public abstract class RenewableEnergyType {

  private String energyType;
  private SceneControls sceneControls;

  public RenewableEnergyType(SceneControls sceneControls, String energyType) {
    this.energyType = energyType;
    this.sceneControls = sceneControls;
  }

  public void initializeEnergyType() {
    sceneControls.createGeneralEnergyTypeScene();
  }

  public String getEnergyType() {
    return energyType;
  }

  public abstract Node getEnergyTypePicture();

  public abstract Node createEnergyTypeDisplay();

  public abstract Node getGamePicture();

  //FIXME: Cams implement this function to show how you want your game to initially look- this will
  // be displayed in the middle of the screen
  public abstract void startGame();

  //FIXME: Cams implement this function to step through your game- this is called every second or so
  // but only when an active game is happening
  public abstract void stepGame(double elapsedTime);
}
