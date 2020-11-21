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

    public void SetModifier(float m) {
        _accelerationModifier = m;
    }

    public float GetModifier() {
        return _accelerationModifier;
    }

    public float GetAcceleration() {
        return _acceleration;
    }

    public float GetAccelerationModified() {
        return _accelerationModifier * _acceleration;
    }
}
