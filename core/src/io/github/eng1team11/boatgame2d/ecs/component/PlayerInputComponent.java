package io.github.eng1team11.boatgame2d.ecs.component;

public class PlayerInputComponent extends ControllerComponent {
    boolean _leftKey = false;
    boolean _rightKey = false;
    float _mouse = 0.0f;

    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public PlayerInputComponent(int id) {
        super(id);
    }

    /**
     * Set the mouse input
     * @param v The value of the mouse input..?
     */
    public void SetMouse(float v) {
        _mouse = v;
    }

    /**
     * Get the value of the mouse input
     * @return the mouse input as a float
     */
    public float GetMouse() {
        return _mouse;
    }
}
