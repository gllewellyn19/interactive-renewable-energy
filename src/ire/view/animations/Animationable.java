package ire.view.animations;

/**
 * This interface allows the next and the step back animation buttons to be able to go through the
 * animation
 *
 * @author Grace Llewellyn
 */
public interface Animationable {

  void stepToNextAnimation();
  void backToLastAnimation();

}
