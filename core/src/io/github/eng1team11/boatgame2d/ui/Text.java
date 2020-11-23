package io.github.eng1team11.boatgame2d.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Align;
import io.github.eng1team11.boatgame2d.util.Vector2;

public class Text extends UIObject {

    String _text;
    BitmapFont _font;

    /**
     * Default c'tor for a Text object
     *
     * @param position The position of the UI element (top-left corner)
     * @param text The text to show
     * @param font The font to use
     */
    public Text(Vector2 position, String text, BitmapFont font) {
        super(position);
        _text = text;
        _font = font;
    }

    public void setText(String text) {
        _text = text;
    }

    /**
     * Draw the object
     *
     * @param spriteBatch The Sprite Batch to draw the object to
     */
    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
        _font.draw(spriteBatch, _text, _position._x, _position._y);
    }
}
