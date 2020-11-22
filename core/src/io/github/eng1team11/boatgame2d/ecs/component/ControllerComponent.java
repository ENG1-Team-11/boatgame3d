package io.github.eng1team11.boatgame2d.ecs.component;

public class ControllerComponent extends Component {

    boolean _left = false;
    boolean _right = false;
    boolean _forwards = false;
    boolean _backwards = false;

    /**
     * Default ctor for a component
     *
     * @param id The ID of the component
     */
    public ControllerComponent(int id) {
        super(id);
    }

    /**
     * Get the value of the left input state
     *
     * @return The left input state as a boolean
     */
    public boolean getLeft() {
        return _left;
    }

    /**
     * Set the left input state
     *
     * @param v Whether the left key is pressed
     */
    public void setLeft(boolean v) {
        _left = v;
    }

    /**
     * Get the value of the right input state
     *
     * @return The right input state as a boolean
     */
    public boolean getRight() {
        return _right;
    }

    /**
     * Set the right input state
     *
     * @param v Whether the right key is pressed
     */
    public void setRight(boolean v) {
        _right = v;
    }

    /**
     * Get the value of the forwards input state
     *
     * @return The forwards input state as a boolean
     */
    public boolean getForwards() {
        return _forwards;
    }

    /**
     * Set the forwards input state
     *
     * @param v Whether the forwards key is pressed
     */
    public void setForwards(boolean v) {
        _forwards = v;
    }

    /**
     * Get the value of the backwards input state
     *
     * @return The backwards input state as a boolean
     */
    public boolean getBackwards() {
        return _backwards;
    }

    /**
     * Set the backwards input state
     *
     * @param v Whether the backwards key is pressed
     */
    public void setBackwards(boolean v) {
        _backwards = v;
    }
}
