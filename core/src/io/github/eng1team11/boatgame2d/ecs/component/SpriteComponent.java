package io.github.eng1team11.boatgame2d.ecs.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.eng1team11.boatgame2d.util.Vector2;

public class SpriteComponent extends Component {

    public Texture _base;
    public Vector2 _size;
    public Vector2 _position;

    /**
     * Default ctor for a component
     *
     * @param id      The ID of the component
     * @param height  The height of the sprite
     * @param width   The width of the sprite
     * @param texture The texture to use
     */
    public SpriteComponent(int id, Texture texture, int width, int height) {
        super(id);
        _base = texture;
        _size = new Vector2(width, height);
        _position = new Vector2();
    }

    /**
     * Set the position of the sprite
     *
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public void setPosition(float x, float y) {
        _position._x = x;
        _position._y = y;
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(_base, _position._x, _position._y, _size._x, _size._y);
    }

}
