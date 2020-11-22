package io.github.eng1team11.boatgame2d.ecs.component;

public class PlayerInputComponent extends ControllerComponent {
    float _mouse = 0.0f;

    /**
     * Default ctor for a component
     *
     * @param id The ID of the component
     */
    public PlayerInputComponent(int id) {
        super(id);
    }

    /**
     * Get the value of the mouse input
     *
     * @return The mouse input as a float
     */
    public float getMouse() {
        return _mouse;
    }

    /**
     * Set the mouse input
     *
     * @param v The value of the mouse input..?
     */
    public void setMouse(float v) {
        _mouse = v;
    }
}
