package io.github.eng1team11.boatgame2d.ecs.component;

public class ControllerComponent extends Component {

    boolean _left = false;
    boolean _right = false;
    boolean _forwards = false;
    boolean _backwards = false;

    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public ControllerComponent(int id) {
        super(id);
    }

    /**
     * Get the value of the left input state
     *
     * @return the left input state as a boolean
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
     * @return the right input state as a boolean
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
     * Get the value of the left input state
     *
     * @return the left input state as a boolean
     */
    public boolean getForwards() {
        return _forwards;
    }

    /**
     * Set the left input state
     *
     * @param v Whether the left key is pressed
     */
    public void setForwards(boolean v) {
        _forwards = v;
    }

    /**
     * Get the value of the right input state
     *
     * @return the right input state as a boolean
     */
    public boolean getBackwards() {
        return _backwards;
    }

    /**
     * Set the right input state
     *
     * @param v Whether the right key is pressed
     */
    public void setBackwards(boolean v) {
        _backwards = v;
    }
}
