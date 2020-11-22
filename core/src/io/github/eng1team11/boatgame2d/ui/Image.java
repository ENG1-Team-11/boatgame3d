package io.github.eng1team11.boatgame2d.ui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.eng1team11.boatgame2d.util.Vector2;

public class Image extends SpriteObject {
    /**
     * Default c'tor for an Image.  (It's just a SpriteObject)
     *
     * @param position The position of the UI element (top-left corner)
     * @param size     The size of the UI element
     * @param parent   The parent object of the UI element
     * @param sprite   The sprite representing the image
     */
    Image(Vector2 position, Vector2 size, UIObject parent, Sprite sprite) {
        super(position, parent, size, sprite);
    }

}
