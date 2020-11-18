package io.github.eng1team11.boatgame2d.ecs.component;

public class PlayerInput extends Component  {
    boolean _leftKey = false;
    boolean _rightKey = false;
    float _mouse = 0.0f;

    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public PlayerInput(int id) {
        super(id);
    }

    /**
     * Set the left input state
     * @param v Whether the left key is pressed
     */
    public void SetLeft(boolean v) {
        _leftKey = v;
    }

    /**
     * Set the right input state
     * @param v Whether the right key is pressed
     */
    public void SetRight(boolean v) {
        _rightKey = v;
    }

    /**
     * Set the mouse input
     * @param v The value of the mouse input..?
     */
    public void SetMouse(float v) {
        _mouse = v;
    }

    /**
     * Get the value of the left input state
     * @return the left input state as a boolean
     */
    public boolean GetLeft() {
        return _leftKey;
    }

    /**
     * Get the value of the right input state
     * @return the right input state as a boolean
     */
    public boolean GetRight() {
        return _rightKey;
    }

    /**
     * Get the value of the mouse input
     * @return the mouse input as a float
     */
    public float GetMouse() {
        return _mouse;
    }
}
