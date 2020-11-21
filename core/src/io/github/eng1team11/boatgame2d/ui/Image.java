package io.github.eng1team11.boatgame2d.ui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.eng1team11.boatgame2d.util.Vector2;

public class Image extends SpriteObject {
    /**
     * Default c'tor for a UI object
     *
     * @param position The position of the UI element (top-left corner)
     * @param size     The size of the UI element
     * @param parent   The parent object of the UI element
     * @param sprite
     */
    Image(Vector2 position, Vector2 size, UIObject parent, Sprite sprite) {
        super(position, parent, size, sprite);
    }

    /**
     * Should be overridden to implement custom behaviours
     *
     * @param mouseX The x position of the mouse
     * @param mouseY The y position of the mouse
     * @param click  Whether or not the mouse has been clicked
     */
    @Override
    public void Update(float mouseX, float mouseY, boolean click) {
        super.Update(mouseX, mouseY, click);
        // Do nothing, *it's an image*
    }
    
}
