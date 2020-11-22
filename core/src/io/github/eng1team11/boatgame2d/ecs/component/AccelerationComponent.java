package io.github.eng1team11.boatgame2d.ecs.component;

public class AccelerationComponent extends Component {

    float _acceleration = 1.0f;
    float _accelerationModifier = 1.0f;

    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public AccelerationComponent(int id) {
        super(id);
    }

    /**
     * Get the acceleration modifier
     *
     * @return The acceleration modifier as a float
     */
    public float getModifier() {
        return _accelerationModifier;
    }

    /**
     * Set the acceleration modifier
     *
     * @param m The value to set the acceleration modifier to
     */
    public void setModifier(float m) {
        _accelerationModifier = m;
    }

    /**
     * Get the base acceleration
     *
     * @return The base acceleration, unaffected by any modifiers, as a float
     */
    public float getAcceleration() {
        return _acceleration;
    }

    /**
     * Get the modified acceleration
     *
     * @return The acceleration, modified by the acceleration modifier, as a float
     */
    public float getAccelerationModified() {
        return _accelerationModifier * _acceleration;
    }
}
