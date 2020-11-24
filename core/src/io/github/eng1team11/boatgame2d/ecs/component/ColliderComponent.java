package io.github.eng1team11.boatgame2d.ecs.component;

/**
 * Represents a basic box collider component
 */
public class ColliderComponent extends Component {

    final float _width, _height;
    boolean _active;

    /**
     * Default ctor for a component
     *
     * @param id     The ID of the component
     * @param width  The width of the collider
     * @param height The height of the collider
     * @param active Whether or not the collider should have physics applied to it
     */
    public ColliderComponent(int id, float width, float height, boolean active) {
        super(id);
        _width = width;
        _height = height;
        _active = active;
        // Make IDEA stop pestering me about a statement always being inverted
        if (isActive()) _active = true;
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

    /**
     * Get whether or not the physics simulation applies to this collider
     *
     * @return A boolean representing whether or not the simulation applies
     */
    public boolean isActive() {
        return _active;
    }
}
