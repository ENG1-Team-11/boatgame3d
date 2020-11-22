package io.github.eng1team11.boatgame2d.ecs.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent extends Component {

    // More or less just a wrapper for the GDX sprite
    public Sprite _base = new Sprite();

    /**
     * Default ctor for a component
     *
     * @param id          The ID of the component
     * @param height      The height of the sprite
     * @param width       The width of the sprite
     * @param texturePath The path of the texture to load
     */
    public SpriteComponent(int id, String texturePath, int width, int height) {
        super(id);
        Texture t = new Texture(Gdx.files.internal(texturePath));
        _base.setTexture(t);
        _base.setSize(width, height);
    }

    /**
     * Ctor for sprite component
     *
     * @param id The ID of the component
     * @param tx The texture to assign to the sprite
     * @param width The width of the sprite
     * @param height The height of the sprite
     */
    public SpriteComponent(int id, Texture tx, int width, int height) {
        super(id);
        _base.setTexture(tx);
        _base.setSize(width, height);
    }
}
