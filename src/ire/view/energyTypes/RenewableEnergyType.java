package ire.view.energyTypes;

import ire.view.GameStatus;
import ire.view.SceneControls;
import ire.view.animations.EnergyAnimation;
import ire.view.games.Game;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public abstract class RenewableEnergyType {

  //public static final String IMAGE_PATH = new File("");
  public static final String IMAGE_EXTENSION = ".jpg";

  private final String energyType;
  private final SceneControls sceneControls;
  private EnergyAnimation energyAnimation;
  private final ResourceBundle languageResources;
  private Game game;

  public RenewableEnergyType(SceneControls sceneControls, ResourceBundle languageResources,
      String energyType) {
    this.energyType = energyType;
    this.sceneControls = sceneControls;
    this.languageResources = languageResources;
  }

  public void initializeEnergyType() {
    sceneControls.createGeneralEnergyTypeScene();
  }

  public void handleKeyInput(KeyCode code) {
    if (sceneControls.getGameStatus() == GameStatus.GAME) {
      game.handleKeyInput(code);
    }
    else if (sceneControls.getGameStatus() == GameStatus.ANIMATION) {
      energyAnimation.handleKeyInput(code);
    }
  }

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

  public void startAnimation() {
    energyAnimation.startAnimation();
  }

  public void stepAnimation(double elapsedTime) {
    energyAnimation.stepAnimation(elapsedTime);
  }

  public Node getAnimationPicture() {
    ImageView toReturn = new ImageView();
    Image energyTypePicture = new Image(energyType+IMAGE_EXTENSION);
    toReturn.setImage(energyTypePicture);
    toReturn.setFitHeight(400);
    toReturn.setFitWidth(500);
    return toReturn;
  }

  public Node getGamePicture() {
    return game.getGamePicture();
  }

  public void startGame() {
    game.startGame();
  }

  public void stepGame(double elapsedTime) {
    game.stepGame(elapsedTime);
  }

  protected void setEnergyAnimation(EnergyAnimation energyAnimation) {
    this.energyAnimation = energyAnimation;
  }

  protected void setEnergyGame(Game game) {
    this.game = game;
  }

  protected ResourceBundle getLanguageResources() {
    return languageResources;
  }
}
