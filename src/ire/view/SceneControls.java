package ire.view;

import java.util.Optional;
import javafx.scene.Group;

/**
 * This class allows other classes like the game to interact with components of the main such as
 * its root
 *
 * @author Grace Llewellyn
 */
public interface SceneControls {

  void createGeneralEnergyTypeScene();
  void restart();
  void createAnimationScreen();
  void createGameScreen();
  void startGame(GameStatus gameOrAnimation);
  double getSceneWidth();
  double getSceneHeight();
  Optional<Group> getRoot();
  GameStatus getGameStatus();
}
