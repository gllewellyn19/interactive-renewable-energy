package ire.view;

import java.util.Optional;
import javafx.scene.Group;
import javafx.scene.Parent;

public interface SceneControls {

  void createGeneralEnergyTypeScene();
  void restart();
  void createAnimationScreen();
  void createGameScreen();
  void startGame();
  double getSceneWidth();
  double getSceneHeight();
  Optional<Group> getRoot();
  boolean getInGameCurrently();
}
