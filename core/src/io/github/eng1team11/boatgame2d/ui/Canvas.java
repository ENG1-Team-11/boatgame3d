package io.github.eng1team11.boatgame2d.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.eng1team11.boatgame2d.util.Vector2;

public class Canvas extends UIObject {

    Canvas() {
        super(new Vector2(0.0f, 0.0f), null);
    }

    /**
     * Draw the object
     *
     * @param spriteBatch The Sprite Batch to draw the object to
     */
    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
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
        super.update(mouseX, mouseY, click);
    }
}
