package io.github.eng1team11.boatgame2d.ecs.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class SpriteComponent extends Component {

    // More or less just a wrapper for the GDX sprite
    public com.badlogic.gdx.graphics.g2d.Sprite _base = new com.badlogic.gdx.graphics.g2d.Sprite();

    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public SpriteComponent(int id, String texturePath) {
        super(id);
        Texture t = new Texture(Gdx.files.internal(texturePath));
        _base.setTexture(t);
        _base.setPosition(0.0f, 0.0f);
        _base.setSize(100, 100);
    }
}
