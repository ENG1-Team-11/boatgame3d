package io.github.eng1team11.boatgame2d.ecs.component;

public class Controller extends Component {

    boolean _left = false;
    boolean _right = false;

    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public Controller(int id) {
        super(id);
    }

    /**
     * Get the value of the left input state
     * @return the left input state as a boolean
     */
    public boolean GetLeft() {
        return _left;
    }

    /**
     * Get the value of the right input state
     * @return the right input state as a boolean
     */
    public boolean GetRight() {
        return _right;
    }

    /**
     * Set the left input state
     * @param v Whether the left key is pressed
     */
    public void SetLeft(boolean v) {
        _left = v;
    }

    /**
     * Set the right input state
     * @param v Whether the right key is pressed
     */
    public void SetRight(boolean v) {
        _right = v;
    }
}
