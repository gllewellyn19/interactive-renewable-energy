package ire.view.energyTypes;

import ire.view.SceneControls;
import java.io.File;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public abstract class RenewableEnergyType {

  //public static final String IMAGE_PATH = new File("");
  public static final String IMAGE_EXTENSION = ".jpg";

  private String energyType;
  private SceneControls sceneControls;

  public RenewableEnergyType(SceneControls sceneControls, String energyType) {
    this.energyType = energyType;
    this.sceneControls = sceneControls;
  }

  public void initializeEnergyType() {
    sceneControls.createGeneralEnergyTypeScene();
  }

  //FIXME: Cams you can use this for your games
  public abstract void handleKeyInput(KeyCode code);

  public String getEnergyType() {
    return energyType;
  }

  public Node getEnergyTypePicture() {
    ImageView toReturn = new ImageView();
    Image energyTypePicture = new Image(energyType+IMAGE_EXTENSION);
    toReturn.setImage(energyTypePicture);
    toReturn.setFitHeight(500);
    toReturn.setFitWidth(600);
    return toReturn;
  }

  public abstract Node createEnergyTypeDisplay();

  //FIXME: Cams if you want a picture to show up on the initial screen for the game then implement this
  // method
  public abstract Node getGamePicture();

  //FIXME: Cams implement this function to show how you want your game to initially look- this will
  // be displayed in the middle of the screen- can use sceneControls to get the root to add shapes to
  public abstract void startGame();

  //FIXME: Cams implement this function to step through your game- this is called every second or so
  // but only when an active game is happening
  public abstract void stepGame(double elapsedTime);
}
