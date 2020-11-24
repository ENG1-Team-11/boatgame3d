package io.github.eng1team11.boatgame2d.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.eng1team11.boatgame2d.util.Vector2;

public class ProgressBar extends SpriteObject {

    float _progress;
    Texture _innerTexture;
    Vector2 _innerSize;

    /**
     * Default c'tor for a Sprite Object
     *
     * @param position The position of the UI element (top-left corner)
     * @param size     The size of the UI object
     * @param containerTexture  The texture the progress bar's container should use
     * @param barTexture The texture the progress bar should use
     */
    public ProgressBar(Vector2 position, Vector2 size, Texture containerTexture, Texture barTexture) {
        super(position, size, containerTexture);
        _innerSize = new Vector2(0.0f, _size._y);
        _innerTexture = barTexture;
        setProgress(0.0f);
    }

    /**
     * Set the bar's progress level to a given value
     * @param p The value to assign to the progress bar, from 0.0f to 1.0f
     */
    public void setProgress(float p) {
        _progress = Math.max(0.0f, Math.min(1.0f, p));
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
        _innerSize._x = _size._x * _progress;
    }

    /**
     * Draw the sprite
     *
     * @param spriteBatch The Sprite Batch to draw the object to
     */
    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(_texture, _position._x, _position._y, _size._x, _size._y);
        spriteBatch.draw(_innerTexture, _position._x + 2, _position._y + 2, (_size._x - 4) * _progress, _size._y - 4);
    }
}
