package io.github.eng1team11.boatgame2d.ecs.component;

/**
 * Represents a basic box collider component
 */
public class ColliderComponent extends Component  {

    final float _width, _height;

    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public ColliderComponent(int id, float width, float height) {
        super(id);
        _width = width;
        _height = height;
    }

    public float GetWidth() {
        return _width;
    }

    public float GetHeight() {
        return _height;
    }
}
