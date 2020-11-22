package io.github.eng1team11.boatgame2d.ui;

import com.badlogic.gdx.graphics.Texture;
import io.github.eng1team11.boatgame2d.util.Vector2;

public class Image extends SpriteObject {
    /**
     * Default c'tor for an Image.  (It's just a SpriteObject)
     *
     * @param position The position of the UI element (top-left corner)
     * @param size     The size of the UI element
     */
    Image(Vector2 position, Vector2 size, Texture texture) {
        super(position, size, texture);
    }

}
