package io.github.eng1team11.boatgame2d.ecs.component;

public class ClickableComponent extends Component {

    Runnable _function;


    /**
     * Default ctor for a component
     *
     * @param id the ID of the a component
     */
    public ClickableComponent(int id) {
        super(id);
    }

    /**
     * Run the function attached to the clickable component
     */
    public void runFunction() {
        _function.run();
    }
}
