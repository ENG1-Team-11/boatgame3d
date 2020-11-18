package io.github.eng1team11.boatgame2d.ecs.component;

public class AI extends Component {
    // Not sure what this is for?
    boolean left = false;
    boolean right = false;

    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public AI(int id) {
        super(id);
    }
}
