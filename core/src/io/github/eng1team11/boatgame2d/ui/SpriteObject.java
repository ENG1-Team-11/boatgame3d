package io.github.eng1team11.boatgame2d.ui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.eng1team11.boatgame2d.util.Vector2;

public class SpriteObject extends UIObject {

    Vector2 _size;
    Sprite _sprite;

    /**
     * Default c'tor for a UI object
     *
     * @param position The position of the UI element (top-left corner)
     * @param parent   The parent object of the UI element
     * @param size     The size of the UI object
     * @param sprite
     */
    SpriteObject(Vector2 position, UIObject parent, Vector2 size, Sprite sprite) {
        super(position, parent);
        _size = size;
        _sprite = sprite;
    }

    /**
     * Should be overridden to implement custom behaviours
     *
     * @param mouseX The x position of the mouse
     * @param mouseY The y position of the mouse
     * @param click  Whether or not the mouse has been clicked
     */
    @Override
    public void update(float mouseX, float mouseY, boolean click) {
        _sprite.setPosition(_absolutePosition._x, _absolutePosition._y);
    }

    /**
     * Draw the object
     *
     * @param spriteBatch The Sprite Batch to draw the object to
     */
    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
        _sprite.draw(spriteBatch);
    }
}
