package io.github.eng1team11.boatgame2d.ecs.component;

/**
 * Represents a basic box collider component
 */
public class ColliderComponent extends Component {

    final float _width, _height;

    /**
     * Default ctor for a component
     *
     * @param id The ID of the component
     * @param width The width of the collider
     * @param height The height of the collider
     */
    public ColliderComponent(int id, float width, float height) {
        super(id);
        _width = width;
        _height = height;
    }

    /**
     * Get the width of the collider
     *
     * @return The width of the collider as a float
     */
    public float getWidth() {
        return _width;
    }

    /**
     * Get the height of the collider
     *
     * @return The height of the collider as a float
     */
    public float getHeight() {
        return _height;
    }
}
