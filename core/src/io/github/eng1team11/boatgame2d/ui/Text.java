package io.github.eng1team11.boatgame2d.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import io.github.eng1team11.boatgame2d.util.Vector2;

public class Text extends UIObject {

    String _text;
    BitmapFont _font;

    /**
     * Default c'tor for a UI object
     *
     * @param position The position of the UI element (top-left corner)
     * @param parent   The parent object of the UI element
     */
    Text(Vector2 position, UIObject parent) {
        super(position, parent);
    }

    /**
     * Draw the object
     *
     * @param spriteBatch The Sprite Batch to draw the object to
     */
    @Override
    public void Draw(SpriteBatch spriteBatch) {
        super.Draw(spriteBatch);
        _font.draw(spriteBatch, _text, _absolutePosition._x, _absolutePosition._y);
    }
}
