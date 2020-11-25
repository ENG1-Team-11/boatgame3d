package io.github.eng1team11.boatgame2d.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.eng1team11.boatgame2d.util.Vector2;

public class Text extends UIObject {

    GlyphLayout _layout;
    BitmapFont _font;
    float xOffset;

    /**
     * Default c'tor for a Text object
     *
     * @param position The position of the UI element (top-left corner)
     * @param text     The text to show
     * @param font     The font to use
     */
    public Text(Vector2 position, String text, BitmapFont font) {
        super(position);
        _font = font;
        _layout = new GlyphLayout();
        _layout.setText(font, text);
        xOffset = _layout.width / 2;
    }

    public void setText(String text) {
        _layout.setText(_font, text);
    }

    /**
     * Draw the object
     *
     * @param spriteBatch The Sprite Batch to draw the object to
     */
    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
        _font.draw(spriteBatch, _layout, _position._x - xOffset, _position._y);
    }
}
