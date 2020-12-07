package ire.view;

/**
 * This enumerated type is used for determining if the game is currently in an animation, game or
 * neither which affects that happens when the user presses certain keys (i.e. nothing happens
 * when the user presses a game cheat key when they are not in a game)
 *
 * @author Grace Llewellyn
 */
public enum GameStatus {
  GAME, ANIMATION, NEITHER;
}
