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
     * @param id the ID of the a component
     */
    public SpriteComponent(int id, String texturePath, int width, int height) {
        super(id);
        Texture t = new Texture(Gdx.files.internal(texturePath));
        _base.setTexture(t);
        _base.setSize(width, height);
    }
}
