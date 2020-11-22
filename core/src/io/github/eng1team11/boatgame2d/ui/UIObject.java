package io.github.eng1team11.boatgame2d.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.eng1team11.boatgame2d.util.Vector2;

public class UIObject {
    UIObject _parent;
    Vector2 _position;

    /**
     * Default c'tor for a UI object
     *
     * @param position The position of the UI element (top-left corner)
     */
    UIObject(Vector2 position) {
        _position = position;
    }

    /**
     * Should be overridden to implement custom behaviours
     *
     * @param mouseX The x position of the mouse
     * @param mouseY The y position of the mouse
     * @param click  Whether or not the mouse has been clicked
     */
    public void update(int mouseX, int mouseY, boolean click) {

    }

    /**
     * Draw the object.  Make sure to run `super.Draw(spriteBatch)` first on derived objects
     *
     * @param spriteBatch The Sprite Batch to draw the object to
     */
    public void draw(SpriteBatch spriteBatch) {

    }

    /**
     * Get the position of the UI element
     *
     * @return The position of the UI element relative to its parent, as a Vector2
     */
    public Vector2 getPosition() {
        return _position;
    }

    /**
     * Set the position of the UI element
     *
     * @param position The new position of the UI element
     */
    public void setPosition(Vector2 position) {
        _position = position;
    }

}
