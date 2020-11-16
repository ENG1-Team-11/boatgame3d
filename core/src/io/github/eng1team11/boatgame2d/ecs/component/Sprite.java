package io.github.eng1team11.boatgame2d.ecs.component;

public class Sprite extends Component {

    // More or less just a wrapper for the GDX sprite
    public com.badlogic.gdx.graphics.g2d.Sprite _base;

    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public Sprite(int id, int type) {
        super(id, type);
    }
}
