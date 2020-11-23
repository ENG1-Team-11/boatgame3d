package io.github.eng1team11.boatgame2d.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.eng1team11.boatgame2d.util.Vector2;

public class SpriteObject extends UIObject {

    Vector2 _size;
    Texture _texture;

    /**
     * Default c'tor for a Sprite Object
     *
     * @param position The position of the UI element (top-left corner)
     * @param size     The size of the UI object
     * @param texture  The sprite object's texture
     */
    SpriteObject(Vector2 position, Vector2 size, Texture texture) {
        super(position);
        _size = size;
        _texture = texture;
    }

    /**
     * Set the position of the sprite
     *
     * @param mouseX The x position of the mouse
     * @param mouseY The y position of the mouse
     * @param click  Whether or not the mouse has been clicked
     */
    @Override
    public void update(int mouseX, int mouseY, boolean click) {
        super.update(mouseX, mouseY, click);
    }

    /**
     * Draw the sprite
     *
     * @param spriteBatch The Sprite Batch to draw the object to
     */
    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
        spriteBatch.draw(_texture, _position._x, _position._y);
    }
}
