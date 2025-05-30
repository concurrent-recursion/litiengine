package de.gurkenlabs.litiengine.gui.screens;

import de.gurkenlabs.litiengine.gui.GuiComponent;

/**
 * Screens are the containers that allow you to organize the visible contents of your game. They render the game’s
 * Environment and are considered the parent of all GUI components you want to display in a particular state of your
 * game. The screen itself inherits from GuiComponent and thereby provides support to define an Appearance and listen to
 * all kinds of Input events (e.g. {@code onMouseMoved(…)}). Everything that should be visible to the player needs to be
 * rendered to the currently active screen.
 */
public abstract class Screen extends GuiComponent {
  protected Screen(final String screenName) {
    super(0, 0);
    this.setName(screenName);
  }
  /**
   * Overrides the setX method from GuiComponent to prevent changing the x-coordinate.
   * This method does nothing because screens always start at the coordinates (0, 0).
   *
   * @param x The new x-coordinate, which will be ignored.
   */
  @Override
  public void setX(final double x) {
    // do nothing because screens always start at 0/0
  }

  /**
   * Overrides the setY method from GuiComponent to prevent changing the y-coordinate.
   * This method does nothing because screens always start at the coordinates (0, 0).
   *
   * @param y The new y-coordinate, which will be ignored.
   */
  @Override
  public void setY(final double y) {
    // do nothing because screens always start at 0/0
  }
}
